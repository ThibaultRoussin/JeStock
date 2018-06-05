package fr.epf.jestock.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.epf.jestock.ListActivity;
import fr.epf.jestock.fragment.ListEmpruntsFragment;
import fr.epf.jestock.fragment.ListMaterielEmpruntableFragment;
import fr.epf.jestock.fragment.ListMaterielEnStockFragment;

/*
    Nom ......... : ListDeficitFragmentPagerAdapter.java
    Role ........ : Element permettant la gestion des onglets de navigation dans l'activité ListActivity.java
    Auteur ...... : DSI_2
*/

public class ListMaterielFragmentPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    private String tabTitles[] = new String[]{"Matériel en stock","Matériel empruntable", "Emprunts"};

    public ListMaterielFragmentPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
    }

    // Retourne le nombre d'onglet
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Retourne le fragment associé à la page
    @Override
    public Fragment getItem(int position) {

        if (position == 0) return new ListMaterielEnStockFragment();
        else if (position == 1) return new ListMaterielEmpruntableFragment();
        else  return new ListEmpruntsFragment();
    }

    // Returne le titre pour chaque page
    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position] ;
    }

}