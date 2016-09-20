package com.hanchao.newscars.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.HotTopickBean;
import com.hanchao.newscars.utils.ScreenSize;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 */
public class HotTopicAdapter extends BaseAdapter{
    private List<HotTopickBean.ResultBean.ListBean> data;
    private Context context;

    public HotTopicAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<HotTopickBean.ResultBean.ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int position) {
        return data!=null?data.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotTopicViewHolder holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_hot_topic,parent,false);
            int height= ScreenSize.getHight(context);
            convertView.setMinimumHeight(height/6);
            holder=new HotTopicViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (HotTopicViewHolder) convertView.getTag();
        }
        HotTopickBean.ResultBean.ListBean bean=data.get(position);
        if (bean!=null){
            holder.titleTv.setText(bean.getTitle());
            holder.authorTv.setText(bean.getBbsname());
            holder.timeTv.setText(bean.getLastreplydate());
            holder.huiTv.setText(bean.getReplycounts()+"回帖");
        }
        return convertView;
    }
    public class HotTopicViewHolder{
        TextView titleTv,authorTv,timeTv,huiTv;
        public HotTopicViewHolder(View view){
            titleTv= (TextView) view.findViewById(R.id.item_hot_topic_titleTv);
            authorTv= (TextView) view.findViewById(R.id.item_hot_topic_authorTv);
            timeTv= (TextView) view.findViewById(R.id.item_hot_topic_timeTv);
            huiTv= (TextView) view.findViewById(R.id.item_hot_topic_huiTv);
        }
    }
}
