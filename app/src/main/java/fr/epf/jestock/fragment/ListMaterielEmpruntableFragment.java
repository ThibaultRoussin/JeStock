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
import fr.epf.jestock.adapter.ListAdapterMaterielEmpruntable;
import fr.epf.jestock.adapter.ListAdapterMaterielEnStock;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.MaterielEmpruntable;
import fr.epf.jestock.model.MaterielEnStock;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
    Nom ......... : ListMaterielEmpruntableFragment.java
    Role ........ : Fragment permettant l'affichage de la liste de materiel empruntable
    Auteur ...... : DSI_2

*/

public class ListMaterielEmpruntableFragment extends ListFragment {

    public interface OnMaterielSelectedListener {
        void onMaterielSelected(int id);
    }

    public ListMaterielEmpruntableFragment() {
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

    //Chargement de la liste depuis le serveur WEB
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

        Call<List<MaterielEmpruntable>> call = appelBDD.affichageListeEmpruntable(Compte.getCampus());

        call.enqueue(new Callback<List<MaterielEmpruntable>>() {
            @Override
            public void onResponse(Call<List<MaterielEmpruntable>> call, Response<List<MaterielEmpruntable>> response) {

                List<MaterielEmpruntable> materielEmpruntable  = response.body();
                ListAdapterMaterielEmpruntable adapter = new ListAdapterMaterielEmpruntable(getActivity(), materielEmpruntable);
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<MaterielEmpruntable>> call, Throwable t) {

                Toast.makeText(getContext(), "Erreur chargement liste du matériel empruntable", Toast.LENGTH_SHORT).show();
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
