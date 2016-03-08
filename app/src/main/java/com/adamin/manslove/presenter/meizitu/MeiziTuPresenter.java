package com.adamin.manslove.presenter.meizitu;

import com.adamin.manslove.domain.MeiZiTuWrapper;
import com.adamin.manslove.model.meizitu.MeiZiTuModel;
import com.adamin.manslove.model.meizitu.MeiZituListener;
import com.adamin.manslove.model.meizitu.MeiziTuModelImpl;
import com.adamin.manslove.view.meizitu.MeiZiTuView;

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
 * //         Created by LiTao on 2016-03-05-22:34.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class MeiziTuPresenter implements MeiZituListener {
    private MeiZiTuView meiZiTuView;
    private MeiZiTuModel meiZiTuModel;

    public MeiziTuPresenter(MeiZiTuView meiZiTuView) {
        this.meiZiTuView = meiZiTuView;
        meiZiTuModel=new MeiziTuModelImpl();
    }

    public void fechData(String siceid,Object tag){
        meiZiTuModel.fetchData(siceid,tag,this);
    }
    public void cancelFetch(Object tag){
        meiZiTuModel.cancelFecth(tag);
    }

    @Override
    public void before() {
        meiZiTuView.showLoading();

    }

    @Override
    public void after() {
        meiZiTuView.hideLoading();

    }

    @Override
    public void success(MeiZiTuWrapper meiZiTuWrapper) {
            meiZiTuView.setData(meiZiTuWrapper.getData());
    }

    @Override
    public void error(Exception e) {
              meiZiTuView.showError(e);
    }

    @Override
    public void progress(float progress) {
    }
}
