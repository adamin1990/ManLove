package com.adamin.manslove.model.juedui;

/**
 * Created by adamlee on 2016/3/30.
 */
public interface JueDuiModel  {
    void getData(Object tag,String category,int page,OnJueDuiListener listener);
    void cancel(Object tag);
}
