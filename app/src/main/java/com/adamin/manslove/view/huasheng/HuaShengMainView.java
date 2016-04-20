package com.adamin.manslove.view.huasheng;

import com.adamin.manslove.domain.huasheng.HuaShengInnerWrapper;

/**
 * Created by adamlee on 2016/3/30.
 */
public interface HuaShengMainView {
    void showloading();
    void hideloading();
    void showError(Exception e);
    void setData(HuaShengInnerWrapper huaShengInnerWrapper);
}
