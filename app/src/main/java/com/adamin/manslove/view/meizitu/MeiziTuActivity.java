package com.adamin.manslove.view.meizitu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.base.BaseActivity;

import butterknife.ButterKnife;

public class MeiziTuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi_tu);
        ButterKnife.bind(this);
    }

    @Override
    public void netWorkOpen() {

    }

    @Override
    public void netWorkClose() {

    }

}
