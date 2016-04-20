package com.adamin.manslove.presenter.lexun;

import com.adamin.manslove.domain.lexun.LeXunDataWrapper;
import com.adamin.manslove.domain.lexun.OnLeXunListener;
import com.adamin.manslove.model.lexun.LeXunModel;
import com.adamin.manslove.model.lexun.LeXunModelImpl;
import com.adamin.manslove.view.lexun.LeXunView;

/**
 * Created by adamlee on 2016/4/3.
 */
public class LeXunPresenter implements OnLeXunListener {

    private LeXunView leXunView;
    private LeXunModel leXunModel;

    public LeXunPresenter(LeXunView leXunView) {
        this.leXunView = leXunView;
        leXunModel=new LeXunModelImpl();
    }

    public void getData(Object tag,int page,int type){
        leXunModel.getData(tag,page,type,this);
    }
    public void cancel(Object tag){
        leXunModel.cancel(tag);
    }

    @Override
    public void before() {
        leXunView.showLoading();

    }

    @Override
    public void after() {
        leXunView.hideLoading();

    }

    @Override
    public void sussess(LeXunDataWrapper dataWrapper) {
        leXunView.setData(dataWrapper);

    }

    @Override
    public void error(Exception error) {
        leXunView.showError(error);

    }
}
