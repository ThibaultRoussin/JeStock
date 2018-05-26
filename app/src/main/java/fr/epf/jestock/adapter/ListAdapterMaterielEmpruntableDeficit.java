package fr.epf.jestock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.epf.jestock.R;
import fr.epf.jestock.model.MaterielEmpruntable;

/**
 * Created by Utilisateur on 25/05/2018.
 */

public class ListAdapterMaterielEmpruntableDeficit extends ArrayAdapter<MaterielEmpruntable> {

    public ListAdapterMaterielEmpruntableDeficit(Context context, List<MaterielEmpruntable> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_materieldeficit_view,parent, false);
        }

        MaterielEmpruntable materielEmpruntable = getItem(position);
        TextView nom = (TextView)convertView.findViewById(R.id.nom3_textview);
        TextView ref = (TextView)convertView.findViewById(R.id.reference_a_commander_textview);
        TextView aCommander = (TextView)convertView.findViewById(R.id.a_commander_textview);
        nom.setText(materielEmpruntable.getNom());
        ref.setText(materielEmpruntable.getReference());
        aCommander.setText(Integer.toString(materielEmpruntable.getQuantiteACommander()));

        return convertView;
    }
}
