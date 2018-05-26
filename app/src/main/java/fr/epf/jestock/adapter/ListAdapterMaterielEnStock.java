package fr.epf.jestock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import fr.epf.jestock.R;
import fr.epf.jestock.model.MaterielEnStock;

/**
 * Created by Utilisateur on 22/05/2018.
 */

public class ListAdapterMaterielEnStock extends ArrayAdapter<MaterielEnStock>{

    public ListAdapterMaterielEnStock(Context context, List<MaterielEnStock> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_materielenstock_view,parent, false);
        }

        MaterielEnStock materielEnStock = getItem(position);
        TextView nom = (TextView)convertView.findViewById(R.id.nom1_textview);
        TextView quantite = (TextView)convertView.findViewById(R.id.quantite_textview);
        nom.setText(materielEnStock.getNom());
        quantite.setText(Integer.toString(materielEnStock.getQuantiteStock()));

        return convertView;
    }


}