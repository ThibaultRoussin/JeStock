package fr.epf.jestock;

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

     static final String EMAIL = "EMAIL";
     static final String MDP = "MDP";

    @BindView(R.id.userId) EditText id;
    @BindView(R.id.userMDP) EditText mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        ButterKnife.bind(this);

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

    @OnClick(R.id.connexion)
    public void connexion(){

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

        Call<User> call = appelBDD.sendUser(id.getText().toString(),mdp.getText().toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                if (user.isSucces()){

                    SharedPreferences preference = getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                    preference.edit().putString("Prenom",user.getFirstName()).apply();
                    preference.edit().putString("Nom",user.getLastName()).apply();
                    preference.edit().putString("Email",id.getText().toString()).apply();
                    preference.edit().putString("Statut",user.getStatut()).apply();
                    preference.edit().putString("Campus",user.getCampus()).apply();

                    Toast toast = Toast.makeText(getApplicationContext(), "Bienvenue " + user.getFirstName() + " " +user.getLastName(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 125, 150);
                    toast.show();

                    Compte.setStatut(user.getStatut());
                    Compte.setCampus(user.getCampus());

                    Intent intent = new Intent(getApplicationContext(),AccueilActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Email ou mot de passe incorrecte", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 125, 150);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Reponse", "Error");
            }
        });

        /*UserDAO BDD = new UserDAO(this);
        List<User> userList = BDD.connexion();
        Intent intent = new Intent(this,AccueilActivity.class);

        for (User user : userList) {
            if (id.getText().toString().equals(user.getLogin()) && mdp.getText().toString().equals(user.getPassword())){
                Compte.setCampus("Sceaux");
                Compte.setDroit(user.getDroit());
                SharedPreferences preference = getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
                preference.edit().putString("Id",user.getLogin()).apply();
                preference.edit().putString("Mdp",user.getPassword()).apply();
                preference.edit().putString("Droit",user.getDroit()).apply();
                preference.edit().putString("Campus","Sceaux").apply();
                startActivity(intent);
            }
        }*/
    }

    @OnClick(R.id.oubliMDP)
    public void oubliMDP(){
        Toast toast = Toast.makeText(getApplicationContext(), "Work in progress!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 125, 150);
        toast.show();
    }

    @Override
    public void onBackPressed(){
        return ;
    }


}
