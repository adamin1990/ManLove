package com.adamin.manslove.view.gank;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.GankAdapter;
import com.adamin.manslove.domain.GanMeiZi;
import com.adamin.manslove.domain.GanMeiZiWrapper;
import com.adamin.manslove.presenter.gank.GanMeiZiPresenter;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.manslove.utils.StatusBarCompact;
import com.adamin.manslove.view.liebao.LieBaoAlbumDetailActivity;
import com.adamin.superrecyclerview.animator.adapters.SlideInLeftAnimationAdapter;
import com.adamin.superrecyclerview.superrecycer.OnMoreListener;
import com.adamin.superrecyclerview.superrecycer.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GankActivity extends AppCompatActivity implements GankView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerview)
    SuperRecyclerView superRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private GanMeiZiPresenter ganMeiZiPresenter;
    private GankAdapter gankAdapter;
    private List<GanMeiZi> ganMeiZis;
    private AlertDialog alertDialog;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        ButterKnife.bind(this);
        init();
        ganMeiZiPresenter.getMeiZi(page, this);

    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gank.IO");
        alertDialog = DialogUtil.buildCustomDialog(GankActivity.this, "正在加载数据");
        gridLayoutManager = new GridLayoutManager(GankActivity.this, 2);
        ganMeiZiPresenter = new GanMeiZiPresenter(this);
        ganMeiZis = new ArrayList<>();
        gankAdapter = new GankAdapter(ganMeiZis);
        SlideInLeftAnimationAdapter animationAdapter = new SlideInLeftAnimationAdapter(gankAdapter);
        superRecyclerView.setLayoutManager(gridLayoutManager);
        superRecyclerView.setAdapter(animationAdapter);
        superRecyclerView.setRefreshingColorResources(R.color.md_blue_500, R.color.md_deep_orange_500, R.color.md_green_500,
                R.color.md_pink_500);
        superRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                ganMeiZis.clear();
                gankAdapter.notifyDataSetChanged();
                ganMeiZiPresenter.getMeiZi(page, GankActivity.this);

            }
        });
        superRecyclerView.setOnMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                page++;
                ganMeiZiPresenter.getMeiZi(page, GankActivity.this);
            }
        });
        StatusBarCompact.compat(GankActivity.this, Color.parseColor("#424242"));
        gankAdapter.setOnItemClickListener(new GankAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, View view) {
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(GankActivity.this, view, "sharedimage");
                Intent intent = new Intent(GankActivity.this, GankDetailActivity.class);
                intent.putExtra("ganmeizi", ganMeiZis.get(position));
                startActivity(intent);
//                ActivityCompat.startActivity(GankActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        alertDialog.show();

    }

    @Override
    public void hideProgress() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }

    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(GankActivity.this, e.getMessage() + "", SnackBarUtils.ERROR);

    }

    @Override
    public void setMeiZi(GanMeiZiWrapper ganMeiZiWrapper) {
        if (ganMeiZiWrapper.getError() == false) {
            ganMeiZis.addAll(ganMeiZiWrapper.getResults());
            gankAdapter.notifyDataSetChanged();
        } else {
            SnackBarUtils.showSnackBar(GankActivity.this, "木有了", SnackBarUtils.INFO);
        }

    }

    @Override
    protected void onDestroy() {
        if (ganMeiZiPresenter != null) {
            ganMeiZiPresenter.cancelGetMeiz(GankActivity.this);
        }
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        super.onDestroy();
    }
}
