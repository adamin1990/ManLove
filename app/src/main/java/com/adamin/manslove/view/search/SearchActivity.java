package com.adamin.manslove.view.search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.MainFragmentAdapter;
import com.adamin.manslove.adapter.SearchAdapter;
import com.adamin.manslove.base.BaseActivity;
import com.adamin.manslove.domain.HomeData;
import com.adamin.manslove.presenter.search.SearchPresenter;
import com.adamin.manslove.utils.LogUtil;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.manslove.utils.StatusBarCompact;
import com.adamin.manslove.view.detail.DetailActivity;
import com.adamin.superrecyclerview.animator.adapters.SlideInLeftAnimationAdapter;
import com.adamin.superrecyclerview.superrecycer.OnMoreListener;
import com.adamin.superrecyclerview.superrecycer.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchView {
    private String query;
    private SearchPresenter searchPresenter;
//    @Bind(R.id.toolbar)
//    Toolbar toolbar;

    SuperRecyclerView superRecyclerView;
    private int page = 1;
    private String pagesize = "18";
    private AlertDialog alertDialog;
    private SearchAdapter adapter;
    private List<HomeData> homeDatas;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        superRecyclerView = (SuperRecyclerView) findViewById(R.id.superrecycerview);
        alertDialog= DialogUtil.buildCustomCancelDialog(SearchActivity.this,"正在搜索");
        homeDatas=new ArrayList<>();
        gridLayoutManager=new GridLayoutManager(SearchActivity.this,2);
        adapter=new SearchAdapter(homeDatas);
        SlideInLeftAnimationAdapter slideInLeftAnimationAdapter=new SlideInLeftAnimationAdapter(adapter);
        superRecyclerView.setLayoutManager(gridLayoutManager);
        superRecyclerView.setAdapter(slideInLeftAnimationAdapter);
        StatusBarCompact.compat(SearchActivity.this,R.color.colorPrimary);
        searchPresenter = new SearchPresenter(this);
        searchPresenter.fetchData(this,query,page,pagesize);
        superRecyclerView.setOnMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                page++;
                searchPresenter.fetchData(this,query,page,pagesize);
            }
        });
        adapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, View view) {
                ActivityOptionsCompat activityOptionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(SearchActivity.this,view,"sharedimage");
                Intent intent=new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("id",homeDatas.get(position).getId()+"");
                intent.putExtra("sh","after");
                ActivityCompat.startActivity(SearchActivity.this,intent,activityOptionsCompat.toBundle());
            }
        });


    }

    @Override
    public void netWorkOpen() {

    }

    @Override
    public void netWorkClose() {

    }

    @Override
    public void showSearching() {
        alertDialog.show();

    }

    @Override
    public void hideSearching() {
        alertDialog.dismiss();

    }

    @Override
    public void setData(List<HomeData> homeDatas) {
         this.homeDatas.addAll(homeDatas);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(SearchActivity.this,"加载出错"+e.getMessage(),SnackBarUtils.ERROR);

    }

    @Override
    public void hideError() {
        /*do nothing!*/

    }

    @Override
    protected void onDestroy() {
        searchPresenter.cancel(this);
        super.onDestroy();
    }
}
