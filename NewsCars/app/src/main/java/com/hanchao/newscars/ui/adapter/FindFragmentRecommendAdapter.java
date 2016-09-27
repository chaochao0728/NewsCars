package com.hanchao.newscars.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.FindFragmentListBean;
import com.hanchao.newscars.utils.ScreenSize;

import java.util.List;

/**
 * Created by dllo on 16/9/26.
 * 发现的精品推荐的适配器
 */
public class FindFragmentRecommendAdapter extends RecyclerView.Adapter<FindFragmentRecommendAdapter.findRecommendViewHolder> {
    private Context context;
    private List<FindFragmentListBean.ResultBean.ModulelistBean.ListBean> datas;

    public void setData(List<FindFragmentListBean.ResultBean.ModulelistBean.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public FindFragmentRecommendAdapter(Context context) {
        this.context = context;
    }

    @Override
    public findRecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fragment_find_head_item, parent, false);
        int width = ScreenSize.getScreenSize(ScreenSize.screenState.WIDTH);
        int height = ScreenSize.getScreenSize(ScreenSize.screenState.HEIGHT);
        view.setMinimumHeight(height / 8);
        view.setMinimumWidth(width / 2);
        findRecommendViewHolder holder = new findRecommendViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(findRecommendViewHolder holder, int position) {
        FindFragmentListBean.ResultBean.ModulelistBean.ListBean bean = datas.get(position);
        holder.nameTv.setText(bean.getTitle());
        holder.newPriceTv.setText(bean.getPrice());
        holder.oldPriceTv.setText(bean.getFctprice());
        holder.contentTv.setText(bean.getShorttitle());
        Glide.with(context).load(bean.getLogo()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class findRecommendViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv, newPriceTv, oldPriceTv, contentTv;
        ImageView imageView;

        public findRecommendViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.item_fragment_find_like_item_nameTv);
            oldPriceTv = (TextView) itemView.findViewById(R.id.item_fragment_find_like_item_oldPriceTv);
            newPriceTv = (TextView) itemView.findViewById(R.id.item_fragment_find_like_item_newPriceTv);
            imageView = (ImageView) itemView.findViewById(R.id.item_fragment_find_like_item_iv);
            contentTv = (TextView) itemView.findViewById(R.id.item_fragment_find_like_item_contentTv);
            oldPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

}
