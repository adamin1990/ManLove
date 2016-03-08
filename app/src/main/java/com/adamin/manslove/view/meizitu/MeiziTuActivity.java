package com.adamin.manslove.view.meizitu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.MeiZiTuAdapter;
import com.adamin.manslove.base.BaseActivity;
import com.adamin.manslove.domain.MeiZiTu;
import com.adamin.manslove.presenter.meizitu.MeiziTuPresenter;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.manslove.utils.StatusBarCompact;
import com.adamin.manslove.view.detail.DetailActivity;
import com.adamin.superrecyclerview.animator.adapters.SlideInBottomAnimationAdapter;
import com.adamin.superrecyclerview.animator.adapters.SlideInLeftAnimationAdapter;
import com.adamin.superrecyclerview.superrecycer.OnMoreListener;
import com.adamin.superrecyclerview.superrecycer.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MeiziTuActivity extends BaseActivity implements MeiZiTuView{

    private MeiziTuPresenter meiziTuPresenter;
    private AlertDialog alertDialog;
    SuperRecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private GridLayoutManager gridLayoutManager;
    private String sinceid="";
    private List<MeiZiTu> meiZiTus;
    private MeiZiTuAdapter meiZiTuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi_tu);
        ButterKnife.bind(this);
        recyclerView= (SuperRecyclerView) findViewById(R.id.superrecyclerview);
        init();
        meiziTuPresenter=new MeiziTuPresenter(this);
        meiziTuPresenter.fechData(sinceid,this);
        StatusBarCompact.compat(MeiziTuActivity.this, Color.parseColor("#424242"));
        recyclerView.setOnMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                meiziTuPresenter.fechData(sinceid,this);
            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("MeiziTu.com");
        alertDialog= DialogUtil.buildCustomCancelDialog(MeiziTuActivity.this,"正在加载妹子图");
        meiZiTus=new ArrayList<>();
        meiZiTuAdapter=new MeiZiTuAdapter(meiZiTus);
        SlideInBottomAnimationAdapter leftAnimationAdapter=new SlideInBottomAnimationAdapter(meiZiTuAdapter);
        gridLayoutManager=new GridLayoutManager(MeiziTuActivity.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(leftAnimationAdapter);
        meiZiTuAdapter.setOnItemClickListener(new MeiZiTuAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, View view) {
                ActivityOptionsCompat activityOptionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(MeiziTuActivity.this,view,"sharedimage");
                Intent intent=new Intent(MeiziTuActivity.this, MeiZiDetailActivity.class);
                intent.putExtra("meizi",meiZiTus.get(position));
                ActivityCompat.startActivity(MeiziTuActivity.this,intent,activityOptionsCompat.toBundle());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void netWorkOpen() {

    }

    @Override
    public void netWorkClose() {

    }

    @Override
    public void showLoading() {
        alertDialog.show();

    }

    @Override
    public void hideLoading() {
        alertDialog.dismiss();

    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(MeiziTuActivity.this,"请求出错",SnackBarUtils.ERROR);

    }

    @Override
    public void showMore() {

    }

    @Override
    public void hideMore() {

    }

    @Override
    public void setData(List<MeiZiTu> meiZiTus) {
        sinceid=meiZiTus.get(meiZiTus.size()-1).getId();
        this.meiZiTus.addAll(meiZiTus);
        meiZiTuAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onDestroy() {
        meiziTuPresenter.cancelFetch(this);
        super.onDestroy();
    }
}
