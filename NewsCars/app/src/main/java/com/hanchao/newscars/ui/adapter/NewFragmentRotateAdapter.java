package com.hanchao.newscars.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.NewFragmentRoateBean;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class NewFragmentRotateAdapter extends PagerAdapter {
    private List<NewFragmentRoateBean> datas;
    private Context context;
    private LayoutInflater inflater;

    public NewFragmentRotateAdapter(List<NewFragmentRoateBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public NewFragmentRotateAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDatas(List<NewFragmentRoateBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int newPosition = position % datas.size();
        View convertView = inflater.inflate(R.layout.item_newfragment_head_viewpager, container, false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.new_fragment_item_viewPager_iv);
        Glide.with(context).load(datas.get(newPosition).getImgUrl()).into(imageView);
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
