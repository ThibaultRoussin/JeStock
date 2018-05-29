package fr.epf.jestock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.data.UserDataBaseOpenHelper;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.MaterielEmpruntable;
import fr.epf.jestock.model.MaterielEnStock;

public class NouvelleRefActivity extends AppCompatActivity {

    @BindView(R.id.ref)
    TextView ref;
    @BindView(R.id.radioStock)
    RadioButton stock;
    @BindView(R.id.radioEmpruntable)
    RadioButton empruntable;
    @BindView(R.id.editNom)
    EditText nom;
    @BindView(R.id.text_quantity_add)
    TextView quantiteAdd;
    @BindView(R.id.text_quantity_conseil)
    TextView quantiteConseil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_ref);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ref.setText(intent.getStringExtra(UserDataBaseOpenHelper.REFERENCE2));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_newRef);
        toolbar.setTitle("Nouveau");
        setSupportActionBar(toolbar);

    }

    @OnTouch(R.id.retirer1Ajout)
    public boolean retirer1Ajout(){
        int quantiteActuelle = Integer.parseInt(quantiteAdd.getText().toString());
        int quantiteFuture = quantiteActuelle - 1;
        if (quantiteFuture > 1) quantiteAdd.setText(String.valueOf(quantiteFuture));
        else quantiteAdd.setText("1");
        return true;
    }

    @OnTouch(R.id.ajouter1Ajout)
    public boolean ajouter1Ajout(){
        int quantiteActuelle = Integer.parseInt(quantiteAdd.getText().toString());
        int quantiteFuture = quantiteActuelle +1;
        quantiteAdd.setText(String.valueOf(quantiteFuture));
        return true;
    }

    @OnTouch(R.id.retirer1Conseil)
    public boolean retirer1Conseil(){
        int quantiteActuelle = Integer.parseInt(quantiteConseil.getText().toString());
        int quantiteFuture = quantiteActuelle - 1;
        if (quantiteFuture > 1) quantiteConseil.setText(String.valueOf(quantiteFuture));
        else quantiteConseil.setText("1");
        return true;
    }

    @OnTouch(R.id.ajouter1Conseil)
    public boolean ajouter1Conseil(){
        int quantiteActuelle = Integer.parseInt(quantiteConseil.getText().toString());
        int quantiteFuture = quantiteActuelle +1;
        quantiteConseil.setText(String.valueOf(quantiteFuture));
        return true;
    }

    @OnClick(R.id.bt_newRefOk)
    public void validerNewRef(){
        MaterielDAO BDD = new MaterielDAO(this);
        Intent intent = new Intent(this,AccueilActivity.class);
        Intent intent2 = getIntent();
        String reference = intent2.getStringExtra(UserDataBaseOpenHelper.REFERENCE2);
        String name = nom.getText().toString();
        int quantiteStock = Integer.parseInt(quantiteAdd.getText().toString());
        int quantiteConseillee = Integer.parseInt(quantiteConseil.getText().toString());
        int quantiteACommander = 0;

        if (quantiteStock < quantiteConseillee) {
            quantiteACommander = quantiteConseillee - quantiteStock;
        }

        if (stock.isChecked()){
            MaterielEnStock materiel = new MaterielEnStock(reference, name, quantiteStock, quantiteConseillee, quantiteACommander);
            BDD.ajouterReferenceStock(materiel);
            startActivity(intent);
        }
        if (empruntable.isChecked()){
            MaterielEmpruntable materiel = new MaterielEmpruntable(reference, name, quantiteStock, 0, quantiteStock, quantiteConseillee, quantiteACommander);
            BDD.ajouterReferenceEmpruntable(materiel);
            startActivity(intent);
        }

        Toast toast = Toast.makeText(getApplicationContext(), "Choisissez une liste de matériel", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 125, 150);
        toast.show();
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
