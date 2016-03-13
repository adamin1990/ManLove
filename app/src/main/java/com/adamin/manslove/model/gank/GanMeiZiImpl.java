package com.adamin.manslove.model.gank;

import com.adamin.manslove.callback.GanMeiZiCallback;
import com.adamin.manslove.domain.GanMeiZiWrapper;
import com.adamin.manslove.utils.Constant;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by adamlee on 2016/3/13.
 */
public class GanMeiZiImpl implements GanMeiZiModel {
    @Override
    public void getMeiZi(int page, Object tag, final GanMeiZiListener listener) {
        OkHttpUtils.get()
                .tag(tag)
                .url(Constant.GANHUO + page)
                .build()
                .execute(new GanMeiZiCallback() {
                    @Override
                    public void onBefore(Request request) {
                        listener.before();
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter() {
                        listener.after();
                        super.onAfter();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        listener.onError(e);

                    }

                    @Override
                    public void onResponse(GanMeiZiWrapper response) {
                        listener.response(response);

                    }
                });
    }

    @Override
    public void cancelGanMZ(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);

    }
}
