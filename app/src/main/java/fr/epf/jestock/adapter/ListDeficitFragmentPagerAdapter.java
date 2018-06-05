package fr.epf.jestock.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.epf.jestock.fragment.ListEmpruntableDeficitFragment;
import fr.epf.jestock.fragment.ListStockDeficitFragment;

/*
    Nom ......... : ListDeficitFragmentPagerAdapter.java
    Role ........ : Element permettant la gestion des onglets de navigation dans l'activité ListDeficitActivity.java
    Auteur ...... : DSI_2
*/

public class ListDeficitFragmentPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    private String tabTitles[] = new String[]{"Matériel en stock","Matériel empruntable"};

    public ListDeficitFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Retourne le fragment associé à la page
    @Override
    public Fragment getItem(int position) {

        if (position == 0) return new ListStockDeficitFragment();
        else  return new ListEmpruntableDeficitFragment();
    }

    // Retourne le nombre d'onglet
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returne le titre pour chaque page
    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position] ;
    }
}
