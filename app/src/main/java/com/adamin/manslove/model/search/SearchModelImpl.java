package com.adamin.manslove.model.search;

import com.adamin.manslove.callback.HomeDataCallback;
import com.adamin.manslove.domain.HomeDataWrapperReal;
import com.adamin.manslove.model.main.OnMainListener;
import com.adamin.manslove.utils.ApiUtils;
import com.adamin.manslove.utils.Constant;
import com.zhy.http.okhttp.OkHttpUtils;

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
 * //         Created by LiTao on 2016-02-27-15:50.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class SearchModelImpl implements SearchModel {
    @Override
    public void searchData(Object tag, String keywords, int page, String pagesize, final OnMainListener onMainListener) {
        OkHttpUtils.post()
                .tag(tag)
                .url(Constant.BASEURL)
                .params(ApiUtils.getAlbumtHomeData(page,pagesize,keywords,""))
                .build()
                .execute(new HomeDataCallback() {
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
                    public void onResponse(HomeDataWrapperReal response) {
                        onMainListener.success(response.getData().getList());

                    }

                    @Override
                    public void onAfter() {
                        onMainListener.after();
                        super.onAfter();
                    }
                });
    }

    @Override
    public void cancel(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);
    }
}
