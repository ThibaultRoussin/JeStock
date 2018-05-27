package fr.epf.jestock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_liste);
        toolbar.setTitle("Listes");
        setSupportActionBar(toolbar);

        TabHost tabHost;
        TabHost host = (TabHost)findViewById(R.id.tab_host);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Matériel en stock");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Matériel en stock");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Matériel empruntable");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Matériel empruntable");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Emprunts");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Emprunts");
        host.addTab(spec);

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

    @OnClick(R.id.bt_retour_accueil)
    public void retourAccueil(){
        Intent intent = new Intent(this,AccueilActivity.class);
        startActivity(intent);
    }
}
