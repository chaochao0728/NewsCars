package com.hanchao.newscars.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.BrandListBean;
import com.hanchao.newscars.mode.bean.BrandPopBean;

import java.util.List;
import java.util.Map;


/**
 * Created by dllo on 16/10/9.
 * 品牌的popupwindow的适配器
 */
public class BrandPopAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;
    private Map<String, List<BrandPopBean.ResultBean.FctlistBean.SerieslistBean>> childList;
    private Context context;


    public void setChildMaps(Map<String, List<BrandPopBean.ResultBean.FctlistBean.SerieslistBean>> childMaps) {
        this.childList = childMaps;
        notifyDataSetChanged();
    }


    public void setGroupList(List<String> groupList) {
        this.groupList = groupList;
        notifyDataSetChanged();
    }


    public BrandPopAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return groupList != null ? groupList.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<BrandPopBean.ResultBean.FctlistBean.SerieslistBean> d;
        d = childList.get(groupList.get(groupPosition));
        return d == null ? 0 : d.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList != null ? groupList.get(groupPosition) : null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<BrandPopBean.ResultBean.FctlistBean.SerieslistBean> d;
        d = childList.get(groupList.get(groupPosition));
        return d.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_brand_group, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.tvGroup.setText(groupList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_brand_pop_list_view, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        BrandPopBean.ResultBean.FctlistBean.SerieslistBean bean = (BrandPopBean.ResultBean.FctlistBean.SerieslistBean) getChild(groupPosition, childPosition);
        childViewHolder.tvchild.setText(bean.getName());
        childViewHolder.priceTv.setText(bean.getPrice());
        Glide.with(context).load(bean.getImgurl()).into(childViewHolder.ivchild);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        TextView tvGroup;

        public GroupViewHolder(View view) {
            tvGroup = (TextView) view.findViewById(R.id.item_fragment_brand_groupTv);
        }
    }

    class ChildViewHolder {
        TextView tvchild, priceTv;
        ImageView ivchild;

        public ChildViewHolder(View view) {
            tvchild = (TextView) view.findViewById(R.id.item_brand_pop_list_nameTv);
            ivchild = (ImageView) view.findViewById(R.id.item_brand_pop_list_iv);
            priceTv = (TextView) view.findViewById(R.id.item_brand_pop_list_priceTv);
        }
    }
}
