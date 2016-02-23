package com.adamin.manslove.model.main;

import android.text.TextUtils;

import com.adamin.manslove.base.App;
import com.adamin.manslove.domain.TabModel;
import com.adamin.manslove.utils.ApiUtils;
import com.adamin.manslove.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 * //
 * //         Created by LiTao on 2016-02-23-12:52.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class MainModelImpl implements MainModel {
    private String data = "";

    @Override
    public String fetchLocalTabs(Object tag, final OnMainListener onMainListener) {
        String localData = App.utilsSharedPreferences.getChannel();
        if (TextUtils.isEmpty(localData)) {
            return fetchTabs(tag, onMainListener);
        } else {
            onMainListener.success(localData);
            return localData;
        }
    }

    @Override
    public String fetchTabs(Object tag, final OnMainListener onMainListener) {
        OkHttpUtils.post()
                .url(Constant.BASEURL)
                .tag(tag)
                .params(ApiUtils.getrequestAlbumChannel())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request) {
                        onMainListener.before();

                        super.onBefore(request);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        onMainListener.error(e);

                    }

                    @Override
                    public void onResponse(String response) {
                        data = response;
                        List<TabModel> tabModels=parseData();
                        onMainListener.success(response);
                        onMainListener.success(tabModels);

                    }

                    @Override
                    public void onAfter() {
                        onMainListener.after();
                        super.onAfter();
                    }
                });
        return data;
    }

    @Override
    public List<TabModel> parseData() {
        Gson gson = new Gson();
        try {
            JSONObject jsonObject=new JSONObject(data);
            JSONObject jb2=jsonObject.getJSONObject("data");
            JSONArray ja=jb2.getJSONArray("list");
            String s=ja.toString();
            List<TabModel> tabModels = gson.fromJson(s,new TypeToken<List<TabModel>>(){}.getType());
            return tabModels;
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<TabModel>();
        }


    }
}
