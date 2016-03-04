package com.adamin.manslove.model.detail;

import com.adamin.manslove.callback.DetailCallback;
import com.adamin.manslove.domain.DetailData;
import com.adamin.manslove.domain.DetailDataWrapperReal;
import com.adamin.manslove.model.main.OnMainListener;
import com.adamin.manslove.utils.ApiUtils;
import com.adamin.manslove.utils.Constant;
import com.zhy.http.okhttp.OkHttpUtils;

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
 * //         Created by LiTao on 2016-02-26-23:14.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class DetailModelImpl implements DetailModel {
    @Override
    public DetailDataWrapperReal fetchData(Object tag, String id, final OnDetailListener onMainListener) {
        final DetailDataWrapperReal[] detailDataWrapperReal = new DetailDataWrapperReal[1];
        OkHttpUtils.post()
                .tag(tag)
                .url(Constant.BASEURL)
                .params(ApiUtils.getAlbumDetail(id))
                .build()
                .execute(new DetailCallback() {
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
                    public void onResponse(DetailDataWrapperReal response) {
                        detailDataWrapperReal[0] =response;

                        onMainListener.success(response.getData());
                        onMainListener.getId(response.getData().getBefore()+"",response.getData().getAfter()+"",response.getData().getTitle()+"");
                    }

                    @Override
                    public void onAfter() {
                        onMainListener.after();
                        super.onAfter();
                    }
                });
        return detailDataWrapperReal[0];
    }

    @Override
    public List<DetailData> fethData(DetailDataWrapperReal detailDataWrapperReal) {
        return detailDataWrapperReal.getData().getList();
    }

    @Override
    public void cancel(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);

    }


}
