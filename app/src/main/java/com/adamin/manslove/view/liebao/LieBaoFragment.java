package com.adamin.manslove.view.liebao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.LieBaoAlbumAdapter;
import com.adamin.manslove.domain.LieBaoAlbum;
import com.adamin.manslove.presenter.liebao.LieBaoAlbumPresenter;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.manslove.view.detail.DetailActivity;
import com.adamin.superrecyclerview.animator.adapters.SlideInRightAnimationAdapter;
import com.adamin.superrecyclerview.superrecycer.OnMoreListener;
import com.adamin.superrecyclerview.superrecycer.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

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
 * //         Created by LiTao on 2016-03-07-18:17.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class LieBaoFragment extends Fragment implements LieBaoFragmentView {
    @Bind(R.id.superrecycer)
    SuperRecyclerView superRecyclerView;
    private int id;
    private String name;
    private LieBaoAlbumPresenter lieBaoAlbumPresenter;
    private int page = 1;
    private LieBaoAlbumAdapter adapter;
    private List<LieBaoAlbum> lieBaoAlbumList;
    private GridLayoutManager gridLayoutManager;
    private AlertDialog alertDialog;

    public static Fragment newInstance(int id, String name) {
        LieBaoFragment lieBaoFragment = new LieBaoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("name", name);
        lieBaoFragment.setArguments(bundle);
        return lieBaoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_home, container, false);
        ButterKnife.bind(this, view);
        id = getArguments().getInt("id", 10);
        name = getArguments().getString("name");
        alertDialog = DialogUtil.buildCustomCancelDialog(getActivity(), "图片列表加载中");
        lieBaoAlbumPresenter = new LieBaoAlbumPresenter(this);
        lieBaoAlbumList = new ArrayList<>();
        adapter = new LieBaoAlbumAdapter(lieBaoAlbumList);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        superRecyclerView.setLayoutManager(gridLayoutManager);
        SlideInRightAnimationAdapter animationAdapter = new SlideInRightAnimationAdapter(adapter);
        superRecyclerView.setAdapter(animationAdapter);
        lieBaoAlbumPresenter.fetchData(page, id, this);
        superRecyclerView.setOnMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                page++;
                lieBaoAlbumPresenter.fetchData(page, id, this);
            }
        });
        adapter.setOnItemClickListener(new LieBaoAlbumAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, View view) {
                ActivityOptionsCompat activityOptionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),view,"sharedimage");
                Intent intent=new Intent(getActivity(), LieBaoAlbumDetailActivity.class);
                intent.putExtra("category",id+"");
                intent.putExtra("name",lieBaoAlbumList.get(position).getTitle());
                intent.putExtra("id",lieBaoAlbumList.get(position).getId());
                ActivityCompat.startActivity(getActivity(),intent,null);

            }
        });
        return view;
    }

    @Override
    public void showLoading() {
        alertDialog.show();

    }

    @Override
    public void hideLoading() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }

    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(getActivity(), e.getMessage() + "", SnackBarUtils.ERROR);

    }

    @Override
    public void setData(List<LieBaoAlbum> lieBaoAlbumList) {
        this.lieBaoAlbumList.addAll(lieBaoAlbumList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroy() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        if (lieBaoAlbumPresenter != null) {
            lieBaoAlbumPresenter.candel(this);
        }
        super.onDestroy();
    }
}
