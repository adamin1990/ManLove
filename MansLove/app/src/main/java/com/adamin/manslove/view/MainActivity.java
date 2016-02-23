package com.adamin.manslove.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.MainAdapter;
import com.adamin.manslove.base.BaseActivity;
import com.adamin.manslove.domain.TabModel;
import com.adamin.manslove.presenter.main.MainPresenter;
import com.adamin.manslove.service.NetWorkService;
import com.adamin.manslove.utils.LogUtil;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.manslove.view.main.MainView;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {
    @Bind(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;
    @Bind(R.id.pager)
    ViewPager viewPager;
    @Bind(R.id.layout_error)
    View errorview;
    private MainAdapter adapter;
    private AlertDialog alertDialog;
    private MainPresenter mainPresenter;
    private List<TabModel> tabModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }


    private void init() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        alertDialog = DialogUtil.buildCustomDialog(MainActivity.this, "正在获取tab数据,请稍后");
        tabModels = new ArrayList<>();
        adapter = new MainAdapter(getSupportFragmentManager(), tabModels);
        viewPager.setAdapter(adapter);
        errorview.setVisibility(View.GONE);
        mainPresenter = new MainPresenter(this);
        mainPresenter.fetchData(this);
        startService(new Intent(MainActivity.this, NetWorkService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.cancel(this);
    }

    @Override
    public void netWorkOpen() {
        LogUtil.error(MainActivity.class, "开启网络 ");
        viewPager.setVisibility(View.VISIBLE);
        errorview.setVisibility(View.GONE);
        tabModels.clear();
        adapter.notifyDataSetChanged();
        mainPresenter.fetchData(MainActivity.this);
    }

    @Override
    public void netWorkClose() {
        LogUtil.error(MainActivity.class, "关闭网络");
        viewPager.setVisibility(View.GONE);
        errorview.setVisibility(View.VISIBLE);

    }


    @Override
    public void showLoadingAlert() {
        alertDialog.show();
    }

    @Override
    public void hideLoaingAlert() {
        alertDialog.dismiss();

    }

    @Override
    public void SetTabData(String response) {
        LogUtil.error(MainActivity.class, response);

    }

    @Override
    public void SetTabData(List<TabModel> tabModels) {
        this.tabModels.addAll(tabModels);
        adapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(MainActivity.this, e.getMessage(), SnackBarUtils.ERROR);


    }
}
