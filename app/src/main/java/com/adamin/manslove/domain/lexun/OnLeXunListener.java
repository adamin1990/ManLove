package com.adamin.manslove.domain.lexun;

/**
 * Created by adamlee on 2016/4/2.
 */
public interface OnLeXunListener {
    void before();
    void after();
    void sussess(LeXunDataWrapper dataWrapper);
    void error(Exception error);
}
