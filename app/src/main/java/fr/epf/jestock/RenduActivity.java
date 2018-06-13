package fr.epf.jestock;

/*
    Nom ......... : RenduActivity.java
    Role ........ : Activité controllant Le rendu d'un matériel
    Auteur ...... : DSI_2
    Note ........ : Work In Progress

*/

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import fr.epf.jestock.fragment.ListEmpruntsFragment;
import fr.epf.jestock.fragment.ListRenduFragment;

public class RenduActivity extends AppCompatActivity implements ListRenduFragment.OnMaterielSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendu);
        ButterKnife.bind(this);

        //Mise en place de la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_rendu);
        toolbar.setTitle("Rendu");
        setSupportActionBar(toolbar);
    }

    @Override
    public void onMaterielSelected(int id) {
        Intent intent = new Intent(this, ValidationRenduActivity.class);
        intent.putExtra("Position", id);
        startActivity(intent);
    }
}
