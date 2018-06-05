package fr.epf.jestock.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.epf.jestock.fragment.EmpruntsEleveFragment;
import fr.epf.jestock.fragment.EmpruntsProfesseurFragment;


/*
    Nom ......... : EmpruntsEleveFragmentPager.java
    Role ........ : Element permettant la gestion des onglets de navigation lors de l'emprunt
    Auteur ...... : DSI_2

*/

public class EmpruntsEleveFragmentPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    private String tabTitles[] = new String[]{"Eleve","Professeur"};

    public EmpruntsEleveFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Retourne le fragment associé à la page
    @Override
    public Fragment getItem(int position) {
        if (position == 0) return new EmpruntsEleveFragment();
        else return new EmpruntsProfesseurFragment();
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
