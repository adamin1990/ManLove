package com.adamin.manslove.view.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adamin.manslove.R;
import com.adamin.manslove.domain.DetailData;
import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.utils.LogUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
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
 * //         Created by LiTao on 2016-02-26-22:42.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class DetailFragment extends Fragment {
    @Bind(R.id.progressbar)
    CircularProgressBar circularProgressBar;
    @Bind(R.id.imageview)
    ImageView imageView;
    PhotoViewAttacher attacher;
    DetailData detailData;
    Picasso picasso;
    TapListener tapListener;

    public static DetailFragment instance(DetailData detailData) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("detail", detailData);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        detailData = (DetailData) getArguments().getSerializable("detail");
        LogUtil.error(DetailFragment.class, detailData.getUrl() + "都自己哦");
        picasso = new Picasso.Builder(getContext())
                .build();
        picasso.with(getContext())
                .load(Constant.BASEIMGURL + detailData.getUrl())
                .into(imageView, new Callback() {
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

        return view;
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

    public interface TapListener{
        void tap();
    }
    public void setTablistener(TapListener listener){
        this.tapListener=listener;
    }
}
