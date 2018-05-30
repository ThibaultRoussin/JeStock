package fr.epf.jestock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.model.Compte;

public class RefInconnueActivity extends AppCompatActivity {

    @BindView(R.id.textCompte)
    TextView textCompte;
    @BindView(R.id.bt_retry)
    Button retry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ref_inconnue);
        ButterKnife.bind(this);

        if (Compte.getDroit().equals("admin")){
            textCompte.setText("Voulez vous ajouter une nouvelle référence aux stocks?");
        }
        if (Compte.getDroit().equals("si")){
            textCompte.setText("Si vous souhaitez ajouter cette nouvelle référence aux stocks, connectez vous en tant que Administrateur.");
            retry.setText("SE CONNECTER EN TANT QU'ADMINISTRATEUR");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_refInconnue);
        toolbar.setTitle("Inconnue");
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.bt_retry)
    public void retry(){
        finish();
    }

    @OnClick(R.id.bt_newRef)
    public void newRef(){
        if(Compte.getDroit().equals("SI")){
            Intent intent2 = new Intent(this, ConnexionActivity.class);
            startActivity(intent2);
        }
        else {
            Intent intent = getIntent();
            String ref = intent.getStringExtra("REFERENCE");
            Log.d("REF INCONNUE", ref);
            Intent intent2 = new Intent(this, NouvelleRefActivity.class);
            intent2.putExtra("REFERENCE", ref);
            startActivity(intent2);
        }
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

            case R.id.action_sceaux:
                Compte.setCampus("Sceaux");
                return true;

            case R.id.action_montpellier:
                Compte.setCampus("montpellier");
                return true;

            case R.id.action_troyes:
                Compte.setCampus("troyes");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
