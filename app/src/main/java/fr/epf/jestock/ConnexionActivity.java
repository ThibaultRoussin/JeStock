package fr.epf.jestock;

/*
    Nom ......... : Connexion.java
    Role ........ : Activité controllant la connexion utilisateur
    Auteur ...... : DSI_2

*/

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.User;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnexionActivity extends AppCompatActivity {

    @BindView(R.id.userId) EditText id;
    @BindView(R.id.userMDP) EditText mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        ButterKnife.bind(this);

        // Si données utilisateurs dans le cache, enregistrement du campus et statut,
        // et chargement de l'activité AccueilActivity.java
        SharedPreferences preference = getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
        if (!preference.getString("Email","null").equals("null")){
            User user = new User();
            user.setFirstName(preference.getString("Prenom",""));
            user.setLastName(preference.getString("Nom",""));
            user.setEmail(preference.getString("Email",""));
            user.setStatut(preference.getString("Statut",""));
            user.setCampus(preference.getString("Campus",""));

            Compte.setStatut(user.getStatut());
            Compte.setCampus(user.getCampus());

            Intent intent = new Intent(this,AccueilActivity.class);
            startActivity(intent);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_connexion);
        toolbar.setTitle("Connexion");
        setSupportActionBar(toolbar);
    }

    //Utilisateur clique sur le bouton de validation
    @OnClick(R.id.connexion)
    public void connexion(){

        //Envoie de l'email et du mot de passe vers le serveur web
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_connexion))  //Ip machine locale, a definir dans la ressource String ip_connexion
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        IAppelBDD appelBDD = retrofit.create(IAppelBDD.class);

        Call<User> call = appelBDD.sendUser(id.getText().toString(),mdp.getText().toString());

        call.enqueue(new Callback<User>() {

            //Reponse recut
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                //Si email et mot de passe corrects, enregistrement des données dans le cache
                //enregistrement du campus et statut, et chargement de l'activité AccueilActivity.java
                if (user.isSucces()){

                    SharedPreferences preference = getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                    preference.edit().putString("Prenom",user.getFirstName()).apply();
                    preference.edit().putString("Nom",user.getLastName()).apply();
                    preference.edit().putString("Email",id.getText().toString()).apply();
                    preference.edit().putString("Statut",user.getStatut()).apply();
                    preference.edit().putString("Campus",user.getCampus()).apply();

                    Toast toast = Toast.makeText(getApplicationContext(), "Bienvenue " + user.getFirstName() + " " +user.getLastName(), Toast.LENGTH_SHORT);
                    toast.show();

                    Compte.setStatut(user.getStatut());
                    Compte.setCampus(user.getCampus());

                    Intent intent = new Intent(getApplicationContext(),AccueilActivity.class);
                    startActivity(intent);
                }

                //Si non
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Email ou mot de passe incorrect", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 125, 150);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Reponse", "Error");
            }
        });


    }

    //Empeche le retour en arrière apres une deconnexion
    @Override
    public void onBackPressed(){
        return ;
    }


}
