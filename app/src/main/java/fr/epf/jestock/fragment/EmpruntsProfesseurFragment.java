package fr.epf.jestock.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.epf.jestock.ListActivity;
import fr.epf.jestock.R;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.ReferenceEmprunt;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpruntsProfesseurFragment extends Fragment {

    @BindView(R.id.nom_professeur_editText)
    EditText nom;
    @BindView(R.id.prenom_professeur_editText)
    EditText prenom;

    public EmpruntsProfesseurFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emprunts_professeur, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.bt_valider)
    public void suivant(){

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

        Call<Void> call = appelBDD.emprunter("professeur", ReferenceEmprunt.getReference(),
                ReferenceEmprunt.getNom(),
                nom.getText().toString(), prenom.getText().toString(), Compte.getCampus());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Intent intent = new Intent(getContext(),ListActivity.class);
                Toast.makeText(getContext(), "Emprunt enregistré", Toast.LENGTH_SHORT).show();
                ReferenceEmprunt.setNom("");
                ReferenceEmprunt.setReference(Long.parseLong("0"));
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getContext(), "Erreur emprunt", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
