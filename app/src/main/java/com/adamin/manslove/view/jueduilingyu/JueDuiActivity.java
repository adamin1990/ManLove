package com.adamin.manslove.view.jueduilingyu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.JueDuiFragmentAdapter;
import com.adamin.manslove.utils.ViewPagerFixed;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JueDuiActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPagerFixed pagerFixed;
    String [] names={"最新","专题","特点","弄潮","Cos","写真"};
    String [] types={"","category/专题","category/特点","category/弄潮","category/cosplay","category/写真"};
    private JueDuiFragmentAdapter duiFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jue_dui);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("绝对领域");
        duiFragmentAdapter=new JueDuiFragmentAdapter(getSupportFragmentManager(),names,types);
        pagerFixed.setAdapter(duiFragmentAdapter);
        tabLayout.setupWithViewPager(pagerFixed);
        pagerFixed.setOffscreenPageLimit(6);

    }

}
