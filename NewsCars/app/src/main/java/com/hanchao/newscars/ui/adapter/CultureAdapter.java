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
import com.hanchao.newscars.mode.bean.CultureBean;
import com.hanchao.newscars.utils.ScreenSize;

import java.util.List;

/**
 * Created by dllo on 16/9/18.
 */
public class CultureAdapter extends BaseAdapter{
    private List<CultureBean.ResultBean.NewslistBean> datas;
    private Context context;

    public CultureAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CultureBean.ResultBean.NewslistBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas!=null?datas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return datas!=null?datas.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CultureViewHolder holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_culture,parent,false);
            int height= ScreenSize.getHight(context);
            convertView.setMinimumHeight(height/6);
            holder=new CultureViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (CultureViewHolder) convertView.getTag();
        }
        CultureBean.ResultBean.NewslistBean bean=datas.get(position);
        if (bean!=null){
            holder.titleTv.setText(bean.getTitle());
            holder.timeTv.setText(bean.getTime());
            holder.talkTv.setText(bean.getReplycount()+"评论");
            Glide.with(context).load(bean.getSmallpic()).into(holder.showIv);
        }
        return convertView;
    }
    public class CultureViewHolder{
        ImageView showIv;
        TextView titleTv,timeTv,talkTv;
        public CultureViewHolder(View view){
            showIv= (ImageView) view.findViewById(R.id.item_culture_showIv);
            titleTv= (TextView) view.findViewById(R.id.item_culture_titleTv);
            timeTv= (TextView) view.findViewById(R.id.item_culture_timeTv);
            talkTv= (TextView) view.findViewById(R.id.item_culture_talkTv);
        }
    }
}
