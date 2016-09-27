package com.hanchao.newscars.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.BrandListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/9/23.
 * 品牌的适配器
 */
public class BrandFragmentAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;
    private Map<String, List<BrandListBean.ResultBean.BrandlistBean.ListBean>> childList;
    private Context context;

    public void setChildMaps(Map<String, List<BrandListBean.ResultBean.BrandlistBean.ListBean>> childMaps) {
        this.childList = childMaps;
        notifyDataSetChanged();
    }

    public void setGroupList(List<String> groupList) {
        this.groupList = groupList;
        notifyDataSetChanged();
    }


    public BrandFragmentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return groupList != null ? groupList.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<BrandListBean.ResultBean.BrandlistBean.ListBean> d;
        d = childList.get(groupList.get(groupPosition));
        return d == null ? 0 : d.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList != null ? groupList.get(groupPosition) : null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<BrandListBean.ResultBean.BrandlistBean.ListBean> d;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_brand_child, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        BrandListBean.ResultBean.BrandlistBean.ListBean bean = (BrandListBean.ResultBean.BrandlistBean.ListBean) getChild(groupPosition, childPosition);
        childViewHolder.tvchild.setText(bean.getName());
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
        TextView tvchild;
        ImageView ivchild;

        public ChildViewHolder(View view) {
            tvchild = (TextView) view.findViewById(R.id.item_fragment_brand_child_tv);
            ivchild = (ImageView) view.findViewById(R.id.item_fragment_brand_child_iv);
        }
    }
}
