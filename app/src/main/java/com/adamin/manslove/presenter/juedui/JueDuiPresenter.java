package com.adamin.manslove.presenter.juedui;

import com.adamin.manslove.domain.juedui.JueDuiWrapper;
import com.adamin.manslove.model.juedui.JueDuiModel;
import com.adamin.manslove.model.juedui.JueDuiModelImpl;
import com.adamin.manslove.model.juedui.OnJueDuiListener;
import com.adamin.manslove.view.jueduilingyu.JueDuiView;

/**
 * Created by adamlee on 2016/3/30.
 */
public class JueDuiPresenter implements OnJueDuiListener {
    private JueDuiView jueDuiView;
    private JueDuiModel jueDuiModel;

    public JueDuiPresenter(JueDuiView jueDuiView) {
        this.jueDuiView = jueDuiView;
        jueDuiModel=new JueDuiModelImpl();
    }


    public void getData(Object tag,String category,int page){
        jueDuiModel.getData(tag,category,page,this);
    }
    public void cancel(Object tag){
        jueDuiModel.cancel(tag);
    }

    @Override
    public void before() {
        jueDuiView.showloading();

    }

    @Override
    public void after() {
        jueDuiView.hideloading();

    }

    @Override
    public void success(JueDuiWrapper innerWrapper) {
        jueDuiView.setData(innerWrapper);

    }

    @Override
    public void error(Exception error) {
        jueDuiView.showError(error);

    }
}
