package com.adamin.manslove.callback;

import com.adamin.manslove.domain.huasheng.HuaShengImageListWrapper;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by adamlee on 2016/3/29.
 */
public abstract class HuaShengImageListCallback extends Callback<HuaShengImageListWrapper> {
    @Override
    public HuaShengImageListWrapper parseNetworkResponse(Response response) throws Exception {
        String json=response.body().string();

        return new Gson().fromJson(json,HuaShengImageListWrapper.class);
    }
}
