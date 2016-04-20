package com.adamin.manslove.presenter.tg;

import com.adamin.manslove.domain.tg.TgDataWrapper;
import com.adamin.manslove.model.tg.OnTgListListener;
import com.adamin.manslove.model.tg.TgListModel;
import com.adamin.manslove.model.tg.TgListModelImpl;
import com.adamin.manslove.view.tg.TgListView;

/**
 * Created by adamlee on 2016/4/11.
 */
public class TgListPresenter implements OnTgListListener {

    private TgListView tgListView;
    private TgListModel tgListModel;

    public TgListPresenter(TgListView tgListView) {
        this.tgListView = tgListView;
        tgListModel = new TgListModelImpl();
    }

    public void getTgList(int page, int id,Object tag) {
        tgListModel.fethctData(page, tag,id, this);
    }

    public void cancel(Object tag) {
        tgListModel.cancel(tag);

    }

    @Override
    public void before() {
        tgListView.showLoading();

    }

    @Override
    public void after() {
        tgListView.hideLoading();

    }

    @Override
    public void success(TgDataWrapper tgDataWrapper) {
        tgListView.setData(tgDataWrapper);

    }

    @Override
    public void error(Exception e) {
        tgListView.hideLoading();
        tgListView.showError(e);

    }
}
