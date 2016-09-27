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
import com.hanchao.newscars.mode.bean.ManyBean;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 * 精品推荐所有复用的fragment的适配器
 */
public class ManyAdapter extends BaseAdapter {
    private List<ManyBean.ResultBean.ListBean> datas;
    private Context context;

    public ManyAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ManyBean.ResultBean.ListBean> datas) {
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
        ManyViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_many, parent, false);
            holder = new ManyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ManyViewHolder) convertView.getTag();
        }
        ManyBean.ResultBean.ListBean bean = datas.get(position);
        if (bean != null) {
            holder.jiTv.setText(bean.getBbsname());
            holder.titleTv.setText(bean.getTitle());
            holder.huiTv.setText(bean.getReplycounts() + "回");
            Glide.with(context).load(bean.getSmallpic()).into(holder.showIv);
        }
        return convertView;
    }

    public class ManyViewHolder {
        ImageView showIv;
        TextView titleTv, jiTv, huiTv;

        public ManyViewHolder(View view) {
            showIv = (ImageView) view.findViewById(R.id.item_manyIv);
            titleTv = (TextView) view.findViewById(R.id.item_many_titleTv);
            jiTv = (TextView) view.findViewById(R.id.item_many_jiTv);
            huiTv = (TextView) view.findViewById(R.id.item_many_huiTv);
        }
    }
}
