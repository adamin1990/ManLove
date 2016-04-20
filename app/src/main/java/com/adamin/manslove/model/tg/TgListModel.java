package com.adamin.manslove.model.tg;

import com.adamin.manslove.domain.tg.TgDataWrapper;

/**
 * Created by adamlee on 2016/4/11.
 */
public interface TgListModel {
    void fethctData(int page, Object tag,int id,  OnTgListListener listListener);
    void cancel(Object tag);
}
