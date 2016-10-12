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
import com.hanchao.newscars.mode.bean.CollectBean;

import java.util.List;

/**
 * Created by dllo on 16/10/11.\
 * 收藏的适配器
 */
public class CollectAdapter extends BaseAdapter {
    private Context context;
    private List<CollectBean> data;

    public CollectAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CollectBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CollectViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_collect, parent, false);
            holder = new CollectViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CollectViewHolder) convertView.getTag();
        }
        CollectBean bean = data.get(position);
        holder.tv.setText(bean.getContent());
        Glide.with(context).load(bean.getImagurl()).into(holder.iv);
        return convertView;
    }

    public class CollectViewHolder {
        TextView tv;
        ImageView iv;

        public CollectViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.item_collect_Tv);
            iv = (ImageView) view.findViewById(R.id.item_collect_Iv);
        }
    }
}
