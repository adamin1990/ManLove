package com.adamin.manslove.presenter.main;

import com.adamin.manslove.domain.HomeData;
import com.adamin.manslove.domain.TabModel;
import com.adamin.manslove.model.main.MainFragmentModel;
import com.adamin.manslove.model.main.MainFragmentModelImpl;
import com.adamin.manslove.model.main.OnMainListener;
import com.adamin.manslove.utils.LogUtil;
import com.adamin.manslove.view.main.MainFragmentView;

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
 * //         Created by LiTao on 2016-02-23-22:46.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class MainFragmentPresenter implements OnMainListener {
    private MainFragmentView mainFragmentView;
    private MainFragmentModel mainFragmentModel;

    public MainFragmentPresenter(MainFragmentView mainFragmentView) {
        this.mainFragmentView = mainFragmentView;
        mainFragmentModel = new MainFragmentModelImpl();
    }

    public void getData(Object object, int page , String pagesize, String keyword, TabModel tabModel){
        mainFragmentModel.fetchDataByNetwork(object,this,page,pagesize,keyword,tabModel);
    }
    public void cancel(Object tag){
        mainFragmentModel.cancel(tag);
    }

    @Override
    public void before() {
        mainFragmentView.showProgress();

    }

    @Override
    public void after() {
        mainFragmentView.hideProgress();

    }

    @Override
    public void success(String response) {
        mainFragmentView.setListData(response);

    }

    @Override
    public void success(List<?> tabModels) {
        mainFragmentView.setListData((List<HomeData>) tabModels);

    }

    @Override
    public void error(Exception e) {
        mainFragmentView.showError(e);

    }
}
