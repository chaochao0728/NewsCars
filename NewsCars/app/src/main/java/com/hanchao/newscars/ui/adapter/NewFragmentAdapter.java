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
import com.hanchao.newscars.mode.bean.FastReportToAtyBean;
import com.hanchao.newscars.mode.bean.NewBean;
import com.hanchao.newscars.utils.ScreenSize;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/13.
 * 最新的适配器
 */
public class NewFragmentAdapter extends BaseAdapter {
    private List<NewBean.ResultBean.NewslistBean> data;
    private Context context;

    public NewFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NewBean.ResultBean.NewslistBean> data) {
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
        NewFragmentViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_newfragment, parent, false);
            int height = ScreenSize.getHight(context);
            convertView.setMinimumHeight(height / 8);
            holder = new NewFragmentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (NewFragmentViewHolder) convertView.getTag();
        }
        NewBean.ResultBean.NewslistBean bean = data.get(position);
        if (bean != null) {
            holder.titleTv.setText(bean.getTitle());
            holder.timeTv.setText(bean.getTime());
            Glide.with(context).load(bean.getSmallpic()).into(holder.iv);
        }
        return convertView;
    }

    public class NewFragmentViewHolder {
        ImageView iv;
        TextView titleTv, timeTv;

        public NewFragmentViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.item_newfragment_iv);
            titleTv = (TextView) view.findViewById(R.id.item_newfragment_titleTv);
            timeTv = (TextView) view.findViewById(R.id.item_newfragment_timeTv);
        }
    }
}
