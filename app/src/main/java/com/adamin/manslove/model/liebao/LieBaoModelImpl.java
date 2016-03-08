package com.adamin.manslove.model.liebao;

import com.adamin.manslove.callback.LieBaoTabCallback;
import com.adamin.manslove.domain.LieBaoTabWrapper;
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
 * //         Created by LiTao on 2016-03-07-12:31.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class LieBaoModelImpl implements LieBaoModel {
    @Override
    public void fethcData(Object tag, final LieBaoListener lieBaoListener) {
        OkHttpUtils.get()
                .tag(tag)
                .url(Constant.LIEBAOTAG)
                .build()
                .execute(new LieBaoTabCallback() {
                    @Override
                    public void onBefore(Request request) {
                        lieBaoListener.before();
                        super.onBefore(request);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        lieBaoListener.error(e);

                    }

                    @Override
                    public void onResponse(LieBaoTabWrapper response) {
                        lieBaoListener.success(response.getData());

                    }

                    @Override
                    public void onAfter() {
                        lieBaoListener.after();
                        super.onAfter();
                    }
                });
    }

    @Override
    public void cancel(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);

    }
}
