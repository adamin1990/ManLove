package com.adamin.manslove.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adamin.manslove.domain.tg.TgDetailData;
import com.adamin.manslove.view.tg.TgDetailFragment;

import java.util.List;

/**
 * Created by adamlee on 2016/4/15.
 */
public class TgDetailAdapter extends FragmentStatePagerAdapter {
    private List<TgDetailData> tgDetailDatas;

    public TgDetailAdapter(FragmentManager fm, List<TgDetailData> tgDetailDatas) {
        super(fm);
        this.tgDetailDatas = tgDetailDatas;
    }

    @Override
    public Fragment getItem(int position) {
        return TgDetailFragment.newInstance(tgDetailDatas.get(position));
    }

    @Override
    public int getCount() {
        return tgDetailDatas.size();
    }
}
