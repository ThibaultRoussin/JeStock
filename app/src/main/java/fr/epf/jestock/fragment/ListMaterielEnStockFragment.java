package fr.epf.jestock.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.epf.jestock.ListActivity;
import fr.epf.jestock.R;
import fr.epf.jestock.adapter.ListAdapterMaterielEnStock;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.MaterielEnStock;
import fr.epf.jestock.model.ReferenceEmprunt;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Utilisateur on 21/05/2018.
 */

public class ListMaterielEnStockFragment extends ListFragment {

    public interface OnMaterielSelectedListener {
        void onMaterielSelected(int id);
    }

    public ListMaterielEnStockFragment() {
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

        Call<List<MaterielEnStock>> call = appelBDD.affichageListeStock(Compte.getCampus());

        call.enqueue(new Callback<List<MaterielEnStock>>() {
            @Override
            public void onResponse(Call<List<MaterielEnStock>> call, Response<List<MaterielEnStock>> response) {

                List<MaterielEnStock> materielEnStock  = response.body();
                ListAdapterMaterielEnStock adapter = new ListAdapterMaterielEnStock(getActivity(), materielEnStock);
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<MaterielEnStock>> call, Throwable t) {

                Toast.makeText(getContext(), "Erreur chargement liste des stocks", Toast.LENGTH_SHORT).show();
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
