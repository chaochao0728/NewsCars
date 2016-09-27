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
import com.hanchao.newscars.mode.bean.YouchuangBean;
import com.hanchao.newscars.utils.ScreenSize;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/18.
 * 优创的适配器
 */
public class YouchuangFfragmentAdapter extends BaseAdapter {
    private List<YouchuangBean.ResultBean.NewslistBean> data;
    private Context context;

    public YouchuangFfragmentAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<YouchuangBean.ResultBean.NewslistBean> data) {
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
        YouchuangFragmentViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_youchuangfragment, parent, false);
            int height = ScreenSize.getHight(context);
            convertView.setMinimumHeight(height / 3);
            holder = new YouchuangFragmentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (YouchuangFragmentViewHolder) convertView.getTag();
        }
        YouchuangBean.ResultBean.NewslistBean bean = data.get(position);
        if (bean != null) {
            holder.authorTv.setText(bean.getUsername());
            holder.titleTv.setText(bean.getTitle());
            holder.zanTv.setText(bean.getPraisenum() + "");
            holder.talkTv.setText(bean.getReplycount() + "");
            holder.timeTv.setText(bean.getPublishtime());
            Glide.with(context).load(bean.getIndexdetail().get(0)).into(holder.youchuangIv);
            Glide.with(context).load(bean.getUserpic()).into(holder.circleIv);
        }
        return convertView;
    }

    public class YouchuangFragmentViewHolder {
        TextView authorTv, titleTv, timeTv, zanTv, talkTv;
        ImageView youchuangIv;
        CircleImageView circleIv;

        public YouchuangFragmentViewHolder(View view) {
            authorTv = (TextView) view.findViewById(R.id.item_youchuang_authorTv);
            titleTv = (TextView) view.findViewById(R.id.item_youchuang_titleTv);
            timeTv = (TextView) view.findViewById(R.id.item_youchuang_timeTv);
            zanTv = (TextView) view.findViewById(R.id.item_youchuang_zanTv);
            talkTv = (TextView) view.findViewById(R.id.item_youchuang_talkTv);
            circleIv = (CircleImageView) view.findViewById(R.id.item_youchuang_circleimage);
            youchuangIv = (ImageView) view.findViewById(R.id.item_youchuang_image);
        }
    }
}
