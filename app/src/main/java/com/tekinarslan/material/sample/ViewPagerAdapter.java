package com.tekinarslan.material.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT=4;
    private String titles[] ;
    Bundle userData = null;

    public ViewPagerAdapter(FragmentManager fm, String[] titles2, Bundle userData) {
        super(fm);
        titles=titles2;
        this.userData = userData;
    }

    @Override
    public Fragment getItem(int position) {

            if(position <= PAGE_COUNT){
                return SampleFragment.newInstance(position, userData);
            }

        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}