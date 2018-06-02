package fr.epf.jestock.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fr.epf.jestock.adapter.ListAdapterMaterielEnStock;
import fr.epf.jestock.adapter.ListAdapterMaterielEnStockDeficit;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.model.MaterielEnStock;

import static android.content.ContentValues.TAG;

/**
 * Created by Utilisateur on 25/05/2018.
 */

public class ListMaterielEnStockDeficitFragment extends ListFragment {

    public ListMaterielEnStockDeficitFragment() {
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

        MaterielDAO materielDAO = new MaterielDAO(getContext());
        List<MaterielEnStock> materielEnStock = materielDAO.rechercheBDDStock();
        ListAdapterMaterielEnStockDeficit adapter = new ListAdapterMaterielEnStockDeficit(getActivity(), materielEnStock);
        setListAdapter(adapter);

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener!=null) {
            listener.onMaterielSelected(position);
        }
    }
}
