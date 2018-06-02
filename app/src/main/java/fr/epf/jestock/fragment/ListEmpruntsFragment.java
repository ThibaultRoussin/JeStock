package fr.epf.jestock.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.epf.jestock.R;
import fr.epf.jestock.adapter.ListAdapterEmprunts;
import fr.epf.jestock.adapter.ListAdapterMaterielEmpruntable;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.Emprunts;
import fr.epf.jestock.model.MaterielEmpruntable;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Utilisateur on 25/05/2018.
 */

public class ListEmpruntsFragment extends ListFragment {

    public ListEmpruntsFragment() {
    }

    public interface OnMaterielSelectedListener {
        void onMaterielSelected(int id);
    }

    private OnMaterielSelectedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity()instanceof OnMaterielSelectedListener) {
            listener = (OnMaterielSelectedListener) getActivity();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chargerListe();
    }

    private void chargerListe() {

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

        Call<List<Emprunts>> call = appelBDD.affichageListeEmprunt(Compte.getCampus());

        call.enqueue(new Callback<List<Emprunts>>() {
            @Override
            public void onResponse(Call<List<Emprunts>> call, Response<List<Emprunts>> response) {

                List<Emprunts> emprunt  = response.body();
                ListAdapterEmprunts adapter = new ListAdapterEmprunts(getActivity(), emprunt);
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Emprunts>> call, Throwable t) {

                Toast.makeText(getContext(), "Erreur chargement liste des emprunts", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener!=null) {
            listener.onMaterielSelected(position);
        }
    }
}
