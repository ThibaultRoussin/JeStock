package fr.epf.jestock.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import fr.epf.jestock.adapter.ListAdapterMaterielEnStock;
import fr.epf.jestock.adapter.ListAdapterRenduMateriel;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.model.Emprunts;

/**
 * Created by Utilisateur on 01/06/2018.
 */

public class ListRenduMaterielFragment extends ListFragment {

    public interface OnMaterielSelectedListener {
        void onMaterielSelected(int id);
    }

    public ListRenduMaterielFragment() {
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
        List<Emprunts> rendumateriel = materielDAO.rechercheBDDEmprunts();
        ListAdapterRenduMateriel adapter = new ListAdapterRenduMateriel(getActivity(), rendumateriel);
        setListAdapter(adapter);
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener!=null) {
            listener.onMaterielSelected(position);
        }
    }

}
