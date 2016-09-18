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
import com.hanchao.newscars.mode.bean.NewsBean;

import java.util.List;

/**
 * Created by dllo on 16/9/18.
 */
public class NewsAdapter extends BaseAdapter{
    private List<NewsBean.ResultBean.NewslistBean> data;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NewsBean.ResultBean.NewslistBean> data) {
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
        NewsViewHolder holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
            holder=new NewsViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (NewsViewHolder) convertView.getTag();
        }
        NewsBean.ResultBean.NewslistBean bean=data.get(position);
        if (bean!=null){
            holder.titleTv.setText(bean.getTitle());
            holder.timeTv.setText(bean.getTime());
            holder.talkTv.setText(bean.getReplycount()+"评论");
            Glide.with(context).load(bean.getSmallpic()).into(holder.showIv);
        }
        return convertView;
    }
    public class NewsViewHolder{
        ImageView showIv;
        TextView titleTv,timeTv,talkTv;
        public NewsViewHolder(View view){
            showIv= (ImageView) view.findViewById(R.id.item_newsIv);
            titleTv= (TextView) view.findViewById(R.id.item_news_titleTv);
            timeTv= (TextView) view.findViewById(R.id.item_news_timeTv);
            talkTv= (TextView) view.findViewById(R.id.item_news_talkTv);
        }
    }
}
