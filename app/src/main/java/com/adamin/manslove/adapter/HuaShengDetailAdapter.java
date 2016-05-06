package com.adamin.manslove.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adamin.manslove.domain.huasheng.HuaShengImageList;
import com.adamin.manslove.view.huasheng.HuaShengDetailFragment;

import java.util.List;

/**
 * Created by adamlee on 2016/5/5.
 */
public class HuaShengDetailAdapter extends FragmentStatePagerAdapter {
    private List<String> huaShengImageLists;

    public HuaShengDetailAdapter(FragmentManager fm, List<String> huaShengImageLists) {
        super(fm);
        this.huaShengImageLists = huaShengImageLists;
    }

    @Override
    public Fragment getItem(int position) {
        return HuaShengDetailFragment.instance(huaShengImageLists.get(position));
    }

    @Override
    public int getCount() {
        return huaShengImageLists.size();
    }
}
