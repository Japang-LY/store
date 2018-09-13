package com.japangly.android.iwontdie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentArrayList = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {

        super(fm);
    }

    public void addFragment(Fragment fragment) {

        fragmentArrayList.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {

        return fragmentArrayList.size();
    }
}
