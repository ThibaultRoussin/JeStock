package fr.epf.jestock.fragment;

/*
    Nom ......... : ListEmpruntableDeficitFragment.java
    Role ........ : Fragment permettant l'affichage de la liste de matériel empruntable en deficit
    Auteur ...... : DSI_2

*/

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.epf.jestock.R;
import fr.epf.jestock.adapter.ListAdapterMaterielDeficit;
import fr.epf.jestock.model.Compte;
import fr.epf.jestock.model.MaterielDeficit;
import fr.epf.jestock.service.IAppelBDD;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListEmpruntableDeficitFragment extends ListFragment {



    public ListEmpruntableDeficitFragment() {
    }

    public interface OnMaterielEmpruntableDeficitListener {
        void onMaterielSelected(int id);
    }

    private OnMaterielEmpruntableDeficitListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity()instanceof ListEmpruntableDeficitFragment.OnMaterielEmpruntableDeficitListener) {
            listener = (ListEmpruntableDeficitFragment.OnMaterielEmpruntableDeficitListener) getActivity();
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

        Call<List<MaterielDeficit>> call = appelBDD.affichageListeDeficit("empruntable",Compte.getCampus());

        call.enqueue(new Callback<List<MaterielDeficit>>() {
            @Override
            public void onResponse(Call<List<MaterielDeficit>> call, Response<List<MaterielDeficit>> response) {

                List<MaterielDeficit> listDeficit  = response.body();
                ListAdapterMaterielDeficit adapter = new ListAdapterMaterielDeficit(getActivity(), listDeficit);
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<MaterielDeficit>> call, Throwable t) {

                List<MaterielDeficit> listDeficit = new ArrayList<>();
                ListAdapterMaterielDeficit adapter = new ListAdapterMaterielDeficit(getActivity(), listDeficit);
                setListAdapter(adapter);

                Toast.makeText(getContext(), "Aucun matériel empruntable en déficit", Toast.LENGTH_SHORT).show();
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
