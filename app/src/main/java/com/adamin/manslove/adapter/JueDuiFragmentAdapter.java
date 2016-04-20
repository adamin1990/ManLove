package com.adamin.manslove.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adamin.manslove.view.jueduilingyu.JueDuiFragment;

/**
 * Created by adamlee on 2016/3/31.
 */
public class JueDuiFragmentAdapter extends FragmentStatePagerAdapter {
    private String [] names;
    private String [] types;

    public JueDuiFragmentAdapter(FragmentManager fm, String[] names, String[] types) {
        super(fm);
        this.names = names;
        this.types = types;
    }

    @Override
    public Fragment getItem(int position) {
        return JueDuiFragment.newInstance(names[position],types[position]);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];
    }
}
