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
import com.hanchao.newscars.mode.bean.VideoBean;
import com.hanchao.newscars.utils.ScreenSize;

import java.util.List;

/**
 * Created by dllo on 16/9/28.
 */
public class VidaoFragmentAdapter extends BaseAdapter {
    private List<VideoBean.ResultBean.ListBean> data;
    private Context context;

    public VidaoFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<VideoBean.ResultBean.ListBean> data) {
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
        VidaoFragmentViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_vidao_fragment_list_view, parent, false);
            holder = new VidaoFragmentViewHolder(convertView);
            int height = ScreenSize.getHight(context);
            convertView.setMinimumHeight(height / 6);
            convertView.setTag(holder);
        } else {
            holder = (VidaoFragmentViewHolder) convertView.getTag();
        }
        VideoBean.ResultBean.ListBean bean = data.get(position);
        if (bean != null) {
            holder.titleTv.setText(bean.getTitle());
            holder.timeTv.setText(bean.getTime());
            holder.playTv.setText(bean.getReplycount() + "播放");
            Glide.with(context).load(bean.getSmallimg()).into(holder.iv);
        }
        return convertView;
    }

    public class VidaoFragmentViewHolder {
        ImageView iv;
        TextView titleTv, timeTv, playTv;

        public VidaoFragmentViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_vidao_fragment_list_view_titleTv);
            timeTv = (TextView) view.findViewById(R.id.item_vidao_fragment_list_view_timeTv);
            playTv = (TextView) view.findViewById(R.id.item_vidao_fragment_list_view_playTv);
            iv = (ImageView) view.findViewById(R.id.item_vidao_fragment_list_view_iv);
        }
    }
}
