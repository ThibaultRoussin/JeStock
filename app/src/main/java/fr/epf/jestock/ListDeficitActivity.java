package fr.epf.jestock;

/*
    Nom ......... : ListDeficit.java
    Role ........ : Activité affichant le contenu des listes de stocks en déficits
    Auteur ...... : DSI_2

*/

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.adapter.ListDeficitFragmentPagerAdapter;
import fr.epf.jestock.adapter.ListMaterielFragmentPagerAdapter;
import fr.epf.jestock.model.Compte;

public class ListDeficitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_deficit);
        ButterKnife.bind(this);

        //Mise en place de la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_listeDeficit);
        toolbar.setTitle("Deficit");
        setSupportActionBar(toolbar);

        //Mise en place des onglets de navigation
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_deficit);
        viewPager.setAdapter(new ListDeficitFragmentPagerAdapter(getSupportFragmentManager()));


        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs_deficit);
        tabLayout.setupWithViewPager(viewPager);
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

    //Bouton de retour au scanner
    @OnClick(R.id.bt_retour_accueil)
    public void retourAccueil(){
        Intent intent = new Intent(this,AccueilActivity.class);
        startActivity(intent);
    }

    //Retour arrière désactivé pour empécher de revenir à l'activité MenuActivity.java
    @Override
    public void onBackPressed(){
        return ;
    }
}
