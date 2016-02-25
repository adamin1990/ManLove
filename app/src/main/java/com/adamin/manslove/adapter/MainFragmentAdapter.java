package com.adamin.manslove.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adamin.manslove.R;
import com.adamin.manslove.domain.HomeData;
import com.adamin.manslove.utils.Constant;
import com.squareup.picasso.Picasso;

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
 * //         Created by LiTao on 2016-02-23-23:26.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.MainFragmentHolder> {

    private List<HomeData> homeDatas;

    public MainFragmentAdapter(List<HomeData> homeDatas) {
        this.homeDatas = homeDatas;
    }

    @Override
    public MainFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, parent, false);

        return new MainFragmentHolder(view);
    }

    @Override
    public void onBindViewHolder(MainFragmentHolder holder, int position) {
        Picasso.with(holder.itemView.getContext()).load(Constant.BASEIMGURL
        +homeDatas.get(position).getImage()).placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);
        holder.tvcount.setText(homeDatas.get(position).getItemNum()+"(images)");

    }

    @Override
    public int getItemCount() {
        return homeDatas.size();
    }

    class MainFragmentHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img)
        ImageView imageView;
        @Bind(R.id.tv_count)
        TextView tvcount;

        public MainFragmentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
