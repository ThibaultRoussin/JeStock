package fr.epf.jestock;

/*
    Nom ......... : RefInconnue.java
    Role ........ : Activité informant l'utilisateur de la détection d'un nouveau matériel
    Auteur ...... : DSI_2

*/

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

        Intent intent = getIntent();

        //Si admin, changement du text a à afficher
        if (Compte.getStatut().equals("admin")){
            textCompte.setText("Voulez vous ajouter cette nouvelle référence '" +
                                intent.getLongExtra("Reference", 0) +
                                "' aux stocks?");
        }
        //Si membre, changement du text a à afficher
        if (Compte.getStatut().equals("membre")){
            textCompte.setText("Si vous souhaitez ajouter cette nouvelle référence '" +
                    intent.getLongExtra("Reference",0) +
                    "' aux stocks, connectez vous en tant qu'Administrateur.");
            retry.setText("SE CONNECTER EN TANT QU'ADMINISTRATEUR");
        }

        //Mise en place de la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_refInconnue);
        toolbar.setTitle("Inconnue");
        setSupportActionBar(toolbar);
    }

    //Bouton permettant de retourner au scanner
    @OnClick(R.id.bt_retry)
    public void retry(){
        finish();
    }


    @OnClick(R.id.bt_newRef)
    public void newRef(){
        //Si membre, retour à la page de connexion
        if(Compte.getStatut().equals("membre")){
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
            preferences.edit().clear().apply();
            Intent intent2 = new Intent(this, ConnexionActivity.class);
            startActivity(intent2);
        }
        // Si admin, lancement de l'activité RefInconnueActivity.java
        else {
            Intent intent = getIntent();
            long ref = intent.getLongExtra("Reference",0);
            Log.d("REF INCONNUE", String.valueOf(ref));
            Intent intent2 = new Intent(this, NouvelleRefActivity.class);
            intent2.putExtra("Reference", ref);
            startActivity(intent2);
        }
    }

    //Si retour en arrière, destruction de l'activité
    @Override
    protected void onPause() {
        super.onPause();
        finish();
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
}
