package fr.epf.jestock;

/*
    Nom ......... : AccueilActivity.java
    Role ........ : Activité controllant l'identification de code EAN13
    Auteur ...... : DSI_2
*/


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.ResultatRecherche;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccueilActivity extends AppCompatActivity {

    @BindView(R.id.cameraPreview)
    SurfaceView scan;
    @BindView(R.id.edit_ref)
    EditText ref;
    private BarcodeDetector detector;
    private CameraSource cameraSource;
    final int RequestCameraPermissionID = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        ButterKnife.bind(this);

        //Mise en place de la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_accueil);
        toolbar.setTitle("Scanner");
        setSupportActionBar(toolbar);

        //Mise en place du détecteur de code EAN 13
        detector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.EAN_13).build();
        cameraSource = new CameraSource
                .Builder(this, detector)
                .setRequestedPreviewSize(640, 1200)
                .setAutoFocusEnabled(true)
                .build();

        //Mise en place de la caméra
        scan.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AccueilActivity.this,
                            new String[]{Manifest.permission.CAMERA},RequestCameraPermissionID);
                    return;
                }

                try {

                    cameraSource.start(scan.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });


        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {            }

            //Détection d'un code EAN 13
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> code = detections.getDetectedItems();
                if (code.size() != 0){
                    Log.d("BARCODE", code.valueAt(0).displayValue);

                    //Méthode d'envoie du code détecté vers le serveur web pour vérification avec la base de données
                    rechercheRef(Long.parseLong(code.valueAt(0).displayValue));
                    detector.release();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {            }

            //Détection d'un code EAN 13
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> code = detections.getDetectedItems();
                if (code.size() != 0){
                    Log.d("BARCODE", code.valueAt(0).displayValue);

                    //Méthode d'envoie du code détecté vers le serveur web pour vérification avec la base de données
                    rechercheRef(Long.parseLong(code.valueAt(0).displayValue));
                    detector.release();
                }

            }
        });
    }

    //Bouton de validation de la recherche manuelle
    @OnClick(R.id.bt_rechercher)
    public void rechercher(){

        rechercheRef(Long.parseLong(ref.getText().toString()));

    }

    //Ajout du menu à la toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barre_menu, menu);
        return true;
    }

    //Event pour chaque icone de la toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.action_affiche_listes:
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_notification:
                Intent intent1 = new Intent(this, ListDeficitActivity.class);
                startActivity(intent1);
                return true;

            case R.id.action_deconnexion :
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent2 = new Intent(this, ConnexionActivity.class);
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Empeche le retour en arrière apres avoir ajouter ou retirer un objet des stocks
    @Override
    public void onBackPressed(){
        return ;
    }

    //Méthode d'envoie du code détecté vers le serveur web pour vérification avec la base de données
    public void rechercheRef(final long ref){

        final Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_connexion))       //Ip machine locale, a definir dans la ressource String ip_connexion
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        IAppelBDD appelBDD = retrofit.create(IAppelBDD.class);
        Log.d("Campus", Compte.getCampus());

        Call<ResultatRecherche> call = appelBDD.sendReferenceStock(ref);

        call.enqueue(new Callback<ResultatRecherche>() {
            //Reponse recut
            @Override
            public void onResponse(Call<ResultatRecherche> call, Response<ResultatRecherche> response) {
                ResultatRecherche result = response.body();

                //Analyse du type de matériel détecté
                if (result.getResultat().equals("Stock")){
                    intent.putExtra("Type","Stock");
                    intent.putExtra("Nom",result.getNom());
                    intent.putExtra("Reference",result.getReference());
                    startActivity(intent);
                }
                if (result.getResultat().equals("Empruntable")) {
                    intent.putExtra("Type","Empruntable");
                    intent.putExtra("Nom",result.getNom());
                    intent.putExtra("Reference",result.getReference());
                    startActivity(intent);
                }
                if (result.getResultat().equals("Deux")) {
                    intent.putExtra("Type","Deux");
                    intent.putExtra("Nom",result.getNom());
                    intent.putExtra("Reference",result.getReference());
                    startActivity(intent);
                }
                if (result.getResultat().equals("Aucun")) {
                    Intent intent1 = new Intent(getApplicationContext(), RefInconnueActivity.class);
                    intent1.putExtra("Reference",ref);
                    startActivity(intent1);
                }
            }

            @Override
            public void onFailure(Call<ResultatRecherche> call, Throwable t) {

                Toast toast = Toast.makeText(getApplicationContext(), "ERROR1", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER, 125, 150);
                toast.show();
            }
        });
    }

}
