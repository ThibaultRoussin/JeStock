package fr.epf.jestock;

/*
    Nom ......... : AfficherInfoCarteEtuActivity.java
    Role ........ : Activité permettant l'affichage des informations d'un élève de l'école
    Auteur ...... : DSI_2

*/

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.Etudiant;
import fr.epf.jestock.model.ReferenceEmprunt;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AfficherInfoCarteEtuActivity extends AppCompatActivity {

    @BindView(R.id.numero_etudiant_textview)
    TextView numEtu;
    @BindView(R.id.nom_etudiant_textview)
    TextView nomPrenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficherinfocarteetu);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        //Mise en place de la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        toolbar.setTitle("Vérification");
        setSupportActionBar(toolbar);

        recupEtudiant(intent);
    }

    //Méthode d'envoie du numero étudiant vers le serveur web pour récupérer les informations étudiantes
    public void recupEtudiant(Intent intent){

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

        Call<Etudiant> call = appelBDD.recupEtudiant(intent.getStringExtra("NumEtu"));

        call.enqueue(new Callback<Etudiant>() {
            @Override
            public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                Etudiant etudiant = response.body();

                //Si étudiant présent dans la BDD, affichage de son nom et prénom
                if(etudiant.isSucces()){

                    numEtu.setText(etudiant.getNumEtu());
                    nomPrenom.setText(etudiant.getNom() + " " + etudiant.getPrenom());
                }
                //Si non
                else{
                    Toast.makeText(getApplicationContext(), "Numero étudiant incorrecte", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Etudiant> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erreur recupEtudiant", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barre_menu, menu);
        return true;
    }

    //Bouton envoyant les données étudiantes vers le serveur web afin que l'emprunt soit ajouté à la BDD
    @OnClick(R.id.bt_suivant)
    public void suivant(){

        String id[] = nomPrenom.getText().toString().split(" ");

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

        Call<Void> call = appelBDD.emprunter("etudiant", ReferenceEmprunt.getReference(),
                                                    ReferenceEmprunt.getNom(),
                                                    id[0], id[1], Compte.getCampus());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                Toast.makeText(getApplicationContext(), "Emprunt enregistré", Toast.LENGTH_SHORT).show();
                ReferenceEmprunt.setNom("");
                ReferenceEmprunt.setReference(Long.parseLong("0"));
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erreur emprunt", Toast.LENGTH_SHORT).show();
            }
        });
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
        }

        return super.onOptionsItemSelected(item);
    }
}
