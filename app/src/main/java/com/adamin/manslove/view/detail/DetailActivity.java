package com.adamin.manslove.view.detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.DetailAdapter;
import com.adamin.manslove.domain.DetailData;
import com.adamin.manslove.model.detail.AdmodelImpl;
import com.adamin.manslove.permission.Func;
import com.adamin.manslove.permission.Func2;
import com.adamin.manslove.permission.PermissionUtil;
import com.adamin.manslove.presenter.detail.DetailPresenter;
import com.adamin.manslove.utils.ApiUtils;
import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.utils.LogUtil;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.manslove.utils.StatusBarCompact;
import com.adamin.manslove.utils.ViewPagerFixed;
//import com.iflytek.voiceads.AdError;
//import com.iflytek.voiceads.AdKeys;
//import com.iflytek.voiceads.IFLYAdListener;
//import com.iflytek.voiceads.IFLYAdSize;
//import com.iflytek.voiceads.IFLYBannerAd;
import com.iflytek.voiceads.AdError;
import com.iflytek.voiceads.AdKeys;
import com.iflytek.voiceads.IFLYAdListener;
import com.iflytek.voiceads.IFLYAdSize;
import com.iflytek.voiceads.IFLYBannerAd;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class DetailActivity extends AppCompatActivity implements DetailView,DetailFragment.TapListener{
    private static final int CODE_ADS = 0X5;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.pager)
    ViewPagerFixed viewPager;
    @Bind(R.id.imageview)
    ImageView imageView;
    private String id;
    private String preid;
    private String nextid;
    private String title="";
    private List<DetailData> detailDatas;
    private DetailAdapter detailAdapter;
    private DetailPresenter detailPresenter;
    private AlertDialog alertDialog;
    private int currentstate=0;
    EdgeEffectCompat leftEdge,rightEdge;
    private boolean hide=false;
    private String sh="after";
    private boolean justone=false;
   @Bind(R.id.layout_adview)
    LinearLayout layout_ads;
    private IFLYBannerAd bannerView;
    private PermissionUtil.PermissionRequestObject permissionRequestObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        id=getIntent().getStringExtra("id");
        sh=getIntent().getStringExtra("sh");
        init();
        if(justone){
            imageView.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
         String s=Constant.BASEIMGURL+getIntent().getStringExtra("url");
            Picasso.with(DetailActivity.this)
                    .load(Constant.BASEIMGURL+getIntent().getStringExtra("url")).into(imageView);

        }else{
            imageView.setVisibility(View.GONE);
            viewPager.setVisibility(View.VISIBLE);
            getData(id);
        }

        initlistener();

        if (Build.VERSION.SDK_INT >= 23) {
//            checkPermisson();
            permissionRequestObject=PermissionUtil.with(this).request(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .onAllGranted(new Func() {
                        @Override
                        protected void call() {
                            LogUtil.error(DetailActivity.class,"都通过了");
                            createBannerAd();
                        }
                    })
                    .onAnyDenied(new Func() {
                        @Override
                        protected void call() {
                            Toast.makeText(DetailActivity.this,"你禁止了某些权限",Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    })
//                    .onResult(new Func2() {
//                        @Override
//                        protected void call(int requestCode, String[] permissions, int[] grantResults) {
//                            for (int i = 0; i < permissions.length; i++) {
//                                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) doOnPermissionGranted(permissions[i]);
//                                else doOnPermissionDenied(permissions[i]);
//                            }
//                        }
//                    })
                    .ask(CODE_ADS);

        } else {
                    createBannerAd();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(permissionRequestObject!=null){
            permissionRequestObject.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initlistener() {
        try {
            Field leftEdgeField = viewPager.getClass().getSuperclass().getDeclaredField("mLeftEdge");
            Field rightEdgeField = viewPager.getClass().getSuperclass().getDeclaredField("mRightEdge");
            if (leftEdgeField != null && rightEdgeField != null) {
                leftEdgeField.setAccessible(true);
                rightEdgeField.setAccessible(true);
                leftEdge = (EdgeEffectCompat) leftEdgeField.get(viewPager);
                rightEdge = (EdgeEffectCompat) rightEdgeField.get(viewPager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                LogUtil.error(DetailActivity.class,"offset"+positionOffset+"pixel"+positionOffsetPixels);
                if(position==0){
                    if(currentstate==1&&position==0){
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.error(DetailActivity.class,"select"+position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

                currentstate=state;
                if(rightEdge!=null&&!rightEdge.isFinished()){//到了最后一张并且还继续拖动，出现蓝色限制边条了
                    LogUtil.error(DetailActivity.class,"最后一页还在拖动"+state);
                    Intent intent=new Intent(DetailActivity.this,DetailActivity.class);
                    intent.putExtra("id",nextid);
                    intent.putExtra("sh","after");
                    startActivity(intent);
                    finish();

                }
                if(leftEdge!=null&&!leftEdge.isFinished()){
                    LogUtil.error(DetailActivity.class,"第一页还在拖动"+state);
                    Intent intent=new Intent(DetailActivity.this,DetailActivity.class);
                    intent.putExtra("id",preid);
                    intent.putExtra("sh","before");
                    startActivity(intent);
                    finish();
                }

            }
        });

//        new AdmodelImpl().loadAd(mAdListener,bannerView,layout_ads,"847182F1E4B28774525113F0C57174E7",DetailActivity.this);
    }

    private void getData(String id) {
        detailPresenter.fetchData(this,id);
    }

    private void init() {
        justone=getIntent().getBooleanExtra("justone",false);
        detailPresenter=new DetailPresenter(this);
        detailDatas=new ArrayList<>();
        detailAdapter=new DetailAdapter(getSupportFragmentManager(),detailDatas);
        viewPager.setAdapter(detailAdapter);
        alertDialog= DialogUtil.buildCustomDialog(DetailActivity.this,"正在加载数据列表，请稍后");
        StatusBarCompact.compat(this, Color.parseColor("#424242"));


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
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            ActivityCompat.finishAfterTransition(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setData(List<DetailData> detailDatas) {
        this.detailDatas.addAll(detailDatas);
        detailAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(sh.equals("before")?(detailDatas.size()-1):0);

    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(DetailActivity.this,"出错了"+e.getMessage(),SnackBarUtils.ERROR);

    }

    @Override
    public void showNoDataWithLimit() {
        SnackBarUtils.showSnackBar(DetailActivity.this,"到头了",SnackBarUtils.ERROR);
    }

    @Override
    public void getIds(String before, String after,String title) {
        preid=before;
        nextid=after;
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showAd() {

    }

    @Override
    public void hideAd() {

    }

    @Override
    protected void onDestroy() {
        detailPresenter.cancel(this);
        super.onDestroy();
    }
    public void setData(List<DetailData> detailDatas,int pos){
        this.detailDatas.addAll(pos,detailDatas);
        detailAdapter.notifyDataSetChanged();
    }

    @Override
    public void tap() {
        hide=!hide;
        appBarLayout.animate()
                .translationY(hide?0:-(appBarLayout.getHeight()+StatusBarCompact.getStatusBarHeight(DetailActivity.this)))
                .setDuration(300)
                .start();

    }

    IFLYAdListener mAdListener = new IFLYAdListener(){

        /**
         * 广告请求成功
         */
        @Override
        public void onAdReceive() {
            //展示广告
//            bannerView.showAd();

        }

        /**
         * 广告请求失败
         */
        @Override
        public void onAdFailed(AdError error) {
        }

        /**
         * 广告被点击
         */
        @Override
        public void onAdClick() {
        }

        /**
         * 广告被关闭
         */
        @Override
        public void onAdClose() {
        }

        @Override
        public void onAdExposure() {
            // TODO Auto-generated method stub

        }
    };

    public void createBannerAd() {
        //此广告位为Demo专用，广告的展示不产生费用
        String adUnitId = "847182F1E4B28774525113F0C57174E7";
        //创建旗帜广告，传入广告位ID
        bannerView = IFLYBannerAd.createBannerAd(this, adUnitId);
        //设置请求的广告尺寸
        bannerView.setAdSize(IFLYAdSize.BANNER);
        //设置下载广告前，弹窗提示
        bannerView.setParameter(AdKeys.DOWNLOAD_ALERT, "true");

        //请求广告，添加监听器
        bannerView.loadAd(mAdListener);
        //将广告添加到布局
//        layout_ads = (LinearLayout)findViewById(R.id.layout_adview);
        layout_ads.removeAllViews();
        layout_ads.addView(bannerView);

    }
}
