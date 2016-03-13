package com.adamin.manslove.adapter;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adamin.manslove.R;
import com.adamin.manslove.domain.GanMeiZi;
import com.adamin.manslove.utils.ColorUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adamlee on 2016/3/13.
 */
public class GankAdapter extends RecyclerView.Adapter<GankAdapter.MeiZiuHolder> {
    private OnItemClickListener onItemClickListener;
    private List<GanMeiZi> ganMeiZis;

    public GankAdapter(List<GanMeiZi> ganMeiZis) {
        this.ganMeiZis = ganMeiZis;
    }

    @Override
    public MeiZiuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, parent, false);
        return  new MeiZiuHolder(view);
    }

    @Override
    public void onBindViewHolder(final MeiZiuHolder holder, final int position) {
        Picasso.with(holder.itemView.getContext()).load(ganMeiZis.get(position).getUrl()).placeholder(new ColorDrawable(ColorUtils.getRandomColor(holder.itemView.getContext())))
                .into(holder.imageView);
        holder.tvcount.setText(ganMeiZis.get(position).getDesc()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.OnItemClick(position,holder.imageView);
                }
            }
        });
        holder.itemView.setClickable(true);
    }

    @Override
    public int getItemCount() {
        return ganMeiZis.size();
    }

    class MeiZiuHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img)
        ImageView imageView;
        @Bind(R.id.tv_count)
        TextView tvcount;

        public MeiZiuHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public interface OnItemClickListener{
        void OnItemClick(int position,View view);
    }
}
