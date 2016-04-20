package com.adamin.manslove.model.lexun;

import com.adamin.manslove.callback.LeXunCallback;
import com.adamin.manslove.domain.lexun.LeXunDataWrapper;
import com.adamin.manslove.domain.lexun.OnLeXunListener;
import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by adamlee on 2016/4/2.
 */
public class LeXunModelImpl implements LeXunModel {
    @Override
    public void getData(Object tag, int page, int type, final OnLeXunListener onLeXunListener) {
        LogUtil.error(LeXunModelImpl.class,Constant.LEXUN+page+"&pagesize=50&order=1&typeid="+type);
        OkHttpUtils.get()
                .url(Constant.LEXUN+page+"&pagesize=20&order=1&typeid="+type)
                .tag(tag)
                .build()
                .execute(new LeXunCallback() {
                    @Override
                    public void onBefore(Request request) {
                        onLeXunListener.before();
                        super.onBefore(request);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        onLeXunListener.error(e);

                    }

                    @Override
                    public void onResponse(LeXunDataWrapper response) {
                        onLeXunListener.sussess(response);

                    }
                });
    }

    @Override
    public void cancel(Object tag) {
        OkHttpUtils.getInstance()
                .cancelTag(tag);

    }
}
