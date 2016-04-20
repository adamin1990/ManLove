package com.adamin.manslove.callback;

import com.adamin.manslove.domain.lexun.LeXunDataWrapper;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by adamlee on 2016/4/2.
 */
public abstract class LeXunCallback extends Callback<LeXunDataWrapper> {
    @Override
    public LeXunDataWrapper parseNetworkResponse(Response response) throws Exception {
        String json=response.body().string();
        String newjson=json.substring(1,json.length()-1);

        return new Gson().fromJson(newjson,LeXunDataWrapper.class);
    }
}
