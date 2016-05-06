package com.adamin.manslove.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adamin.manslove.domain.lexun.LeXunDataDetail;
import com.adamin.manslove.view.lexun.LeXunDetailFragment;

import java.util.List;

/**
 * Created by adamlee on 2016/5/5.
 */
public class LeXunDetailAdapter  extends FragmentStatePagerAdapter {
    private List<LeXunDataDetail> leXunDataDetails;

    public LeXunDetailAdapter(FragmentManager fm, List<LeXunDataDetail> leXunDataDetails) {
        super(fm);
        this.leXunDataDetails = leXunDataDetails;
    }

    @Override
    public Fragment getItem(int position) {
        return LeXunDetailFragment.instance(leXunDataDetails.get(position));
    }

    @Override
    public int getCount() {
        return leXunDataDetails.size();
    }
}
