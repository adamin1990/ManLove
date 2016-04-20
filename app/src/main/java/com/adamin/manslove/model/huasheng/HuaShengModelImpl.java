package com.adamin.manslove.model.huasheng;

import com.adamin.manslove.callback.HuaShengListCallback;
import com.adamin.manslove.domain.huasheng.HuaShengDataWrapper;
import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by adamlee on 2016/3/30.
 */
public class HuaShengModelImpl implements HuaShengModel {
    @Override
    public void getList(Object tag, int start, String type, final OnHuaShengListener listener) {
        LogUtil.error(HuaShengModelImpl.class,Constant.HUASHENGLIST+start+"&limit=10&tag="+type);
        OkHttpUtils.get()
                .tag(tag)
                .url(Constant.HUASHENGLIST+start+"&limit=10&tag="+type)
                .build()
                .execute(new HuaShengListCallback() {
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
                    public void onResponse(HuaShengDataWrapper response) {
                        listener.success(response.getData());

                    }
                });
    }

    @Override
    public void cancel(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);

    }
}
