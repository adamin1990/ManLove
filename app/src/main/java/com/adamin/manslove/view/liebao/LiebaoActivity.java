package com.adamin.manslove.view.liebao;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.LieBaoFragmentAdapter;
import com.adamin.manslove.base.BaseActivity;
import com.adamin.manslove.domain.LieBaoTab;
import com.adamin.manslove.presenter.liebao.LieBaoTagPresenter;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.manslove.utils.StatusBarCompact;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class LiebaoActivity extends BaseActivity implements LieBaoView{
   @Bind(R.id.toolbar)
   Toolbar toolbar;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    private LieBaoTagPresenter lieBaoTagPresenter;
    private AlertDialog alertDialog;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    LieBaoFragmentAdapter adapter;
    private List<LieBaoTab> lieBaoTabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liebao);
        ButterKnife.bind(this);
        init();
        lieBaoTagPresenter.getTags(this);

    }

    @Override
    public void netWorkOpen() {

    }

    @Override
    public void netWorkClose() {

    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("猎豹美图");
        StatusBarCompact.compat(LiebaoActivity.this, Color.parseColor("#424242"));
        alertDialog= DialogUtil.buildCustomCancelDialog(LiebaoActivity.this,"正在加载tab内容");
        lieBaoTagPresenter=new LieBaoTagPresenter(this);
        lieBaoTabs=new ArrayList<>();
        viewPager.setOffscreenPageLimit(10);
      adapter=new LieBaoFragmentAdapter(getSupportFragmentManager(),lieBaoTabs);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        alertDialog.show();

    }

    @Override
    public void hideLoading() {
     if(alertDialog!=null){
         alertDialog.dismiss();
     }
    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(LiebaoActivity.this,"出错了"+e.getMessage(),SnackBarUtils.ERROR);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setData(List<LieBaoTab> data) {
        lieBaoTabs.addAll(data);
        adapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onDestroy() {
        if(alertDialog!=null){
            alertDialog.dismiss();
        }
        if(lieBaoTagPresenter!=null){
            lieBaoTagPresenter.cancelGet(this);
        }
        super.onDestroy();
    }
}
