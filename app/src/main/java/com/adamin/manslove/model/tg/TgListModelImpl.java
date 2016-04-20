package com.adamin.manslove.model.tg;

import com.adamin.manslove.callback.TgListCallback;
import com.adamin.manslove.domain.tg.TgDataWrapper;
import com.adamin.manslove.utils.Constant;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by adamlee on 2016/4/11.
 */
public class TgListModelImpl implements TgListModel {
    @Override
    public void fethctData(int page, Object tag,int id, final OnTgListListener listListener) {
        OkHttpUtils.get()
                .url(Constant.TGLIST + page + "&rows=10"+"&id="+id)
                .tag(tag)
                .build()
                .execute(new TgListCallback() {
                    @Override
                    public void onBefore(Request request) {
                        listListener.before();
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter() {
                        listListener.after();
                        super.onAfter();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        listListener.error(e);

                    }

                    @Override
                    public void onResponse(TgDataWrapper response) {
                        listListener.success(response);

                    }
                });
    }

    @Override
    public void cancel(Object tag) {
        OkHttpUtils.getInstance()
                .cancelTag(tag);

    }
}
