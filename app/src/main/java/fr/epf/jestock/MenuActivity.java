package fr.epf.jestock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.ReferenceEmprunt;
import fr.epf.jestock.model.SuccesRequete;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.ref_materiel)
    TextView reference;
    @BindView(R.id.nom_materiel)
    TextView nom;
    @BindView(R.id.text_quantity)
    TextView quantite;


    private Button hide1,hide2,hide3,hide4;
    private Intent recupData, retourListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        Context context = getApplicationContext();
        CharSequence text = "Le matériel a bien été reconnu!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 125, 150);
        toast.show();

        recupData = getIntent();

        long ref = recupData.getLongExtra("Reference",0);
        String name = recupData.getStringExtra("Nom");

        reference.setText(String.valueOf(ref));
        nom.setText(name);

        Log.d("Type", recupData.getStringExtra("Type"));

        if (recupData.getStringExtra("Type").equals("Stock")){
            hide1 = (Button)findViewById(R.id.bt_ajout_materiel_empruntable);
            hide2 = (Button)findViewById(R.id.bt_retrait_materiel_empruntable);
            hide3 = (Button)findViewById(R.id.bt_emprunt_materiel);
            hide4 = (Button)findViewById(R.id.bt_rendu_materiel);
            hide1.setVisibility(View.INVISIBLE);
            hide2.setVisibility(View.INVISIBLE);
            hide3.setVisibility(View.INVISIBLE);
            hide4.setVisibility(View.INVISIBLE);
        }
        if (recupData.getStringExtra("Type").equals("Empruntable")){
            hide1 = (Button)findViewById(R.id.bt_ajout_materiel_stock);
            hide2 = (Button)findViewById(R.id.bt_retrait_materiel_stock);
            hide1.setVisibility(View.INVISIBLE);
            hide2.setVisibility(View.INVISIBLE);
        }
    }

    @OnTouch(R.id.retirer1)
    public boolean retirer1(){
        int quantiteActuelle = Integer.parseInt(quantite.getText().toString());
        int quantiteFuture = quantiteActuelle - 1;
        if (quantiteFuture > 1) quantite.setText(String.valueOf(quantiteFuture));
        else quantite.setText("1");
        return true;
    }

    @OnTouch(R.id.ajouter1)
    public boolean ajouter1(){
        int quantiteActuelle = Integer.parseInt(quantite.getText().toString());
        int quantiteFuture = quantiteActuelle +1;
        quantite.setText(String.valueOf(quantiteFuture));
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barre_menu, menu);
        return true;
    }

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

    @OnClick(R.id.bt_ajout_materiel_stock)
    public void ajouterMateriel(){

        modifierQuantite("Stock", "Ajouter",
                        Long.parseLong(reference.getText().toString()),
                        Integer.parseInt(quantite.getText().toString()));

    }

    @OnClick(R.id.bt_retrait_materiel_stock)
    public void retirerMateriel(){

        modifierQuantite("Stock", "Retirer",
                Long.parseLong(reference.getText().toString()),
                Integer.parseInt(quantite.getText().toString()));
    }

    @OnClick(R.id.bt_ajout_materiel_empruntable)
    public void ajouterMaterielEmpruntable(){

        modifierQuantite("Empruntable", "Ajouter",
                Long.parseLong(reference.getText().toString()),
                Integer.parseInt(quantite.getText().toString()));
    }

    @OnClick(R.id.bt_retrait_materiel_empruntable)
    public void retirerMaterielEmpruntable(){

        modifierQuantite("Empruntable", "Retirer",
                Long.parseLong(reference.getText().toString()),
                Integer.parseInt(quantite.getText().toString()));
    }

    @OnClick(R.id.bt_emprunt_materiel)
    public void emprunterMateriel(){

        Intent intent = new Intent(this,EmpruntsActivity.class);
        ReferenceEmprunt.setReference(Long.parseLong(reference.getText().toString()));
        ReferenceEmprunt.setNom(nom.getText().toString());
        startActivity(intent);
    }

    @OnClick(R.id.bt_rendu_materiel)
    public void rendreMateriel(){

    }

    public void modifierQuantite(String typeMateriel, final String typeModif, long ref, int quantite){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_connexion))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        IAppelBDD appelBDD = retrofit.create(IAppelBDD.class);
        Log.d("Campus", Compte.getCampus());

        Call<SuccesRequete> call = appelBDD.modifierQuantite(typeMateriel, typeModif, ref, quantite, Compte.getCampus());

        call.enqueue(new Callback<SuccesRequete>() {
            @Override
            public void onResponse(Call<SuccesRequete> call, Response<SuccesRequete> response) {
                SuccesRequete result = response.body();

                if (result.isSucces()){
                    if (typeModif.equals("Ajouter")){
                        Toast toast = Toast.makeText(getApplicationContext(), "Matériel ajouté!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER, 125, 150);
                        toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Matériel retiré!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER, 125, 150);
                        toast.show();
                    }
                    retourListe = new Intent(getApplicationContext(),AccueilActivity.class);
                    startActivity(retourListe);
                    finish();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Pas assez de matériel en stock", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 125, 150);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<SuccesRequete> call, Throwable t) {

                Toast toast = Toast.makeText(getApplicationContext(), "ERROR1", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER, 125, 150);
                toast.show();
            }
        });
    }
}
