package fr.epf.jestock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.adapter.ListAdapterEmprunts;
import fr.epf.jestock.fragment.ListRenduFragment;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.Emprunts;
import fr.epf.jestock.model.ReferenceEmprunt;
import fr.epf.jestock.model.SuccesRequete;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ValidationRenduActivity extends AppCompatActivity {

    @BindView(R.id.nomEmprunteur)
    TextView nomEmprunteur;
    @BindView(R.id.prenomEmprunteur)
    TextView prenomEmprunteur;
    @BindView(R.id.nomMateriel)
    TextView nomMateriel;
    @BindView(R.id.refMateriel)
    TextView refMateriel;
    @BindView(R.id.dateEmprunt)
    TextView dateEmprunt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_rendu);
        ButterKnife.bind(this);

        //Mise en place de la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_validation_rendu);
        toolbar.setTitle("Validation");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Emprunts emprunt = ListRenduFragment.empruntList2.get((int)intent.getLongExtra("Position", 0L));

        nomEmprunteur.setText(emprunt.getNomEmprunteur());
        prenomEmprunteur.setText(emprunt.getPrenomEmprunteur());
        nomMateriel.setText(emprunt.getNomRef());
        refMateriel.setText(String.valueOf(emprunt.getReference()));
        dateEmprunt.setText(emprunt.getDateEmprunt());

        ListRenduFragment.empruntList2.clear();
    }

    @OnClick(R.id.bt_valider)
    public void validerRendu(){

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

        Call<Void> call = appelBDD.rendre(Long.parseLong(refMateriel.getText().toString()), dateEmprunt.getText().toString());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                ReferenceEmprunt.setReference(0L);
                ReferenceEmprunt.setNom("");
                Toast.makeText(getApplicationContext(), "Materiel rendu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erreur", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
