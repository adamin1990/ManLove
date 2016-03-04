package com.adamin.manslove.presenter.detail;

import android.widget.LinearLayout;

import com.adamin.manslove.domain.DetailDataWrapper;
import com.adamin.manslove.model.detail.AdModel;
import com.adamin.manslove.model.detail.AdmodelImpl;
import com.adamin.manslove.model.detail.DetailModel;
import com.adamin.manslove.model.detail.DetailModelImpl;
import com.adamin.manslove.model.detail.OnDetailListener;
import com.adamin.manslove.utils.LogUtil;
import com.adamin.manslove.view.detail.DetailActivity;
import com.adamin.manslove.view.detail.DetailView;
//import com.iflytek.voiceads.AdError;
//import com.iflytek.voiceads.IFLYAdListener;
//import com.iflytek.voiceads.IFLYBannerAd;
import com.zhy.http.okhttp.OkHttpUtils;

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
 * //         Created by LiTao on 2016-02-26-23:24.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class DetailPresenter implements OnDetailListener {
    private DetailModel detailModel;
    private DetailView detailView;
    private AdModel adModel;

    public DetailPresenter(DetailView detailView) {
        this.detailView = detailView;
        detailModel=new DetailModelImpl();
        adModel=new AdmodelImpl();
    }

    public void fetchData(Object tag,String id){
        detailModel.fetchData(tag,id,this);
    }
    public void cancel(Object tag){
        OkHttpUtils.getInstance().cancelTag(tag);

    }


    @Override

    public void before() {
        detailView.showLoading();

    }

    @Override
    public void after() {
        detailView.hideLoading();

    }

    @Override
    public void success(String response) {

    }

    @Override
    public void success(Object tabModels) {
        LogUtil.error(DetailPresenter.class,((DetailDataWrapper)tabModels).getList().get(0).getUrl()+"");
        detailView.setData(((DetailDataWrapper)tabModels).getList());

    }

    @Override
    public void error(Exception e) {
        detailView.showError(e);

    }

    @Override
    public void getId(String preid, String nextid,String title) {
        detailView.getIds(preid,nextid,title);

    }


}
