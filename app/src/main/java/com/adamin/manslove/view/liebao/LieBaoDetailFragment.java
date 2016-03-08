package com.adamin.manslove.view.liebao;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adamin.manslove.R;
import com.adamin.manslove.domain.LieBaoAlbumDetail;
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
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 * //
 * //         Created by LiTao on 2016-03-08-12:04.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class LieBaoDetailFragment extends Fragment {
    @Bind(R.id.progressbar)
    CircularProgressBar circularProgressBar;
    @Bind(R.id.imageview)
    ImageView imageView;
    PhotoViewAttacher attacher;

    Picasso picasso;
    TapListener tapListener;
    String url=null;
    private LieBaoAlbumDetail lieBaoAlbumDetail;

    public static  Fragment newInstance(LieBaoAlbumDetail lieBaoAlbumDetail){
        LieBaoDetailFragment fragment=new LieBaoDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("liebao",lieBaoAlbumDetail);
        fragment.setArguments(bundle);
        return fragment;
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
           lieBaoAlbumDetail= (LieBaoAlbumDetail) getArguments().getSerializable("liebao");
        picasso = new Picasso.Builder(getContext())
                .build();
        picasso.load(lieBaoAlbumDetail.getBigImage()).
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
    public void onDestroy() {
        picasso.cancelRequest(imageView);
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_down){
            OkHttpUtils.get()
                    .url(lieBaoAlbumDetail.getBigImage())
                    .tag(this)
                    .build()
                    .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath()+"/manlove",getRandomFile()) {
                        @Override
                        public void inProgress(float progress) {

                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            SnackBarUtils.showSnackBar(getActivity(),"下载出错",SnackBarUtils.ERROR);

                        }

                        @Override
                        public void onResponse(File response) {
                            if(response.exists()){
                                SnackBarUtils.showSnackBar(getActivity(),"成功下载到"+response.getPath(),SnackBarUtils.SUCCESS);

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


        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onAttach(Activity activity) {
        if(activity instanceof TapListener){
            try {
                throw  new Exception("必须实现Tablistener回调接口");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tapListener= (TapListener) activity;
        super.onAttach(activity);
    }



    private String getUri() {
        OkHttpUtils.get()
                .url(lieBaoAlbumDetail.getBigImage())
                .tag(this)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath()+"/manlove",getRandomFile()) {
                    @Override
                    public void inProgress(float progress) {

                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        SnackBarUtils.showSnackBar(getActivity(),"分享出错",SnackBarUtils.ERROR);

                    }

                    @Override
                    public void onResponse(File response) {
                        if(response.exists()){
                            SnackBarUtils.showSnackBar(getActivity(),"即将分享",SnackBarUtils.SUCCESS);
                            url=response.getAbsolutePath();
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
        String s= UUID.randomUUID().toString();

        return s+".png";
    }
    public interface TapListener{
        void tap();
    }
    public void setTablistener(TapListener listener){
        this.tapListener=listener;
    }
}
