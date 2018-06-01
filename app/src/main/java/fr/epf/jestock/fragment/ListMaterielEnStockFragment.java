package fr.epf.jestock.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.epf.jestock.adapter.ListAdapterMaterielEnStock;
import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.model.MaterielEnStock;

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

        MaterielDAO materielDAO = new MaterielDAO(getContext());
        List<MaterielEnStock> materielEnStock  = new ArrayList<>();
        ListAdapterMaterielEnStock adapter = new ListAdapterMaterielEnStock(getActivity(), materielEnStock);
        setListAdapter(adapter);
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener!=null) {
            listener.onMaterielSelected(position);
        }
    }



}
