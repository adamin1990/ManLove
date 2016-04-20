package com.adamin.manslove.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.adamin.manslove.domain.tg.TgData;
import com.adamin.manslove.view.tg.TgFragment;

import java.util.List;

/**
 * Created by adamlee on 2016/4/15.
 */
public class TgPagerAdapter extends FragmentPagerAdapter {
    private int [] ids;
    private String [] titles;

    public TgPagerAdapter(FragmentManager fm, int[] ids, String[] titles) {
        super(fm);
        this.ids = ids;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return TgFragment.newInstance(titles[position],ids[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
