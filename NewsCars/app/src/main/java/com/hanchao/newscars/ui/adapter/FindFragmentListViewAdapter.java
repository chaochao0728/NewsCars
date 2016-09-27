package com.hanchao.newscars.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.FindFragmentListBean;

import java.util.List;

/**
 * Created by dllo on 16/9/23.
 * 发现的最先面的listView的适配器
 */
public class FindFragmentListViewAdapter extends BaseAdapter {
    private List<FindFragmentListBean.ResultBean.GoodslistBean.ListBean> datas;
    private Context context;

    public FindFragmentListViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<FindFragmentListBean.ResultBean.GoodslistBean.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas != null ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        findViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_find_list_view, parent, false);
            holder = new findViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (findViewHolder) convertView.getTag();
        }
        FindFragmentListBean.ResultBean.GoodslistBean.ListBean bean = datas.get(position);
        if (bean != null) {
            holder.titleTv.setText(bean.getTitle());
            holder.contentTv.setText(bean.getAdinfo());
            holder.priceTv.setText(bean.getPrice());
            Glide.with(context).load(bean.getLogo()).into(holder.showIv);
        }
        return convertView;
    }

    public class findViewHolder {
        ImageView showIv;
        TextView titleTv, contentTv, priceTv;

        public findViewHolder(View view) {
            showIv = (ImageView) view.findViewById(R.id.item_find_listView_showIv);
            titleTv = (TextView) view.findViewById(R.id.item_find_listView_titleTv);
            contentTv = (TextView) view.findViewById(R.id.item_find_listView_contentTv);
            priceTv = (TextView) view.findViewById(R.id.item_find_listView_priceTv);
        }
    }
}
