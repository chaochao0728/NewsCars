package com.hanchao.newscars.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.BrandRecyclerBean;
import com.hanchao.newscars.utils.ScreenSize;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 * 品牌的Recycler的适配器
 */
public class BrandRecyclerAdapter extends RecyclerView.Adapter<BrandRecyclerAdapter.brandViewHolder> {
    private List<BrandRecyclerBean.ResultBean.ListBean> datas;
    private Context context;

    public BrandRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<BrandRecyclerBean.ResultBean.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public brandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brandfragment_head_recycler, parent, false);
        int height = ScreenSize.getHight(context);
        view.setMinimumHeight(height / 8);
        brandViewHolder holder = new brandViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(brandViewHolder holder, int position) {
        BrandRecyclerBean.ResultBean.ListBean bean = datas.get(position);
        holder.textView.setText(bean.getName());
        Glide.with(context).load(bean.getImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class brandViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public brandViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_brand_fragme_head_recycler_tv);
            imageView = (ImageView) itemView.findViewById(R.id.item_brand_fragme_head_recycler_ima);
        }
    }
}
