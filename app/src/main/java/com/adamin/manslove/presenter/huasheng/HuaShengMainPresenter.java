package com.adamin.manslove.presenter.huasheng;

import com.adamin.manslove.domain.huasheng.HuaShengInnerWrapper;
import com.adamin.manslove.model.huasheng.HuaShengModel;
import com.adamin.manslove.model.huasheng.HuaShengModelImpl;
import com.adamin.manslove.model.huasheng.OnHuaShengListener;
import com.adamin.manslove.view.huasheng.HuaShengMainView;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by adamlee on 2016/3/30.
 */
public class HuaShengMainPresenter implements OnHuaShengListener {
    private HuaShengModel huaShengModel;
    private HuaShengMainView mainView;

    public HuaShengMainPresenter(HuaShengMainView mainView) {
        this.mainView = mainView;
        huaShengModel=new HuaShengModelImpl();
    }

    public void getData(Object tag,int start,String type){
        huaShengModel.getList(tag,start,type,this);

    }
    public void cancel(Object tag){
        huaShengModel.cancel(tag);

    }

    @Override
    public void before() {
        mainView.showloading();

    }

    @Override
    public void after() {
        mainView.hideloading();

    }

    @Override
    public void success(HuaShengInnerWrapper innerWrapper) {
        mainView.setData(innerWrapper);

    }

    @Override
    public void error(Exception error) {
        mainView.showError(error);

    }
}
