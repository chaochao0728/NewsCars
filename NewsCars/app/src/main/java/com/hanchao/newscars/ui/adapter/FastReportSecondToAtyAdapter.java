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

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 * 快报详情适配器
 */
public class FastReportSecondToAtyAdapter extends BaseAdapter {
    private int itemCount = 1;
    private Context context;
    private List<FastReportToAtyBean.ResultBean.MessagelistBean.CommentlistBean> datas;

    public FastReportSecondToAtyAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<FastReportToAtyBean.ResultBean.MessagelistBean.CommentlistBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (datas.size() > 1) {
            return itemCount;
        } else {
            return datas.size();
        }
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
        SecondViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_item_fast_report_to_aty_list_view, parent, false);
            holder = new SecondViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SecondViewHolder) convertView.getTag();
        }
        FastReportToAtyBean.ResultBean.MessagelistBean.CommentlistBean bean = datas.get(position);
        holder.talkNameTv.setText(bean.getUsername());
        holder.talkTv.setText(bean.getContent());
        Glide.with(context).load(bean.getHeadimg()).into(holder.talkIv);
        return convertView;
    }

    public class SecondViewHolder {
        ImageView talkIv;
        TextView talkNameTv, talkTv;

        public SecondViewHolder(View view) {
            talkIv = (ImageView) view.findViewById(R.id.item_second_fast_report_talkIv);
            talkNameTv = (TextView) view.findViewById(R.id.item_second_fast_report_talkerTv);
            talkTv = (TextView) view.findViewById(R.id.item_second_fast_report_talkTv);
        }
    }
}
