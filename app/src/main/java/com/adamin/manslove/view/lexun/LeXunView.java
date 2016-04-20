package com.adamin.manslove.view.lexun;

import com.adamin.manslove.domain.lexun.LeXunDataWrapper;

/**
 * Created by adamlee on 2016/4/3.
 */
public interface LeXunView {
    void showLoading();
    void hideLoading();
    void showError(Exception error);
    void setData(LeXunDataWrapper dataWrapper);
}
