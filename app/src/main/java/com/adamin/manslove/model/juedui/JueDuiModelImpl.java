package com.adamin.manslove.model.juedui;

import com.adamin.manslove.callback.JueDuiCallback;
import com.adamin.manslove.domain.juedui.JueDuiWrapper;
import com.adamin.manslove.utils.Constant;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by adamlee on 2016/3/30.
 */
public class JueDuiModelImpl implements JueDuiModel {
    @Override
    public void getData(Object tag, String category, int page, final OnJueDuiListener listener) {
        OkHttpUtils.get()
                .tag(tag)
                .url(Constant.JUEDUILINGYU+category+"/?json=1&page="+page)
                .build()
                .execute(new JueDuiCallback() {
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
                        listener.error(e);

                    }

                    @Override
                    public void onResponse(JueDuiWrapper response) {
                        listener.success(response);

                    }
                });
    }

    @Override
    public void cancel(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);

    }
}
