package com.adamin.manslove.presenter.liebao;

import com.adamin.manslove.domain.LieBaoTab;
import com.adamin.manslove.model.liebao.LieBaoListener;
import com.adamin.manslove.model.liebao.LieBaoModel;
import com.adamin.manslove.model.liebao.LieBaoModelImpl;
import com.adamin.manslove.view.liebao.LieBaoView;

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
 * //         Created by LiTao on 2016-03-07-17:57.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class LieBaoTagPresenter implements LieBaoListener {
    private LieBaoModel lieBaoModel;
    private LieBaoView lieBaoView;

    public LieBaoTagPresenter(LieBaoView lieBaoView) {
        this.lieBaoView = lieBaoView;
        lieBaoModel=new LieBaoModelImpl();
    }

    public void getTags(Object tag){
        lieBaoModel.fethcData(tag,this);
    }
    public void cancelGet(Object tag){
        lieBaoModel.cancel(tag);
    }

    @Override
    public void before() {
        lieBaoView.showLoading();

    }

    @Override
    public void after() {
        lieBaoView.hideLoading();

    }

    @Override
    public void success(List<?> tabModels) {
          lieBaoView.setData((List<LieBaoTab>) tabModels);
    }

    @Override
    public void error(Exception e) {
        lieBaoView.showError(e);

    }
}
