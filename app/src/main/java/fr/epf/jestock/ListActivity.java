package fr.epf.jestock;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.adapter.ListFragmentPagerAdapter;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_liste);
        toolbar.setTitle("Listes");
        setSupportActionBar(toolbar);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ListFragmentPagerAdapter(getSupportFragmentManager(), this));


        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);




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
                Intent intent2 = new Intent(this, ConnexionActivity.class);
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
