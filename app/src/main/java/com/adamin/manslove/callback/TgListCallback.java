package com.adamin.manslove.callback;

import com.adamin.manslove.domain.tg.TgDataWrapper;
import com.adamin.manslove.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by adamlee on 2016/4/11.
 */
public abstract class TgListCallback extends Callback<TgDataWrapper> {
    @Override
    public TgDataWrapper parseNetworkResponse(Response response) throws Exception {
        String json=response.body().string();
        LogUtil.error(TgListCallback.class,json);
        return new Gson().fromJson(json,TgDataWrapper.class);
    }
}
