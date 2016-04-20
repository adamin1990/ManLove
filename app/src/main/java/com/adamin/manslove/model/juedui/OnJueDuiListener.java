package com.adamin.manslove.model.juedui;


import com.adamin.manslove.domain.juedui.JueDuiWrapper;

/**
 * Created by adamlee on 2016/3/30.
 */
public interface OnJueDuiListener {
    void before();
    void after();
    void success(JueDuiWrapper innerWrapper);
    void error(Exception error);
}
