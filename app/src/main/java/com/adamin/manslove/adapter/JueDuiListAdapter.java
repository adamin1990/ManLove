package com.adamin.manslove.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adamin.manslove.R;
import com.adamin.manslove.domain.juedui.JdPost;
import com.adamin.manslove.domain.juedui.JueDuiTemp;
import com.adamin.manslove.utils.ColorUtils;
import com.adamin.manslove.view.jueduilingyu.WebActivity;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

/**
 * Created by adamlee on 2016/3/31.
 */
public class JueDuiListAdapter extends RecyclerView.Adapter<JueDuiListAdapter.MyAdapter> {
    private List<JueDuiTemp> jdPosts;
    private Picasso picasso;
    private Context context;

    public JueDuiListAdapter(List<JueDuiTemp> jdPosts,Context context) {
        this.jdPosts = jdPosts;
        this.context=context;
        picasso=new Picasso.Builder(context)
                .build();
    }

    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, parent, false);
        return new MyAdapter(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter holder, final int position) {
        picasso.with(holder.itemView.getContext())
                .load(jdPosts.get(position).getFirstimage())
                .config(Bitmap.Config.ARGB_4444)
                .fit()
                .placeholder(new ColorDrawable(ColorUtils.getRandomColor(holder.itemView.getContext())))
                .into(holder.imageView);
        holder.tvcount.setText(jdPosts.get(position).getTitle()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, WebActivity.class);
                intent.putExtra("title",jdPosts.get(position).getTitle());
                intent.putExtra("content",jdPosts.get(position).getCotent());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return jdPosts.size();
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
