package com.adamin.manslove.view.jueduilingyu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adamin.manslove.R;
import com.adamin.manslove.adapter.JueDuiListAdapter;
import com.adamin.manslove.domain.juedui.JdPost;
import com.adamin.manslove.domain.juedui.JueDuiTemp;
import com.adamin.manslove.domain.juedui.JueDuiWrapper;
import com.adamin.manslove.presenter.juedui.JueDuiPresenter;
import com.adamin.manslove.utils.SnackBarUtils;
import com.adamin.superrecyclerview.animator.adapters.SlideInLeftAnimationAdapter;
import com.adamin.superrecyclerview.superrecycer.OnMoreListener;
import com.adamin.superrecyclerview.superrecycer.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import adamin.toolkits.utils.LogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adamlee on 2016/3/31.
 */
public class JueDuiFragment extends Fragment implements JueDuiView{
    private JueDuiPresenter presenter;
    private List<JueDuiTemp> jdPosts;
    @Bind(R.id.superrecycer)
    SuperRecyclerView superRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private String name;
    private String type;
    private JueDuiListAdapter adapter;
    private int page=1;
    private AlertDialog alertDialog;
    private boolean canloading=true;


    public static Fragment newInstance(String name, String type) {
        JueDuiFragment fragment = new JueDuiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_home, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        alertDialog= DialogUtil.buildCustomCancelDialog(getActivity(),"loading");
        presenter=new JueDuiPresenter(this);
        jdPosts=new ArrayList<>();
        gridLayoutManager=new GridLayoutManager(getActivity(),2);
        name=getArguments().getString("name");
        type=getArguments().getString("type");
        adapter=new JueDuiListAdapter(jdPosts,getActivity());
        SlideInLeftAnimationAdapter slideInLeftAnimationAdapter=new SlideInLeftAnimationAdapter(adapter);
        superRecyclerView.setLayoutManager(gridLayoutManager);
        superRecyclerView.setAdapter(slideInLeftAnimationAdapter);
        superRecyclerView.setOnMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                if(canloading){
                    page++;
                    presenter.getData(JueDuiFragment.this,type,page);
                }

            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.getData(this,type,page);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showloading() {
        canloading=false;
        alertDialog.show();

    }

    @Override
    public void hideloading() {
        canloading=true;
        alertDialog.dismiss();

    }

    @Override
    public void showError(Exception e) {
        SnackBarUtils.showSnackBar(getActivity(),e.getMessage()+"",SnackBarUtils.ERROR);

    }

    @Override
    public void setData(JueDuiWrapper jueDuiWrapper) {
        new GetTemp().execute(jueDuiWrapper.getPosts());


    }
    class  GetTemp extends AsyncTask<List<JdPost>,Void,List<JueDuiTemp>>{

        @Override
        protected void onPreExecute() {
            canloading=false;
            super.onPreExecute();
        }

        @Override
        protected List<JueDuiTemp> doInBackground(List<JdPost>... params) {
            canloading=false;
            List<JueDuiTemp> jueDuiTemps=new ArrayList<>();
            for(int i=0;i<params[0].size();i++){
                JueDuiTemp jueDuiTemp=new JueDuiTemp();
                jueDuiTemp.setCotent(params[0].get(i).getContent());
                jueDuiTemp.setFirstimage(getFirst(params[0].get(i).getContent()));
                jueDuiTemp.setTitle(params[0].get(i).getTitle()+"");
                jueDuiTemps.add(jueDuiTemp);
            }
            return jueDuiTemps;
        }

        @Override
        protected void onPostExecute(List<JueDuiTemp> jueDuiTemps) {
            LogUtil.error(JueDuiFragment.class,"可不可以下载"+canloading);
            super.onPostExecute(jueDuiTemps);
            jdPosts.addAll(jueDuiTemps);
            adapter.notifyDataSetChanged();
            canloading=true;


        }
    }

    private String getFirst(String content) {
        String s=  content.split("http://")[1].split(".jpg")[0];
        return "http://"+s+".jpg";
    }
}
