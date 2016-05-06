package com.adamin.manslove.view.huasheng;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.HuaShengDetailAdapter;
import com.adamin.manslove.callback.HuaShengImageListCallback;
import com.adamin.manslove.domain.huasheng.HuaShengImageList;
import com.adamin.manslove.domain.huasheng.HuaShengImageListWrapper;
import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.utils.StatusBarCompact;
import com.adamin.manslove.utils.ViewPagerFixed;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class HuaShengDetialActivity extends AppCompatActivity implements HuaShengDetailFragment.TapListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.pager)
    ViewPagerFixed viewPager;
    private List<String> huaShengImageLists;
    private AlertDialog alertDialog;
    private HuaShengDetailAdapter detailAdapter;
    private boolean hide = false;
    private String tid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hua_sheng_detial);
        ButterKnife.bind(this);
        init();
        getData();
    }

    private void init() {
        tid = getIntent().getStringExtra("aid");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        huaShengImageLists = new ArrayList<>();
        alertDialog = DialogUtil.buildCustomCancelDialog(HuaShengDetialActivity.this, "正在加载数据");
        detailAdapter = new HuaShengDetailAdapter(getSupportFragmentManager(), huaShengImageLists);
        viewPager.setAdapter(detailAdapter);
    }

    private void getData() {
        OkHttpUtils.get()
                .url(Constant.HUASHENGDETAIL + tid)
                .tag(this)
                .build()
                .execute(new HuaShengImageListCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(HuaShengImageListWrapper response) {
                        huaShengImageLists.addAll(response.getImagelist().getImglist());
                        detailAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public void tap() {
        hide = !hide;
        appBarLayout.animate()
                .translationY(hide ? 0 : -(appBarLayout.getHeight() + StatusBarCompact.getStatusBarHeight(HuaShengDetialActivity.this)))
                .setDuration(300)
                .start();
//        layout_ads.animate()
//                .translationY(hide?0:layout_ads.getHeight())
//                .setDuration(300)
//                .start();
    }
}
