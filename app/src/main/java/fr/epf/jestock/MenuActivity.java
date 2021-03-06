package fr.epf.jestock;

/*
    Nom ......... : Menu.java
    Role ........ : Activité affichant les possibles actions utilisateurs après identification d'un matériel
    Auteur ...... : DSI_2

*/

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Space;
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

    private boolean boutonRelache = true;

    private Button hide1,hide2,hide3,hide4;
    private Intent recupData, retourListe;
    private Space space1,space2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        //Mise en place de la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        Context context = getApplicationContext();
        CharSequence text = "Le matériel a bien été reconnu!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //Recuperation de la reference et du nom du materiel scanner
        recupData = getIntent();

        long ref = recupData.getLongExtra("Reference",0);
        String name = recupData.getStringExtra("Nom");

        reference.setText(String.valueOf(ref));
        nom.setText(name);

        Log.d("Type", recupData.getStringExtra("Type"));

        //Affichade des boutons concernés
        if (recupData.getStringExtra("Type").equals("Stock")){
            hide1 = (Button)findViewById(R.id.bt_ajout_materiel_empruntable);
            hide2 = (Button)findViewById(R.id.bt_retrait_materiel_empruntable);
            hide3 = (Button)findViewById(R.id.bt_emprunt_materiel);
            hide4 = (Button)findViewById(R.id.bt_rendu_materiel);
            hide1.setVisibility(View.GONE);
            hide2.setVisibility(View.GONE);
            hide3.setVisibility(View.GONE);
            hide4.setVisibility(View.GONE);
        }
        if (recupData.getStringExtra("Type").equals("Empruntable")){
            hide1 = (Button)findViewById(R.id.bt_ajout_materiel_stock);
            hide2 = (Button)findViewById(R.id.bt_retrait_materiel_stock);
            space1 = (Space)findViewById(R.id.spaceAjout);
            space2 = (Space)findViewById(R.id.spaceRetrait);
            hide1.setVisibility(View.GONE);
            hide2.setVisibility(View.GONE);
            space1.setVisibility(View.GONE);
            space2.setVisibility(View.GONE);
        }
    }

    //Réduction de la quantité par 1
    @OnTouch(R.id.retirer1)
    public boolean retirer1(){
        if (boutonRelache) {
            int quantiteActuelle = Integer.parseInt(quantite.getText().toString());
            int quantiteFuture = quantiteActuelle - 1;
            if (quantiteFuture > 1) quantite.setText(String.valueOf(quantiteFuture));
            else quantite.setText("1");
            boutonRelache = false;
            return true;
        }
        else boutonRelache = true;
        return true;
    }

    //Augmentation de la quantité par 1
    @OnTouch(R.id.ajouter1)
    public boolean ajouter1(){
        if (boutonRelache) {
            int quantiteActuelle = Integer.parseInt(quantite.getText().toString());
            int quantiteFuture = quantiteActuelle + 1;
            quantite.setText(String.valueOf(quantiteFuture));
            boutonRelache = false;
            return true;
        }
        else boutonRelache = true;
        return true;
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

    //Méthode d'ajout du matériel au stock
    @OnClick(R.id.bt_ajout_materiel_stock)
    public void ajouterMateriel(){

        modifierQuantite("Stock", "Ajouter",
                        Long.parseLong(reference.getText().toString()),
                        Integer.parseInt(quantite.getText().toString()));

    }

    //Méthode de retrait du matériel du stock
    @OnClick(R.id.bt_retrait_materiel_stock)
    public void retirerMateriel(){

        modifierQuantite("Stock", "Retirer",
                Long.parseLong(reference.getText().toString()),
                Integer.parseInt(quantite.getText().toString()));
    }

    //Méthode d'ajout du matériel au stock empruntable
    @OnClick(R.id.bt_ajout_materiel_empruntable)
    public void ajouterMaterielEmpruntable(){

        modifierQuantite("Empruntable", "Ajouter",
                Long.parseLong(reference.getText().toString()),
                Integer.parseInt(quantite.getText().toString()));
    }

    //Méthode de retrait du matériel au stock empruntable
    @OnClick(R.id.bt_retrait_materiel_empruntable)
    public void retirerMaterielEmpruntable(){

        modifierQuantite("Empruntable", "Retirer",
                Long.parseLong(reference.getText().toString()),
                Integer.parseInt(quantite.getText().toString()));
    }

    //Méthode d'emprunt d'un matériel
    @OnClick(R.id.bt_emprunt_materiel)
    public void emprunterMateriel(){

        Intent intent = new Intent(this,EmpruntsActivity.class);
        ReferenceEmprunt.setReference(Long.parseLong(reference.getText().toString()));
        ReferenceEmprunt.setNom(nom.getText().toString());
        startActivity(intent);
    }

    //Méthode de rendu d'un matériel
    @OnClick(R.id.bt_rendu_materiel)
    public void rendreMateriel(){

        Intent intent = new Intent(this,RenduActivity.class);
        ReferenceEmprunt.setReference(Long.parseLong(reference.getText().toString()));
        ReferenceEmprunt.setNom(nom.getText().toString());
        startActivity(intent);

    }

    //Méthode d'envoie des données du matériel vers le erveur web
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

                //Si succes, retour à l'activité ListActivity.java
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

                //Si non
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Pas assez de matériel en stock", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 125, 150);
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
