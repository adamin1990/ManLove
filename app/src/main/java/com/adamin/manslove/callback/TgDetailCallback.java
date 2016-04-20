package com.adamin.manslove.callback;

import com.adamin.manslove.domain.tg.TgDetailDataWrapper;
import com.adamin.manslove.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by adamlee on 2016/4/16.
 */
public abstract class TgDetailCallback extends Callback<TgDetailDataWrapper> {

    @Override
    public TgDetailDataWrapper parseNetworkResponse(Response response) throws Exception {
        String json=response.body().string();
        LogUtil.error(TgDetailCallback.class,json);

        return new Gson().fromJson(json,TgDetailDataWrapper.class);
    }
}
