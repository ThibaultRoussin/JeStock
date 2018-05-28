package fr.epf.jestock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
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

        if (Compte.getDroit().equals("Admin")){
            textCompte.setText("Voulez vous ajouter une nouvelle référence aux stocks?");
        }
        if (Compte.getDroit().equals("SI")){
            textCompte.setText("Si vous souhaitez ajouter cette nouvelle référence aux stocks, connectez vous en tant que Administrateur.");

        }
        Intent intent = getIntent();


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
