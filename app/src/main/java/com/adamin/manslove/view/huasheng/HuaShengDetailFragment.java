package com.adamin.manslove.view.huasheng;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.adamin.manslove.R;
import com.adamin.manslove.domain.DetailData;
import com.adamin.manslove.domain.huasheng.HuaShengImageList;
import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.utils.LogUtil;
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
 * Created by adamlee on 2016/5/5.
 */
public class HuaShengDetailFragment extends Fragment {
    private static final int WRITE_EXTERNAL_PERMISSIONS_REQUEST = 0X2;
    private static final int MSGDW = 0X3;
    private static final int MSGSHARE = 0X4;
    @Bind(R.id.progressbar)
    CircularProgressBar circularProgressBar;
    @Bind(R.id.imageview)
    ImageView imageView;
    PhotoViewAttacher attacher;
    String detailData;
    Picasso picasso;
    TapListener tapListener;
    String url = null;

    public static HuaShengDetailFragment instance(String detailData) {
        HuaShengDetailFragment detailFragment = new HuaShengDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("detail", detailData);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        detailData = getArguments().getString("detail");
        LogUtil.error(HuaShengDetailFragment.class, detailData);
        picasso = new Picasso.Builder(getContext())
                .build();
//        LogUtil.error(HuaShengDetailFragment.class,Constant.BASEIMGURL + detailData.getUrl());
        picasso.with(getContext())
                .load(detailData)
                .fit()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        circularProgressBar.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        attacher = new PhotoViewAttacher(imageView);
                        attacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                            @Override
                            public void onPhotoTap(View view, float x, float y) {
                                if (tapListener != null) {
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

        return view;
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == WRITE_EXTERNAL_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadImg();
            } else {
                Toast.makeText(getActivity(), "下载权限被拒绝了~", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof TapListener) {
            try {
                throw new Exception("必须实现Tablistener回调接口");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tapListener = (TapListener) activity;
        super.onAttach(activity);
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

    private void downloadImg() {
        OkHttpUtils.get()
                .url(detailData)
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
                .url(detailData)
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

    private String getRandomFile() {
        String s = UUID.randomUUID().toString();

        return s + ".png";
    }


    public interface TapListener {
        void tap();
    }
}
