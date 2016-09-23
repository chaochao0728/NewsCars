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
import com.hanchao.newscars.mode.bean.PickUpBean;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 */
public class PickUpAdapter extends BaseAdapter {
    private List<PickUpBean.ResultBean.SeriesBean> datas;
    private Context context;

    public PickUpAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<PickUpBean.ResultBean.SeriesBean> datas) {
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
        pickUpViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pick_up_listview, parent, false);
            holder = new pickUpViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (pickUpViewHolder) convertView.getTag();
        }
        PickUpBean.ResultBean.SeriesBean bean = datas.get(position);
        if (bean != null) {
            holder.titleTv.setText(bean.getSeriesname());
            holder.priceTv.setText(bean.getPricerange());
            Glide.with(context).load(bean.getThumburl()).into(holder.showIv);
        }
        return convertView;
    }

    public class pickUpViewHolder {
        TextView titleTv, priceTv;
        ImageView showIv;

        public pickUpViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_pick_up_titleTv);
            priceTv = (TextView) view.findViewById(R.id.item_pick_up_priceTv);
            showIv = (ImageView) view.findViewById(R.id.item_pick_up_showIv);
        }
    }
}
