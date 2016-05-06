package com.adamin.manslove.view.huasheng;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.HuaShengFragmentAdapter;
import com.adamin.manslove.utils.ViewPagerFixed;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HuashengActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPagerFixed pagerFixed;
    String [] ids={"3","8","5","1","6","7","9","10","11","12"};
    String [] names={"性感","内衣","美腿","美模","日韩","欧美","萝莉","制服","清纯","气质"};
    private HuaShengFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huasheng);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("华声");
        adapter=new HuaShengFragmentAdapter(getSupportFragmentManager(),ids,names);
        pagerFixed.setAdapter(adapter);
        tabLayout.setupWithViewPager(pagerFixed);
        pagerFixed.setOffscreenPageLimit(10);
    }


}
