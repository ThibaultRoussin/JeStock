package fr.epf.jestock.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.epf.jestock.ListActivity;
import fr.epf.jestock.fragment.ListEmpruntsFragment;
import fr.epf.jestock.fragment.ListMaterielEmpruntableFragment;
import fr.epf.jestock.fragment.ListMaterielEnStockFragment;

public class ListFragmentPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    private String tabTitles[] = new String[]{"Matériel en stock","Matériel empruntable", "Emprunts"};

    public ListFragmentPagerAdapter(FragmentManager fragmentManager, Context context) {
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

                if (position == 0) return new ListMaterielEnStockFragment();
                else if (position == 1) return new ListMaterielEmpruntableFragment();
                else  return new ListEmpruntsFragment();



    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position] ;
    }

}