package fr.epf.jestock;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.EditText;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.data.MaterielDAO;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_accueil);
        toolbar.setTitle("Scanner");
        setSupportActionBar(toolbar);

        detector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.EAN_13).build();
        cameraSource = new CameraSource
                .Builder(this, detector)
                .setRequestedPreviewSize(640, 1200)
                .build();

        //Event
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

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> code = detections.getDetectedItems();
                if (code.size() != 0){
                    Log.d("BARCODE", code.valueAt(0).displayValue);
                    MaterielDAO BDD = new MaterielDAO(getApplicationContext());

                    Intent intent = BDD.rechercheBDD(code.valueAt(0).displayValue);
                    startActivity(intent);
                }
            }
        });
    }

    @OnClick(R.id.bt_rechercher)
    public void rechercher(){
        MaterielDAO BDD = new MaterielDAO(getApplicationContext());
        Intent intent = BDD.rechercheBDD(ref.getText().toString());
        startActivity(intent);
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
                Intent intent = new Intent(this, EmpruntsActivity.class);
                //ListActivity
                startActivity(intent);
                return true;

            case R.id.action_notification:
                Intent intent1 = new Intent(this, RenduMaterielActivity.class);
                //AfficheInfoCarteEtu_Activity
                startActivity(intent1);
                return true;

            case R.id.action_deconnexion :
                Intent intent2 = new Intent(this, ConnexionActivity.class);
                //SaisieManuelleCarteEtuActivity
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
