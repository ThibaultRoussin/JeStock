package fr.epf.jestock.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.epf.jestock.fragment.ListEmpruntableDeficitFragment;
import fr.epf.jestock.fragment.ListStockDeficitFragment;

/**
 * Created by Thibault on 02/06/2018.
 */

public class ListDeficitFragmentPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    private String tabTitles[] = new String[]{"Matériel en stock","Matériel empruntable"};

    public ListDeficitFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) return new ListStockDeficitFragment();
        else  return new ListEmpruntableDeficitFragment();
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
