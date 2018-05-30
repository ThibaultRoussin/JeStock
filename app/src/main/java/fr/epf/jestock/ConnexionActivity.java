package fr.epf.jestock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.data.UserDAO;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.User;

public class ConnexionActivity extends AppCompatActivity {

    @BindView(R.id.userId) EditText id;
    @BindView(R.id.userMDP) EditText mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        ButterKnife.bind(this);

        SharedPreferences preference = getApplicationContext().getSharedPreferences("Pref",MODE_PRIVATE);
        if (!preference.getString("Id","null").equals("null") && !preference.getString("Mdp","null").equals("null")){
            Compte.setCampus(preference.getString("Campus","Sceaux"));
            Compte.setDroit(preference.getString("Droit","si"));
            Intent intent = new Intent(this,AccueilActivity.class);
            startActivity(intent);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_connexion);
        toolbar.setTitle("Connection");
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.connexion)
    public void connexion(){

        UserDAO BDD = new UserDAO(this);
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
        }
    }

    @OnClick(R.id.oubliMDP)
    public void oubliMDP(){
        Toast toast = Toast.makeText(getApplicationContext(), "Work in progress!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 125, 150);
        toast.show();
    }

    @Override
    public void onBackPressed(){
        return ;
    }


}
