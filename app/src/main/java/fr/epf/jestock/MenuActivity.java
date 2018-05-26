package fr.epf.jestock;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.data.UserDAO;
import fr.epf.jestock.data.UserDataBaseOpenHelper;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.ref_materiel)
    TextView reference;
    @BindView(R.id.nom_materiel)
    TextView nom;
    private Button hide1,hide2,hide3,hide4;
    private Intent recupData, retourScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        Context context = getApplicationContext();
        CharSequence text = "Le matériel a bien été reconnu!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 125, 150);
        toast.show();

        recupData = getIntent();

        Log.d("Type", recupData.getStringExtra("TYPE"));

        if (recupData.getStringExtra("TYPE").equals("Stock")){
            reference.setText(recupData.getStringExtra(UserDataBaseOpenHelper.REFERENCE));
            nom.setText(recupData.getStringExtra(UserDataBaseOpenHelper.NAME));
            hide1 = (Button)findViewById(R.id.bt_ajout_materiel_empruntable);
            hide2 = (Button)findViewById(R.id.bt_retrait_materiel_empruntable);
            hide3 = (Button)findViewById(R.id.bt_emprunt_materiel);
            hide4 = (Button)findViewById(R.id.bt_rendu_materiel);
            hide1.setVisibility(View.INVISIBLE);
            hide2.setVisibility(View.INVISIBLE);
            hide3.setVisibility(View.INVISIBLE);
            hide4.setVisibility(View.INVISIBLE);
        }
        if (recupData.getStringExtra("TYPE").equals("Empruntable")){
            reference.setText(recupData.getStringExtra(UserDataBaseOpenHelper.REFERENCE2));
            nom.setText(recupData.getStringExtra(UserDataBaseOpenHelper.NAME2));
            hide1 = (Button)findViewById(R.id.bt_ajout_materiel_stock);
            hide2 = (Button)findViewById(R.id.bt_retrait_materiel_stock);
            hide1.setVisibility(View.INVISIBLE);
            hide2.setVisibility(View.INVISIBLE);
        }
        if (recupData.getStringExtra("TYPE").equals("Double")){
            reference.setText(recupData.getStringExtra(UserDataBaseOpenHelper.REFERENCE));
            nom.setText(recupData.getStringExtra(UserDataBaseOpenHelper.NAME));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barre_menu, menu);
        return true;
    }

    @OnClick(R.id.bt_ajout_materiel_stock)
    public void ajouterMateriel(){
        MaterielDAO BDD = new MaterielDAO(this);
        recupData = getIntent();
        BDD.ajouterMateriel(recupData);

        Toast toast = Toast.makeText(getApplicationContext(), "Le matériel a bien été ajouté!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 125, 150);
        toast.show();

        retourScanner = new Intent(this,AccueilActivity.class);
        startActivity(retourScanner);
    }

    @OnClick(R.id.bt_retrait_materiel_stock)
    public void retirerMateriel(){
        MaterielDAO BDD = new MaterielDAO(this);
        recupData = getIntent();
        BDD.retirerMateriel(recupData);

        Toast toast = Toast.makeText(getApplicationContext(), "Le matériel a bien été retiré!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 125, 150);
        toast.show();

        retourScanner = new Intent(this,AccueilActivity.class);
        startActivity(retourScanner);
    }

    @OnClick(R.id.bt_emprunt_materiel)
    public void emprunterMateriel(){

    }

    @OnClick(R.id.bt_rendu_materiel)
    public void rendreMateriel(){

    }
}
