package com.adamin.manslove.view.gank;

import com.adamin.manslove.domain.GanMeiZiWrapper;

/**
 * Created by adamlee on 2016/3/13.
 */
public interface GankView {
    public void showProgress();
    public void hideProgress();
    public void showError(Exception e);
    public void setMeiZi(GanMeiZiWrapper ganMeiZiWrapper);
}
