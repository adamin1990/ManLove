package com.adamin.manslove.model.gank;


/**
 * Created by adamlee on 2016/3/13.
 */
public interface GanMeiZiModel {
    void getMeiZi(int page, Object tag,GanMeiZiListener listener);
    void cancelGanMZ(Object tag);

}
