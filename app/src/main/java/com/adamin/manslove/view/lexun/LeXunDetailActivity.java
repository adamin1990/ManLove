package com.adamin.manslove.view.lexun;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.HuaShengDetailAdapter;
import com.adamin.manslove.adapter.LeXunDetailAdapter;
import com.adamin.manslove.callback.LeXunDetailCallback;
import com.adamin.manslove.domain.lexun.LeXunDataDetail;
import com.adamin.manslove.domain.lexun.LeXunDataDetailWrapper;
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

/**
 * Created by adamlee on 2016/5/5.
 */
public class LeXunDetailActivity extends AppCompatActivity implements LeXunDetailFragment.TapListener{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.pager)
    ViewPagerFixed viewPager;
    private List<LeXunDataDetail> leXunDataDetails;
    private AlertDialog alertDialog;
    private boolean hide = false;
    private int id;
    private LeXunDetailAdapter detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hua_sheng_detial);
        ButterKnife.bind(this);
        init();
        getData();
    }

    private void getData() {
        OkHttpUtils.get()
                .url(Constant.LEXUNDETAIL + id)
                .tag(this)
                .build()
                .execute(new LeXunDetailCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(LeXunDataDetailWrapper response) {
                        leXunDataDetails.addAll(response.getRecord());
                        detailAdapter.notifyDataSetChanged();
                    }
                });

    }

    private void init() {
        id = getIntent().getIntExtra("id", 21461);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        alertDialog = DialogUtil.buildCustomCancelDialog(LeXunDetailActivity.this, "正在加载数据");
        leXunDataDetails = new ArrayList<>();
        detailAdapter = new LeXunDetailAdapter(getSupportFragmentManager(), leXunDataDetails);
        viewPager.setAdapter(detailAdapter);

    }

    @Override
    public void tap() {
        hide = !hide;
        appBarLayout.animate()
                .translationY(hide ? 0 : -(appBarLayout.getHeight() + StatusBarCompact.getStatusBarHeight(LeXunDetailActivity.this)))
                .setDuration(300)
                .start();
    }
}
