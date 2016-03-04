package com.adamin.manslove.presenter.main;

import com.adamin.manslove.domain.TabModel;
import com.adamin.manslove.model.main.MainModel;
import com.adamin.manslove.model.main.MainModelImpl;
import com.adamin.manslove.model.main.OnMainListener;
import com.adamin.manslove.view.main.MainView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

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
 * //         Created by LiTao on 2016-02-22-23:11.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class MainPresenter implements OnMainListener{
    private MainView mainView;
    private MainModel mainModel;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        mainModel=new MainModelImpl();
    }

    public void fetchData(Object tag){
        mainModel.fetchTabs(tag,this);
    }
    public void cancel(Object tag){
        OkHttpUtils.getInstance().cancelTag(tag);

    }

    @Override
    public void before() {
        mainView.showLoadingAlert();


    }

    @Override
    public void after() {
        mainView.hideLoaingAlert();

    }

    @Override
    public void success(String response) {
        mainView.SetTabData(response);

    }

    @Override
    public void success(List<?> tabModels) {
        mainView.SetTabData((List<TabModel>) tabModels);
    }


//    @Override
//    public void success(List<TabModel> tabModels) {
//           mainView.SetTabData(tabModels);
//    }

    @Override
    public void error(Exception e) {
        mainView.showError(e);

    }
}
