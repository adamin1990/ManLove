package com.adamin.manslove.view.meizitu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.DetailMeiziAdapter;
import com.adamin.manslove.adapter.MeiZiTuAdapter;
import com.adamin.manslove.base.BaseActivity;
import com.adamin.manslove.domain.MeiZiTu;
import com.adamin.manslove.utils.StatusBarCompact;
import com.adamin.manslove.utils.ViewPagerFixed;

import butterknife.Bind;
import butterknife.ButterKnife;

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
 * //         Created by LiTao on 2016-03-06-20:52.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class MeiZiDetailActivity extends BaseActivity implements MeiZituDetailFragment.TapListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.pager)
    ViewPagerFixed viewPager;
    private MeiZiTu meiZiTu;
    private DetailMeiziAdapter meiZiTuAdapter;
    private boolean hide=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        meiZiTu= (MeiZiTu) getIntent().getSerializableExtra("meizi");
        meiZiTuAdapter=new DetailMeiziAdapter(getSupportFragmentManager(),meiZiTu);
        viewPager.setAdapter(meiZiTuAdapter);
    }

    @Override
    public void netWorkOpen() {

    }

    @Override
    public void netWorkClose() {

    }

    @Override
    public void tap() {
        hide=!hide;
        appBarLayout.animate()
                .translationY(hide?0:-(appBarLayout.getHeight()+ StatusBarCompact.getStatusBarHeight(MeiZiDetailActivity.this)))
                .setDuration(300)
                .start();
    }
}
