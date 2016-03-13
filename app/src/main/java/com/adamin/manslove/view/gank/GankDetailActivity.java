package com.adamin.manslove.view.gank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.adamin.manslove.R;
import com.adamin.manslove.domain.GanMeiZi;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.manslove.utils.StatusBarCompact;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import okhttp3.Call;
import okhttp3.Request;
import uk.co.senab.photoview.PhotoViewAttacher;

public class GankDetailActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.imageview)
    ImageView imageView;
    @Bind(R.id.progressbar)
    CircularProgressBar circularProgressBar;
    @Bind(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    PhotoViewAttacher photoViewAttacher;
    private GanMeiZi ganMeiZi;
    private boolean hide = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ganMeiZi = (GanMeiZi) getIntent().getSerializableExtra("ganmeizi");
        getSupportActionBar().setTitle(ganMeiZi.getCreatedAt()+"");
        circularProgressBar.setVisibility(View.VISIBLE);
        Picasso.with(GankDetailActivity.this)
                .load(ganMeiZi.getUrl())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        circularProgressBar.setVisibility(View.GONE);
                        photoViewAttacher = new PhotoViewAttacher(imageView);
                        photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                            @Override
                            public void onPhotoTap(View view, float x, float y) {
                                hideorshowAppbar();
                            }
                        });
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_down){
            OkHttpUtils.get()
                    .url(ganMeiZi.getUrl())
                    .tag(this)
                    .build()
                    .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath()+"/manlove",getRandomFile()) {
                        @Override
                        public void inProgress(float progress) {

                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            SnackBarUtils.showSnackBar(GankDetailActivity.this,"下载出错",SnackBarUtils.ERROR);

                        }

                        @Override
                        public void onResponse(File response) {
                            if(response.exists()){
                                SnackBarUtils.showSnackBar(GankDetailActivity.this,"成功下载到"+response.getPath(),SnackBarUtils.SUCCESS);

                            }

                        }

                        @Override
                        public void onBefore(Request request) {
                            circularProgressBar.setVisibility(View.VISIBLE);
                            super.onBefore(request);
                        }

                        @Override
                        public void onAfter() {
                            circularProgressBar.setVisibility(View.GONE);
                            super.onAfter();
                        }
                    });
        }else if(item.getItemId()==R.id.menu_share)
        {    getUri();


        }else if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    private String getUri() {
        OkHttpUtils.get()
                .url(ganMeiZi.getUrl())
                .tag(this)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath()+"/manlove",getRandomFile()) {
                    @Override
                    public void inProgress(float progress) {

                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        SnackBarUtils.showSnackBar(GankDetailActivity.this,"分享出错",SnackBarUtils.ERROR);

                    }

                    @Override
                    public void onResponse(File response) {
                        if(response.exists()){
                            SnackBarUtils.showSnackBar(GankDetailActivity.this,"即将分享",SnackBarUtils.SUCCESS);
//                            url=response.getAbsolutePath();
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(response));
                            shareIntent.setType("image/png");
                            startActivity(Intent.createChooser(shareIntent, "发送到"));
                        }

                    }

                    @Override
                    public void onBefore(Request request) {
                        circularProgressBar.setVisibility(View.VISIBLE);
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter() {
                        circularProgressBar.setVisibility(View.GONE);
                        super.onAfter();
                    }
                });
        return "";
    }


    private void hideorshowAppbar() {
        hide = !hide;
        appBarLayout.animate()
                .translationY(hide ? 0 : -(appBarLayout.getHeight() + StatusBarCompact.getStatusBarHeight(GankDetailActivity.this)))
                .setDuration(300)
                .start();
    }


    private String getRandomFile() {
        String s= UUID.randomUUID().toString();

        return s+".png";
    }
}
