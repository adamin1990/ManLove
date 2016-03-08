package com.adamin.manslove.model.meizitu;

import com.adamin.manslove.callback.MeiZiTuCallback;
import com.adamin.manslove.domain.MeiZiTuWrapper;
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
 * //         Created by LiTao on 2016-03-05-22:30.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class MeiziTuModelImpl implements MeiZiTuModel {
    @Override
    public void fetchData(String sinceid, Object tag, final MeiZituListener meiZituListener) {
        OkHttpUtils.get()
                .url(Constant.MEIZITU+sinceid)
                .tag(tag)
                .build()
                .execute(new MeiZiTuCallback() {
                    @Override
                    public void onBefore(Request request) {
                        meiZituListener.before();
                        super.onBefore(request);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                            meiZituListener.error(e);
                    }

                    @Override
                    public void inProgress(float progress) {
                        meiZituListener.progress(progress);
                        super.inProgress(progress);
                    }

                    @Override
                    public void onResponse(MeiZiTuWrapper response) {
                       meiZituListener.success(response);
                    }

                    @Override
                    public void onAfter() {
                        meiZituListener.after();
                        super.onAfter();
                    }
                });
    }

    @Override
    public void cancelFecth(Object tag) {
        OkHttpUtils.getInstance()
                .cancelTag(tag);

    }
}
