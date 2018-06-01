package fr.epf.jestock.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.epf.jestock.R;

/**
 * Created by Utilisateur on 01/06/2018.
 */

public class EmpruntsProfesseurFragment extends Fragment {


    public EmpruntsProfesseurFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emprunts_professeur_view, container, false);
        return view;
    }
}
