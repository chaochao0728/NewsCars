package com.hanchao.newscars.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.utils.OnRecycleItemClik;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class PickRecommendRecyclerAdapter extends RecyclerView.Adapter<PickRecommendRecyclerAdapter.PickRecommentViewHolder>{
    private List<String>datas;
    private Context context;
    private OnRecycleItemClik onRecycleItemClik;

    public void setOnRecycleItemClik(OnRecycleItemClik onRecycleItemClik) {
        this.onRecycleItemClik = onRecycleItemClik;
    }

    public PickRecommendRecyclerAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public PickRecommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_pickrecommend_recycler,parent,false);
        PickRecommentViewHolder holder=new PickRecommentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PickRecommentViewHolder holder, final int position) {
        holder.tv.setText(datas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecycleItemClik!=null){
                    int p=holder.getLayoutPosition();
                    String string=datas.get(position);
                    onRecycleItemClik.OnRvItemClicListener(p,string);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }

    class PickRecommentViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public PickRecommentViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.item_pickRecommend_recycler_Tv);
        }
    }
}
