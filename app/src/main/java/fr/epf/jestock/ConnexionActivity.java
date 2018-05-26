package fr.epf.jestock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.data.UserDAO;
import fr.epf.jestock.model.User;

public class ConnexionActivity extends AppCompatActivity {

    @BindView(R.id.userId) EditText id;
    @BindView(R.id.userMDP) EditText mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        ButterKnife.bind(this);
        UserDAO BDD = new UserDAO(this);
        MaterielDAO DD = new MaterielDAO(this);
        BDD.create();
        DD.create2();
        DD.create3();
    }

    @OnClick(R.id.connexion)
    public void connexion(){

        UserDAO BDD = new UserDAO(this);
        List<User> userList = BDD.connexion();
        Intent intent = new Intent(this,AccueilActivity.class);

        for (User user : userList) {
            if (id.getText().toString().equals(user.getLogin()) && mdp.getText().toString().equals(user.getPassword())){
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


}
