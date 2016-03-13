package com.adamin.manslove.presenter.gank;

import com.adamin.manslove.domain.GanMeiZiWrapper;
import com.adamin.manslove.model.gank.GanMeiZiImpl;
import com.adamin.manslove.model.gank.GanMeiZiListener;
import com.adamin.manslove.model.gank.GanMeiZiModel;
import com.adamin.manslove.view.gank.GankView;

/**
 * Created by adamlee on 2016/3/13.
 */
public class GanMeiZiPresenter  implements GanMeiZiListener {
    private GankView gankView;
    private GanMeiZiModel ganMeiZiModel;

    public GanMeiZiPresenter(GankView gankView) {
        this.gankView = gankView;
        ganMeiZiModel=new GanMeiZiImpl();

    }
    public void getMeiZi(int page,Object tag){
        ganMeiZiModel.getMeiZi(page,tag,this);
    }
    public void cancelGetMeiz(Object tag){
        ganMeiZiModel.cancelGanMZ(tag);

    }


    @Override
    public void before() {
        gankView.showProgress();

    }

    @Override
    public void after() {
        gankView.hideProgress();

    }

    @Override
    public void response(GanMeiZiWrapper ganMeiZiWrapper) {
        gankView.setMeiZi(ganMeiZiWrapper);

    }

    @Override
    public void onError(Exception e) {
        gankView.showError(e);

    }
}
