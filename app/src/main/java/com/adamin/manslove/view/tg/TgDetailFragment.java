package com.adamin.manslove.view.tg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adamin.manslove.R;
import com.adamin.manslove.domain.tg.TgDetailData;
import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.utils.SnackBarUtils;
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

/**
 * Created by adamlee on 2016/4/16.
 */
public class TgDetailFragment extends Fragment {

    private TgDetailData tgDetailData;

    @Bind(R.id.progressbar)
    CircularProgressBar circularProgressBar;
    @Bind(R.id.imageview)
    ImageView imageView;
    PhotoViewAttacher attacher;
    Picasso picasso;

    TapListener tapListener;
    String url = null;

    private static final int WRITE_EXTERNAL_PERMISSIONS_REQUEST = 0X2;

    public static Fragment newInstance(TgDetailData tgDetailData) {
        TgDetailFragment tgDetailFragment = new TgDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", tgDetailData);
        tgDetailFragment.setArguments(bundle);
        return tgDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tgDetailData = (TgDetailData) getArguments().getSerializable("data");
        picasso = new Picasso.Builder(getContext())
                .build();
        picasso.load(Constant.TGIMGPREFIX+tgDetailData.getSrc()).
                into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        circularProgressBar.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        attacher=new PhotoViewAttacher(imageView);
                        attacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                            @Override
                            public void onPhotoTap(View view, float x, float y) {
                                if(tapListener!=null){
                                    tapListener.tap();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError() {
                        circularProgressBar.setVisibility(View.GONE);
                    }
                });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_down) {
            if (Build.VERSION.SDK_INT >= 23) {
                checkPermisson();
            } else {
                downloadImg();
            }
        } else if (item.getItemId() == R.id.menu_share) {
            getUri();


        }
        return super.onOptionsItemSelected(item);
    }

    private String getRandomFile() {
        String s = UUID.randomUUID().toString();

        return s + ".png";
    }

    private void downloadImg() {
        OkHttpUtils.get()
                .url(Constant.TGIMGPREFIX+tgDetailData.getSrc())
                .tag(this)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath() + "/manlove", getRandomFile()) {
                    @Override
                    public void inProgress(float progress) {

                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        SnackBarUtils.showSnackBar(getActivity(), "下载出错", SnackBarUtils.ERROR);

                    }

                    @Override
                    public void onResponse(File response) {
                        if (response.exists()) {
                            SnackBarUtils.showSnackBar(getActivity(), "成功下载到" + response.getPath(), SnackBarUtils.SUCCESS);

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
    }

    private String getUri() {
        OkHttpUtils.get()
                .url(Constant.TGIMGPREFIX+tgDetailData.getSrc())
                .tag(this)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath() + "/manlove", getRandomFile()) {
                    @Override
                    public void inProgress(float progress) {

                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        SnackBarUtils.showSnackBar(getActivity(), "分享出错", SnackBarUtils.ERROR);

                    }

                    @Override
                    public void onResponse(File response) {
                        if (response.exists()) {
                            SnackBarUtils.showSnackBar(getActivity(), "即将分享", SnackBarUtils.SUCCESS);
                            url = response.getAbsolutePath();
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
        return url;
    }


    public interface TapListener{
        void tap();
    }
    public void setTablistener(TapListener listener){
        this.tapListener=listener;
    }



    private void checkPermisson() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_PERMISSIONS_REQUEST);
        }
    }

}
