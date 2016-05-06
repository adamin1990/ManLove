package com.adamin.manslove.callback;

import com.adamin.manslove.domain.lexun.LeXunDataDetailWrapper;
import com.adamin.manslove.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by adamlee on 2016/4/2.
 */
public abstract class LeXunDetailCallback extends Callback<LeXunDataDetailWrapper> {

    @Override
    public LeXunDataDetailWrapper parseNetworkResponse(Response response) throws Exception {
        String json=response.body().string();
        String json2=json.substring(1,json.length()-1);
        return new Gson().fromJson(json2,LeXunDataDetailWrapper.class);
    }
}
