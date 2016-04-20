package com.adamin.manslove.view.tg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.TgListAdapter;
import com.adamin.manslove.domain.tg.TgData;
import com.adamin.manslove.domain.tg.TgDataWrapper;
import com.adamin.manslove.presenter.tg.TgListPresenter;
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
 * Created by adamlee on 2016/4/15.
 */
public class TgFragment extends Fragment implements TgListView{

    @Bind(R.id.superrecycer)
    SuperRecyclerView superRecyclerView;
    GridLayoutManager gridLayoutManager;
    private List<TgData> tgDatas;
    private TgListPresenter tgListPresenter;
    private TgListAdapter tgListAdapter;
    private AlertDialog alertDialog;
    private int page=1;
    private int id=1;

    public  static  Fragment newInstance(String title,int id){
        TgFragment tgFragment=new TgFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",title);
        bundle.putInt("id",id);
        tgFragment.setArguments(bundle);
        return  tgFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_le_xun,container,false);
        ButterKnife.bind(this,view);
        gridLayoutManager=new GridLayoutManager(getActivity(),2);
        tgDatas=new ArrayList<>();
        tgListPresenter=new TgListPresenter(this);
        tgListAdapter=new TgListAdapter(tgDatas);
        SlideInLeftAnimationAdapter slideInLeftAnimationAdapter=new SlideInLeftAnimationAdapter(tgListAdapter);
        superRecyclerView.setLayoutManager(gridLayoutManager);
        superRecyclerView.setAdapter(slideInLeftAnimationAdapter);
        alertDialog= DialogUtil.buildCustomCancelDialog(getActivity(),"正在加载中");
        id=getArguments().getInt("id",1);
        tgListPresenter.getTgList(page,id,getActivity());
        superRecyclerView.setOnMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                page++;
                tgListPresenter.getTgList(page,id,getActivity());
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
        alertDialog.hide();

    }

    @Override
    public void setData(TgDataWrapper tgDataWrapper) {
        tgDatas.addAll(tgDataWrapper.getTngou());
        tgListAdapter.notifyDataSetChanged();

    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(getActivity(),e.getMessage()+"",SnackBarUtils.ERROR);

    }
}
