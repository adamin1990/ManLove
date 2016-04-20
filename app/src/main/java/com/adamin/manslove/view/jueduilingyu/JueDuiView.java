package com.adamin.manslove.view.jueduilingyu;


import com.adamin.manslove.domain.juedui.JueDuiWrapper;

/**
 * Created by adamlee on 2016/3/30.
 */
public interface JueDuiView {
    void showloading();
    void hideloading();
    void showError(Exception e);
    void setData(JueDuiWrapper jueDuiWrapper);
}
