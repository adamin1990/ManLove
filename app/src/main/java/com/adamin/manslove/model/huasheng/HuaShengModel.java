package com.adamin.manslove.model.huasheng;

/**
 * Created by adamlee on 2016/3/30.
 */
public interface HuaShengModel{
   void getList(Object tag,int start,String type,OnHuaShengListener listener);
    void cancel(Object tag);

}
