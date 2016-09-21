package com.hanchao.newscars.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.utils.OnRecycleItemClik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 精品推荐里的两个recycleView的适配器
 */
public class PickRecommendRecyclerAdapter extends RecyclerView.Adapter<PickRecommendRecyclerAdapter.PickRecommentViewHolder> {
    private List<String> datas;
    private Context context;
    private OnRecycleItemClik onRecycleItemClik;
    private int p;

    public void setOnRecycleItemClik(OnRecycleItemClik onRecycleItemClik) {
        this.onRecycleItemClik = onRecycleItemClik;
    }

    public PickRecommendRecyclerAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public PickRecommentViewHolder onCreateViewHolder(ViewGroup parent, int postion) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pickrecommend_recycler, parent, false);
        PickRecommentViewHolder holder = new PickRecommentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PickRecommentViewHolder holder, final int postion) {
        Log.d("PickRecommendRecyclerAd", "postion:" + postion);
        holder.tv.setText(datas.get(postion));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = holder.getLayoutPosition();
                notifyDataSetChanged();
                String string = datas.get(postion);
                onRecycleItemClik.OnRvItemClicListener(p, string);
            }

        });
        if (postion==0) {
            if (postion == p) {
                holder.tv.setTextColor(Color.parseColor("#303F9F"));
            } else {
                holder.tv.setTextColor(Color.parseColor("#000000"));
            }
        }else {
            if (postion == p) {
                holder.tv.setTextColor(Color.parseColor("#303F9F"));
            } else {
                holder.tv.setTextColor(Color.parseColor("#000000"));
            }
        }

    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class PickRecommentViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public PickRecommentViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_pickRecommend_recycler_tv);
        }
    }
}

