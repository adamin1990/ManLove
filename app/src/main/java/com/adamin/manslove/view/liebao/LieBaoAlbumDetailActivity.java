package com.adamin.manslove.view.liebao;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.LieBaoAlbumDetailAdapter;
import com.adamin.manslove.base.BaseActivity;
import com.adamin.manslove.domain.LieBaoAlbum;
import com.adamin.manslove.domain.LieBaoAlbumDetail;
import com.adamin.manslove.domain.LieBaoAlbumDetailWrapper;
import com.adamin.manslove.presenter.liebao.LieBaoAlbumDetailPresenter;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.manslove.utils.StatusBarCompact;
import com.adamin.manslove.utils.ViewPagerFixed;
import com.adamin.manslove.view.detail.DetailFragment;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class LieBaoAlbumDetailActivity extends BaseActivity  implements LieBaoAlbumDetailView,LieBaoDetailFragment.TapListener{
    @Bind(R.id.viewpager)
    ViewPagerFixed pagerFixed;
    @Bind(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private PhotoViewAttacher attacher;
    private AlertDialog dialog;
    private LieBaoAlbumDetailPresenter lieBaoAlbumDetailPresenter;
    private boolean hide=false;
    private String categary,beforeid,afterid;
    private boolean before=false;
    private List<LieBaoAlbumDetail> lieBaoAlbumDetails;
    private LieBaoAlbumDetailAdapter albumDetailAdapter;
    private int temp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lie_bao_album_detail);
        ButterKnife.bind(this);
        init();
        lieBaoAlbumDetailPresenter.fetchData(this,categary,afterid,before);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        afterid=getIntent().getStringExtra("id");
        categary=getIntent().getStringExtra("category");
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        dialog= DialogUtil.buildCustomDialog(LieBaoAlbumDetailActivity.this,"正在加载，请稍候~");
        lieBaoAlbumDetailPresenter=new LieBaoAlbumDetailPresenter(this);
        lieBaoAlbumDetails=new ArrayList<>();
        albumDetailAdapter=new LieBaoAlbumDetailAdapter(getSupportFragmentManager(),lieBaoAlbumDetails);
        pagerFixed.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==lieBaoAlbumDetails.size()-1){
                    lieBaoAlbumDetailPresenter.fetchData(LieBaoAlbumDetailActivity.this,categary,afterid,before);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pagerFixed.setAdapter(albumDetailAdapter);
    }

    @Override
    public void netWorkOpen() {

    }

    @Override
    public void netWorkClose() {

    }

    @Override
    public void showLoading() {
        dialog.show();

    }

    @Override
    public void hideLoaidng() {
        if(dialog!=null){
            dialog.dismiss();
        }

    }

    @Override
    public void setDataBefore(LieBaoAlbumDetailWrapper lieBaoAlbumDetailWrapper) {
        beforeid=lieBaoAlbumDetailWrapper.getInfo().getPrev();
        afterid=lieBaoAlbumDetailWrapper.getInfo().getNext();
//        categary=lieBaoAlbumDetailWrapper.getInfo().getCategory();
    insertBefore(lieBaoAlbumDetails,lieBaoAlbumDetails.get(0),lieBaoAlbumDetailWrapper.getData());
    }

    @Override
    public void setDataAfter(LieBaoAlbumDetailWrapper lieBaoAlbumDetailWrapper) {
        beforeid=lieBaoAlbumDetailWrapper.getInfo().getPrev();
        afterid=lieBaoAlbumDetailWrapper.getInfo().getNext();
//        categary=lieBaoAlbumDetailWrapper.getInfo().getCategory();
        temp=(lieBaoAlbumDetails.size()==0?0:lieBaoAlbumDetails.size()-1);
        insertAfter(lieBaoAlbumDetails,lieBaoAlbumDetailWrapper.getData());
        albumDetailAdapter.notifyDataSetChanged();
        pagerFixed.setCurrentItem(temp,false);

    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(LieBaoAlbumDetailActivity.this,"出错了"+e.getMessage(),SnackBarUtils.ERROR);

    }

    private void hideorshowAppbar(){
        hide=!hide;
        appBarLayout.animate()
                .translationY(hide?0:-(appBarLayout.getHeight()+ StatusBarCompact.getStatusBarHeight(LieBaoAlbumDetailActivity.this)))
                .setDuration(300)
                .start();
    }
    public static void insertBefore(List<LieBaoAlbumDetail> list, LieBaoAlbumDetail reference, List<LieBaoAlbumDetail> data) {
        list.addAll(list.indexOf(reference), data);
    }
    public static void insertAfter(List<LieBaoAlbumDetail> list,List<LieBaoAlbumDetail> data) {
        list.addAll( data);
    }

    @Override
    public void tap() {
        hideorshowAppbar();
    }
}
