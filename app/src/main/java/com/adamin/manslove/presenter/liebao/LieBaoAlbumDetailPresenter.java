package com.adamin.manslove.presenter.liebao;

import com.adamin.manslove.domain.LieBaoAlbumDetailWrapper;
import com.adamin.manslove.model.liebao.LieBaoAlbumDetailModel;
import com.adamin.manslove.model.liebao.LieBaoAlbumDetailModelImpl;
import com.adamin.manslove.model.liebao.LieBaoAlbunDetailListener;
import com.adamin.manslove.view.liebao.LieBaoAlbumDetailView;

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
 * //         Created by LiTao on 2016-03-08-10:45.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class LieBaoAlbumDetailPresenter implements LieBaoAlbunDetailListener {
    private LieBaoAlbumDetailView detailView;
    private LieBaoAlbumDetailModel detailModel;

    public LieBaoAlbumDetailPresenter(LieBaoAlbumDetailView detailView) {
        this.detailView = detailView;
        detailModel=new LieBaoAlbumDetailModelImpl();
    }

    public void fetchData(Object tag,String category,String id,boolean before){
        detailModel.fetchData(tag,category,id,this,before);
    }
    public void cancel(Object tag){
        detailModel.candelFetch(tag);
    }

    @Override
    public void before() {
        detailView.showLoading();

    }

    @Override
    public void after() {
        detailView.hideLoaidng();

    }

    @Override
    public void success(LieBaoAlbumDetailWrapper albumDetailWrapper, boolean before) {
        if(before){
            detailView.setDataBefore(albumDetailWrapper);
        }else{
            detailView.setDataAfter(albumDetailWrapper);
        }
    }


    @Override
    public void error(Exception e) {
        detailView.showError(e);

    }
}
