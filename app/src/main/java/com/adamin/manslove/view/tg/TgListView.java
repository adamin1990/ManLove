package com.adamin.manslove.view.tg;

import com.adamin.manslove.domain.tg.TgDataWrapper;

/**
 * Created by adamlee on 2016/4/11.
 */
public interface TgListView {
    void showLoading();
    void hideLoading();
    void setData(TgDataWrapper tgDataWrapper);
    void showError(Exception e);
}
