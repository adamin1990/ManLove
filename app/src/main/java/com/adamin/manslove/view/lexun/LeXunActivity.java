package com.adamin.manslove.view.lexun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.LeXunAdapter;
import com.adamin.manslove.domain.lexun.LeXunData;
import com.adamin.manslove.domain.lexun.LeXunDataWrapper;
import com.adamin.manslove.presenter.lexun.LeXunPresenter;
import com.adamin.superrecyclerview.animator.adapters.SlideInLeftAnimationAdapter;
import com.adamin.superrecyclerview.superrecycer.OnMoreListener;
import com.adamin.superrecyclerview.superrecycer.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LeXunActivity extends AppCompatActivity implements LeXunView{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.superrecycer)
    SuperRecyclerView superRecyclerView;
    private int page=1;
    private List<LeXunData>  leXunDatas;
    private LeXunPresenter leXunPresenter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private LeXunAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_le_xun);
        ButterKnife.bind(this);
        init();
        leXunPresenter.getData(this,page,15);

    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("乐讯性感美图");
        leXunDatas=new ArrayList<>();
        leXunPresenter=new LeXunPresenter(this);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        adapter=new LeXunAdapter(leXunDatas);
        SlideInLeftAnimationAdapter slideInLeftAnimationAdapter=new SlideInLeftAnimationAdapter(adapter);
        superRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        superRecyclerView.setAdapter(slideInLeftAnimationAdapter);
        superRecyclerView.setOnMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                page++;
                leXunPresenter.getData(LeXunActivity.this,page,15);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(Exception error) {

    }

    @Override
    public void setData(LeXunDataWrapper dataWrapper) {
       leXunDatas.addAll(dataWrapper.getRecord());
        adapter.notifyDataSetChanged();
    }
}
