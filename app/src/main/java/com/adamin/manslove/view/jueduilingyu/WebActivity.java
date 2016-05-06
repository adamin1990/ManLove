package com.adamin.manslove.view.jueduilingyu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.adamin.manslove.R;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.web)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(""+getIntent().getStringExtra("title"));
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setSupportZoom(false);
//        webView.getSettings().setDisplayZoomControls(false);
        //自适应屏幕
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadDataWithBaseURL(null,getIntent().getStringExtra("content")+"","text/html","utf-8",null);
        setupBannerAd();
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
        AdView adView = new AdView(WebActivity.this, AdSize.FIT_SCREEN);
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
