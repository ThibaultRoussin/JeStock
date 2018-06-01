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
 * Created by Utilisateur on 23/05/2018.
 */

public class ListAdapterMaterielEmpruntable extends ArrayAdapter<MaterielEmpruntable>{

    public ListAdapterMaterielEmpruntable(Context context, List<MaterielEmpruntable> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_materielempruntable_view,parent, false);
        }

        MaterielEmpruntable materielEmpruntable = getItem(position);
        TextView ref = (TextView)convertView.findViewById(R.id.ref2_textview);
        TextView nom = (TextView)convertView.findViewById(R.id.nom2_textview);
        TextView quantiteTotale = (TextView)convertView.findViewById(R.id.quantite_totale_textview);
        TextView quantiteDisponible = (TextView)convertView.findViewById(R.id.quantite_disponible_textview);
        TextView quantiteEmpruntee = (TextView)convertView.findViewById(R.id.quantite_empruntee_textview);
        nom.setText(materielEmpruntable.getNom());
        ref.setText(materielEmpruntable.getReference());
        quantiteTotale.setText(Integer.toString(materielEmpruntable.getQuantiteTotale()));
        quantiteEmpruntee.setText(Integer.toString(materielEmpruntable.getQuantiteEmprunte()));
        quantiteDisponible.setText(Integer.toString(materielEmpruntable.getQuantiteNonEmprunte()));

        return convertView;
    }
}
