package com.adamin.manslove.model.huasheng;

import com.adamin.manslove.domain.huasheng.HuaShengInnerWrapper;

/**
 * Created by adamlee on 2016/3/30.
 */
public interface OnHuaShengListener {
    void before();
    void after();
    void success(HuaShengInnerWrapper innerWrapper);
    void error(Exception error);
}
