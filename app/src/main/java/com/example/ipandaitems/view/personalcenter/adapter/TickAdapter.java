package com.example.ipandaitems.view.personalcenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 张豫耀 on 2017/9/6.
 */

public class TickAdapter extends FragmentPagerAdapter {
    private List<Fragment> fralist;
    private List<String> strlist;

    public TickAdapter(FragmentManager fm, List<Fragment> fralist, List<String> strlist) {
        super(fm);
        this.fralist = fralist;
        this.strlist = strlist;
    }

    @Override
    public Fragment getItem(int position) {
        return fralist.get(position);
    }

    @Override
    public int getCount() {
        return fralist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strlist.get(position);
    }
}
