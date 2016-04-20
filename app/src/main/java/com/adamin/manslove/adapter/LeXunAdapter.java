package com.adamin.manslove.adapter;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adamin.manslove.R;
import com.adamin.manslove.domain.lexun.LeXunData;
import com.adamin.manslove.utils.ColorUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adamlee on 2016/4/3.
 */
public class LeXunAdapter extends RecyclerView.Adapter<LeXunAdapter.MyAdapter> {
    private List<LeXunData> leXunDatas;

    public LeXunAdapter(List<LeXunData> leXunDatas) {
        this.leXunDatas = leXunDatas;
    }

    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, parent, false);
        return new MyAdapter(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter holder, int position) {
        Picasso.with(holder.itemView.getContext())
                .load(leXunDatas.get(position).getPrevpath())
                .placeholder(new ColorDrawable(ColorUtils.getRandomColor(holder.itemView.getContext())))
                .into(holder.imageView);
        holder.tvcount.setText(leXunDatas.get(position).getTitle()+"");

    }

    @Override
    public int getItemCount() {
        return leXunDatas.size();
    }

    class MyAdapter extends RecyclerView.ViewHolder {
        @Bind(R.id.img)
        ImageView imageView;
        @Bind(R.id.tv_count)
        TextView tvcount;

        public MyAdapter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
