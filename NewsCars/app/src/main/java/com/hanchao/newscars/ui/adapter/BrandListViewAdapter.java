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
import com.hanchao.newscars.mode.bean.BrandListBean;
import com.hanchao.newscars.utils.ScreenSize;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 */
public class BrandListViewAdapter extends BaseAdapter{
    private List<BrandListBean.ResultBean.BrandlistBean.ListBean> data;
    private List<BrandListBean.ResultBean.BrandlistBean>datas;
    private Context context;

    public BrandListViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<BrandListBean.ResultBean.BrandlistBean.ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setDatas(List<BrandListBean.ResultBean.BrandlistBean> datas) {
        this.datas = datas;
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
        brandViewHolder holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_brand_listview,parent,false);
            int height= ScreenSize.getHight(context);
            convertView.setMinimumHeight(height/9);
            holder=new brandViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (brandViewHolder) convertView.getTag();
            BrandListBean.ResultBean.BrandlistBean.ListBean bean=data.get(position);
//            BrandListBean.ResultBean.BrandlistBean beans=datas.get(position);
            if (bean!=null){
//                holder.wordTv.setText(beans.getLetter());
                holder.brandTv.setText(bean.getName());
                Glide.with(context).load(bean.getImgurl()).into(holder.showIv);
            }
        }
        return convertView;
    }
    public class brandViewHolder{
        TextView brandTv,wordTv;
        ImageView showIv;
        public brandViewHolder(View view){
            brandTv= (TextView) view.findViewById(R.id.item_brand_fragment_brandTv);
            showIv= (ImageView) view.findViewById(R.id.item_brand_fragment_imageView);
//            wordTv= (TextView) view.findViewById(R.id.item_brand_fragment_wordTv);
        }
    }
}
