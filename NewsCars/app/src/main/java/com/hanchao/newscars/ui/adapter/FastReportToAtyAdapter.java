package com.hanchao.newscars.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.FastReportToAtyBean;
import com.hanchao.newscars.utils.ScreenSize;

import java.util.List;

/**
 * Created by dllo on 16/10/7.
 * 快报详情适配器
 */
public class FastReportToAtyAdapter extends BaseAdapter {
    private List<FastReportToAtyBean.ResultBean.MessagelistBean> data;
    private Context context;

    public FastReportToAtyAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<FastReportToAtyBean.ResultBean.MessagelistBean> data) {
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
        fastReportToAtyViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fast_report_to_aty_list_view, parent, false);
            int height = ScreenSize.getHight(context);
            convertView.setMinimumHeight(height / 2);
            holder = new fastReportToAtyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (fastReportToAtyViewHolder) convertView.getTag();
        }
        FastReportToAtyBean.ResultBean.MessagelistBean bean = data.get(position);
        holder.timeTv.setText(bean.getPublishtime());
        holder.authorTv.setText(bean.getAuthorname());
        holder.titleTv.setText(bean.getContent());
        Glide.with(context).load(bean.getAttachments().get(0).getPicurl()).into(holder.coundIv);
        Glide.with(context).load(bean.getHeadimg()).into(holder.authoeIv);
        //每一条的listView
        FastReportSecondToAtyAdapter adapter = new FastReportSecondToAtyAdapter(context);
        adapter.setDatas(bean.getCommentlist());
        holder.listView.setAdapter(adapter);
        return convertView;
    }

    class fastReportToAtyViewHolder {
        ImageView authoeIv, coundIv;
        TextView authorTv, timeTv, titleTv;
        ListView listView;

        public fastReportToAtyViewHolder(View view) {
            authoeIv = (ImageView) view.findViewById(R.id.item_fast_report_to_aty_list_authorIv);
            coundIv = (ImageView) view.findViewById(R.id.item_fast_report_to_aty_list_coundIv);
            authorTv = (TextView) view.findViewById(R.id.item_fast_report_to_aty_list_authorTv);
            timeTv = (TextView) view.findViewById(R.id.item_fast_report_to_aty_list_timeTv);
            titleTv = (TextView) view.findViewById(R.id.item_fast_report_to_aty_list_titleTv);
            listView = (ListView) view.findViewById(R.id.item_fast_report_to_aty_list_list_view);
        }
    }
}
