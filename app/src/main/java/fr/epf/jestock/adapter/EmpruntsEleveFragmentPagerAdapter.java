package fr.epf.jestock.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.epf.jestock.fragment.EmpruntsEleveFragment;
import fr.epf.jestock.fragment.EmpruntsProfesseurFragment;

/**
 * Created by Thibault on 01/06/2018.
 */

public class EmpruntsEleveFragmentPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    private String tabTitles[] = new String[]{"Eleve","Professeur"};

    public EmpruntsEleveFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return new EmpruntsEleveFragment();
        else return new EmpruntsProfesseurFragment();
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position] ;
    }
}
