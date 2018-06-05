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

/*
    Nom ......... : ListAdapterEmprunts.java
    Role ........ : Element permettant l'affichage de la liste des emprunts
    Auteur ...... : DSI_2
*/

public class ListAdapterEmprunts extends ArrayAdapter<Emprunts>{

    public ListAdapterEmprunts(Context context, List<Emprunts> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_emprunts_view,parent, false);
        }

        Emprunts emprunts = getItem(position);
        TextView numeroEtudiant = (TextView)convertView.findViewById(R.id.numero_etudiant__textview);
        TextView nom = (TextView)convertView.findViewById(R.id.nom3_textview);
        TextView ref = (TextView)convertView.findViewById(R.id.ref3_textview);
        TextView materielEmprunte = (TextView)convertView.findViewById(R.id.materiel_emprunte_textview);
        TextView dateEmprunt = (TextView)convertView.findViewById(R.id.date_emprunt_textview);
        TextView dateRetour = (TextView)convertView.findViewById(R.id.date_retour_textview);
        numeroEtudiant.setText(emprunts.getNomEmprunteur());
        nom.setText(emprunts.getPrenomEmprunteur());
        ref.setText(String.valueOf(emprunts.getReference()));
        materielEmprunte.setText(emprunts.getNomRef());
        dateEmprunt.setText(emprunts.getDateEmprunt());
        dateRetour.setText(emprunts.getDateRetour());

        return convertView;
    }
}
