package com.adamin.manslove.callback;

import com.adamin.manslove.domain.GanMeiZiWrapper;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by adamlee on 2016/3/13.
 */
public abstract class GanMeiZiCallback extends Callback<GanMeiZiWrapper> {
    @Override
    public GanMeiZiWrapper parseNetworkResponse(Response response) throws Exception {
        String json=response.body().string();

        return new Gson().fromJson(json,GanMeiZiWrapper.class);
    }
}
