package fr.epf.jestock.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.epf.jestock.adapter.ListAdapterMaterielEmpruntable;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.model.MaterielEmpruntable;

/**
 * Created by Utilisateur on 25/05/2018.
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

        MaterielDAO materielDAO = new MaterielDAO(getContext());
        List<MaterielEmpruntable> materielEmpruntable  = new ArrayList<>();
        ListAdapterMaterielEmpruntable adapter = new ListAdapterMaterielEmpruntable(getActivity(), materielEmpruntable);
        setListAdapter(adapter);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener!=null) {
            listener.onMaterielSelected(position);
        }
    }
}