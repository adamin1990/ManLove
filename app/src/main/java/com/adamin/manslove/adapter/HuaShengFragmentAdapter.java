package com.adamin.manslove.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.adamin.manslove.view.huasheng.HuaShengListFragment;

/**
 * Created by adamlee on 2016/3/29.
 */
public class HuaShengFragmentAdapter extends FragmentPagerAdapter {
    private String [] ids;
    private String [] names;

    public HuaShengFragmentAdapter(FragmentManager fm, String[] ids, String[] names) {
        super(fm);
        this.ids = ids;
        this.names = names;
    }

    @Override
    public Fragment getItem(int position) {
        return HuaShengListFragment.newInstance(ids[position],names[position]);
    }

    @Override
    public int getCount() {
        return ids.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];
    }
}
