package com.adamin.manslove.view.huasheng;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.HuaShengMainImgAdapter;
import com.adamin.manslove.domain.huasheng.HuaShengData;
import com.adamin.manslove.domain.huasheng.HuaShengInnerWrapper;
import com.adamin.manslove.presenter.huasheng.HuaShengMainPresenter;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.superrecyclerview.animator.adapters.SlideInLeftAnimationAdapter;
import com.adamin.superrecyclerview.superrecycer.OnMoreListener;
import com.adamin.superrecyclerview.superrecycer.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adamlee on 2016/3/29.
 */
public class HuaShengListFragment extends Fragment  implements HuaShengMainView{
  private HuaShengMainPresenter presenter;
    private GridLayoutManager gridLayoutManager;
    private String id;
    private String name;
    @Bind(R.id.superrecycer)
    SuperRecyclerView superRecyclerView;
    private List<HuaShengData> huaShengDatas;
    private HuaShengMainImgAdapter adapter;
    private int page=1;
    private AlertDialog alertDialog;

    public static Fragment newInstance(String id,String name){
        HuaShengListFragment fragment=new HuaShengListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        bundle.putString("name",name);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_home, container, false);
        ButterKnife.bind(this, view);
        alertDialog= DialogUtil.buildCustomCancelDialog(getActivity(),"加载中");
        gridLayoutManager=new GridLayoutManager(getActivity(),2);
        huaShengDatas=new ArrayList<>();
        adapter=new HuaShengMainImgAdapter(huaShengDatas);
        SlideInLeftAnimationAdapter slideInLeftAnimationAdapter=new SlideInLeftAnimationAdapter(adapter);
        id=getArguments().getString("id");
        name=getArguments().getString("name");
        superRecyclerView.setLayoutManager(gridLayoutManager);
        superRecyclerView.setAdapter(slideInLeftAnimationAdapter);
        presenter=new HuaShengMainPresenter(this);
        presenter.getData(HuaShengListFragment.this,0,id);
        superRecyclerView.setOnMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                page++;
                presenter.getData(HuaShengListFragment.this,10*(page-1),id);
            }
        });

        return view;
    }

    @Override
    public void showloading() {
        alertDialog.show();

    }

    @Override
    public void hideloading() {
        alertDialog.dismiss();

    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(getActivity(),e.getMessage(),SnackBarUtils.ERROR);


    }

    @Override
    public void setData(HuaShengInnerWrapper huaShengInnerWrapper) {
        huaShengDatas.addAll(huaShengInnerWrapper.getThread());
        adapter.notifyDataSetChanged();

    }
}
