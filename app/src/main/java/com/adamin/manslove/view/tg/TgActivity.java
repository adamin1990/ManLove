package com.adamin.manslove.view.tg;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.TgPagerAdapter;
import com.adamin.manslove.domain.tg.TgDataWrapper;
import com.adamin.manslove.utils.ViewPagerFixed;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TgActivity extends AppCompatActivity {
    private  int [] ids={1,2,3,4,5,6,7};
    private String [] names={"性感美女","日韩美女","丝袜美腿","美女照片","美女写真","清纯美女","性感车模"};

    private TgPagerAdapter pagerAdapter;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPagerFixed viewPagerFixed;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tg);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("天狗美图");
        pagerAdapter=new TgPagerAdapter(getSupportFragmentManager(),ids,names);
        viewPagerFixed.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPagerFixed);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
