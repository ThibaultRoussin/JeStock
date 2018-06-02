package fr.epf.jestock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import fr.epf.jestock.fragment.ListEmpruntsFragment;

public class RenduActivity extends AppCompatActivity implements ListEmpruntsFragment.OnMaterielSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendu);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_rendu);
        toolbar.setTitle("Rendu");
        setSupportActionBar(toolbar);
    }

    @Override
    public void onMaterielSelected(int id) {

    }
}
