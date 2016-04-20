package com.adamin.manslove.model.tg;


import com.adamin.manslove.domain.tg.TgDataWrapper;

/**
 * Created by adamlee on 2016/4/11.
 */
public interface OnTgListListener {
    void before();
    void after();
    void success(TgDataWrapper tgDataWrapper);
    void error(Exception e);
}
