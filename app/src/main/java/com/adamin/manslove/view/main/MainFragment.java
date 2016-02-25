package com.adamin.manslove.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.MainFragmentAdapter;
import com.adamin.manslove.domain.HomeData;
import com.adamin.manslove.domain.TabModel;
import com.adamin.manslove.presenter.main.MainFragmentPresenter;
import com.adamin.manslove.utils.LogUtil;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.superrecyclerview.animator.adapters.SlideInBottomAnimationAdapter;
import com.adamin.superrecyclerview.superrecycer.OnMoreListener;
import com.adamin.superrecyclerview.superrecycer.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

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
 * //         Created by LiTao on 2016-02-23-14:13.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class MainFragment extends Fragment implements MainFragmentView {


    SuperRecyclerView superRecyclerView;
    private MainFragmentPresenter mainFragmentPresenter;
    private int page = 1;
    private String pagesize = "18";
    private TabModel tabModel;
    private GridLayoutManager gridLayoutManager;
    private MainFragmentAdapter mainFragmentAdapter;
    private List<HomeData> homeDatas;


    public static MainFragment getInstance(TabModel tabModel) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("tabmodel", tabModel);
        mainFragment.setArguments(bundle);

        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_home, container, false);
        ButterKnife.bind(this, view);
        superRecyclerView = (SuperRecyclerView) view.findViewById(R.id.superrecycer);
        mainFragmentPresenter = new MainFragmentPresenter(this);
        homeDatas = new ArrayList<>();
        mainFragmentAdapter = new MainFragmentAdapter(homeDatas);
        SlideInBottomAnimationAdapter slideInBottomAnimationAdapter = new SlideInBottomAnimationAdapter(mainFragmentAdapter);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        superRecyclerView.setLayoutManager(gridLayoutManager);
        superRecyclerView.setAdapter(slideInBottomAnimationAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tabModel = (TabModel) getArguments().getSerializable("tabmodel");
        mainFragmentPresenter.getData(MainFragment.this, page, pagesize, "", tabModel);
        superRecyclerView.setOnMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                page++;
                mainFragmentPresenter.getData(MainFragment.this, page, pagesize, "", tabModel);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setListData(String listData) {
        LogUtil.error(MainFragment.class, listData);

    }

    @Override
    public void setListData(List<HomeData> listData) {
        homeDatas.addAll(listData);
        mainFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMore() {

    }

    @Override
    public void hideMore() {

    }

    @Override
    public void showError(Exception e) {

        SnackBarUtils.showSnackBar(getActivity(), e.getMessage(), SnackBarUtils.ERROR);


    }
}
