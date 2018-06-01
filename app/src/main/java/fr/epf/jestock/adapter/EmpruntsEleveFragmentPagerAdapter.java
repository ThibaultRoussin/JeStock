package fr.epf.jestock.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.epf.jestock.fragment.EmpruntsEleveFragment;
import fr.epf.jestock.fragment.EmpruntsProfesseurFragment;

/**
 * Created by Utilisateur on 01/06/2018.
 */

public class EmpruntsEleveFragmentPagerAdapter extends FragmentPagerAdapter{

    private static int NUM_ITEMS = 2;
    private String tabTitles[] = new String[]{"Eleve","Professeur"};

    public EmpruntsEleveFragmentPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        if (position == 0) return new EmpruntsEleveFragment();
        else return new EmpruntsProfesseurFragment();

    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position] ;
    }

}
