package fr.epf.jestock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.epf.jestock.R;
import fr.epf.jestock.model.Emprunts;

/**
 * Created by Utilisateur on 01/06/2018.
 */

public class ListAdapterRenduMateriel extends ArrayAdapter<Emprunts>{

    public ListAdapterRenduMateriel(Context context, List<Emprunts> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_rendumateriel_view,parent, false);
        }


        return convertView;
    }
}
