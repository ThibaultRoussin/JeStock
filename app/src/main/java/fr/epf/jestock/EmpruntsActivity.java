package fr.epf.jestock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import fr.epf.jestock.adapter.EmpruntsEleveFragmentPagerAdapter;

/**
 * Created by Utilisateur on 31/05/2018.
 */

public class EmpruntsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_emprunts);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_emprunts);
        toolbar.setTitle("Emprunts");
        setSupportActionBar(toolbar);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_emprunts);
        viewPager.setAdapter(new EmpruntsEleveFragmentPagerAdapter(getSupportFragmentManager(), this));


        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs_emprunts);
        tabLayout.setupWithViewPager(viewPager);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barre_menu, menu);
        return true;
    }
}

