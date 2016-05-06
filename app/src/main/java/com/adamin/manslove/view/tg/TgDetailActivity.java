package com.adamin.manslove.view.tg;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.TgDetailAdapter;
import com.adamin.manslove.callback.TgDetailCallback;
import com.adamin.manslove.domain.tg.TgDetailData;
import com.adamin.manslove.domain.tg.TgDetailDataWrapper;
import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.manslove.utils.ViewPagerFixed;
import com.zhy.http.okhttp.OkHttpUtils;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

public class TgDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewpager)
    ViewPagerFixed viewPagerFixed;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private TgDetailAdapter tgDetailAdapter;
    private List<TgDetailData> tgDetailDatas;
    private int id;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tg_detail);
        ButterKnife.bind(this);
        init();
        getData();
    }

    private void getData() {
        OkHttpUtils.get()
                .url(Constant.TGIMGLIST + id)
                .tag(this)
                .build()
                .execute(new TgDetailCallback() {
                    @Override
                    public void onBefore(Request request) {
                        alertDialog.show();
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter() {
                        alertDialog.dismiss();
                        super.onAfter();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        SnackBarUtils.showSnackBar(TgDetailActivity.this, "加载出错", SnackBarUtils.ERROR);

                    }

                    @Override
                    public void onResponse(TgDetailDataWrapper response) {
                        tgDetailDatas.addAll(response.getList());
                        tgDetailAdapter.notifyDataSetChanged();
                        getSupportActionBar().setTitle(response.getTitle()+"");

                    }
                });
    }

    private void init() {
        id=getIntent().getIntExtra("id",1);
        alertDialog = DialogUtil.buildCustomCancelDialog(TgDetailActivity.this, "正在加载图片列表");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tgDetailDatas = new ArrayList<>();
        tgDetailAdapter = new TgDetailAdapter(getSupportFragmentManager(), tgDetailDatas);
        viewPagerFixed.setAdapter(tgDetailAdapter);
        viewPagerFixed.setOffscreenPageLimit(10);
        setupBannerAd();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * 设置广告条广告
     */
    private void setupBannerAd() {
        //		/**
        //		 * 普通布局
        //		 */
        //		//　实例化广告条
        //		AdView adView = new AdView(mContext, AdSize.FIT_SCREEN);
        //		LinearLayout bannerLayout = (LinearLayout) findViewById(R.id.ll_banner);
        //		bannerLayout.addView(adView);
        /**
         * 悬浮布局
         */
        // 实例化LayoutParams(重要)
        FrameLayout.LayoutParams layoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        //　设置广告条的悬浮位置，这里示例为右下角
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
//        layoutParams.topMargin=56;
        //　实例化广告条
        AdView adView = new AdView(TgDetailActivity.this, AdSize.FIT_SCREEN);
        // 监听广告条接口
        adView.setAdListener(new AdViewListener() {

            @Override
            public void onSwitchedAd(AdView adView) {
//                Log.i(TAG, "广告条切换");
            }

            @Override
            public void onReceivedAd(AdView adView) {
//                Log.i(TAG, "请求广告条成功");
            }

            @Override
            public void onFailedToReceivedAd(AdView adView) {
//                Log.i(TAG, "请求广告条失败");
            }
        });
        // 调用Activity的addContentView函数
        addContentView(adView, layoutParams);
    }
}
