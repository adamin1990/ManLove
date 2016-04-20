package com.adamin.manslove.model.lexun;

import com.adamin.manslove.domain.lexun.OnLeXunListener;

/**
 * Created by adamlee on 2016/4/2.
 */
public interface LeXunModel {
    void getData(Object tag, int page , int type, OnLeXunListener onLeXunListener);
    void cancel(Object tag);
}
