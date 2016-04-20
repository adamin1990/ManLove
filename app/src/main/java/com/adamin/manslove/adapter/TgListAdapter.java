package com.adamin.manslove.adapter;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adamin.manslove.R;
import com.adamin.manslove.domain.tg.TgData;
import com.adamin.manslove.utils.ColorUtils;
import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.view.tg.TgDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adamlee on 2016/4/15.
 */
public class TgListAdapter extends RecyclerView.Adapter<TgListAdapter.MyAdapter> {
  private List<TgData> tgDatas;

    public TgListAdapter(List<TgData> tgDatas) {
        this.tgDatas = tgDatas;
    }

    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, parent, false);
        return new MyAdapter(view);
    }

    @Override
    public void onBindViewHolder(final MyAdapter holder, final int position) {
        Picasso.with(holder.itemView.getContext())
                .load(Constant.TGIMGPREFIX+tgDatas.get(position).getImg())
                .placeholder(new ColorDrawable(ColorUtils.getRandomColor(holder.itemView.getContext())))
                .into(holder.imageView);
        holder.tvcount.setText(tgDatas.get(position).getTitle()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), TgDetailActivity.class);
                intent.putExtra("id",tgDatas.get(position).getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tgDatas.size();
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
