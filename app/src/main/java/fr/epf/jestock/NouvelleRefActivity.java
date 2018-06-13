package fr.epf.jestock;

/*
    Nom ......... : NouvelleRef.java
    Role ........ : Activité permettant à l'utilisateur de saisir les informations d'un nouveau matériel à ajouter aux stocks
    Auteur ...... : DSI_2

*/

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import fr.epf.jestock.adapter.ListAdapterEmprunts;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.data.UserDataBaseOpenHelper;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.Emprunts;
import fr.epf.jestock.model.MaterielEmpruntable;
import fr.epf.jestock.model.MaterielEnStock;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private boolean boutonRelache = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_ref);
        ButterKnife.bind(this);

        //Recuperation de la référence matériel
        Intent intent = getIntent();
        ref.setText(String.valueOf(intent.getLongExtra("Reference",0)));

        //Mise en place de la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_newRef);
        toolbar.setTitle("Nouveau");
        setSupportActionBar(toolbar);

    }

    //Retirer 1 à la quantité de matériel ajoutée
    @OnTouch(R.id.retirer1Ajout)
    public boolean retirer1Ajout(){
        if (boutonRelache) {
            int quantiteActuelle = Integer.parseInt(quantiteAdd.getText().toString());
            int quantiteFuture = quantiteActuelle - 1;
            if (quantiteFuture > 1) quantiteAdd.setText(String.valueOf(quantiteFuture));
            else quantiteAdd.setText("1");
            boutonRelache = false;
            return true;
        }
        else boutonRelache = true;
        return true;
    }

    //Ajouter 1 à la quantité de matériel ajoutée
    @OnTouch(R.id.ajouter1Ajout)
    public boolean ajouter1Ajout(){
        if (boutonRelache) {
            int quantiteActuelle = Integer.parseInt(quantiteAdd.getText().toString());
            int quantiteFuture = quantiteActuelle + 1;
            quantiteAdd.setText(String.valueOf(quantiteFuture));
            boutonRelache = false;
            return true;
        }
        else boutonRelache = true;
        return true;
    }

    //Retirer 1 à la quantité de matériel conseillée
    @OnTouch(R.id.retirer1Conseil)
    public boolean retirer1Conseil(){
        if (boutonRelache) {
            int quantiteActuelle = Integer.parseInt(quantiteConseil.getText().toString());
            int quantiteFuture = quantiteActuelle - 1;
            if (quantiteFuture > 1) quantiteConseil.setText(String.valueOf(quantiteFuture));
            else quantiteConseil.setText("1");
            boutonRelache = false;
            return true;
        }
        else boutonRelache = true;
        return true;
    }

    //Ajouter à la quantité de matériel Conseillée
    @OnTouch(R.id.ajouter1Conseil)
    public boolean ajouter1Conseil(){
        if (boutonRelache) {
            int quantiteActuelle = Integer.parseInt(quantiteConseil.getText().toString());
            int quantiteFuture = quantiteActuelle + 1;
            quantiteConseil.setText(String.valueOf(quantiteFuture));
            boutonRelache = false;
            return true;
        }
        else boutonRelache = true;
        return true;
    }

    //Validation et envoie des informations saisies vers le serveur web
    @OnClick(R.id.bt_newRefOk)
    public void validerNewRef(){

        String liste = "";
        String nomRef = nom.getText().toString();
        long reference = Long.parseLong(ref.getText().toString());
        int quantiteAjoutee = Integer.parseInt(quantiteAdd.getText().toString());
        int quantiteConseillee = Integer.parseInt(quantiteConseil.getText().toString());

        if (stock.isChecked()) liste = "stock";
        else if (empruntable.isChecked()) liste = "empruntable";
        else Toast.makeText(this, "Choisissez une liste", Toast.LENGTH_SHORT).show();

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

        Call<Void> call = appelBDD.nouvelleRef(liste,reference,nomRef,quantiteAjoutee,quantiteConseillee,Compte.getCampus());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Toast.makeText(getApplicationContext(), "Matériel ajouté au stock", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erreur ajout d'une nouvelle référence", Toast.LENGTH_SHORT).show();
            }
        });
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


}
