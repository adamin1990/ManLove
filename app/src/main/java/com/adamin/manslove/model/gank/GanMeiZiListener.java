package com.adamin.manslove.model.gank;

import com.adamin.manslove.domain.GanMeiZiWrapper;

/**
 * Created by adamlee on 2016/3/13.
 */
public interface GanMeiZiListener {
    void before();
    void after();
    void response(GanMeiZiWrapper ganMeiZiWrapper);
    void onError(Exception e);
}
