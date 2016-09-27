package com.hanchao.newscars.ui.fragment.findcarsfragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.BrandListBean;
import com.hanchao.newscars.mode.bean.BrandRecyclerBean;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.adapter.BrandFragmentAdapter;
import com.hanchao.newscars.ui.adapter.BrandRecyclerAdapter;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/9/10.
 * 品牌的fragment
 */
public class BrandFragment extends AbsBaseFragment {
    private ExpandableListView listView;
    private BrandFragmentAdapter adapter;
    private List<String> groupList;
    private Map<String, List<BrandListBean.ResultBean.BrandlistBean.ListBean>> childs;
    private String Url = "http://223.99.255.20/cars.app.autohome.com.cn/dealer_v5.7.0/dealer/hotbrands-pm2.json";

    public static BrandFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("URL", str);
        BrandFragment fragment = new BrandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_brand;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.fragment_brand_expandableListView);
    }

    @Override
    protected void initDatas() {
        adapter = new BrandFragmentAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String string = bundle.getString("URL");
        VolleyInstance.getInstance().startRequest(string, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                groupList = new ArrayList<>();
                childs = new HashMap<>();
                BrandListBean bean = gson.fromJson(result, BrandListBean.class);
                //A-Z的解析
                List<BrandListBean.ResultBean.BrandlistBean> datas = bean.getResult().getBrandlist();
                for (int i = 0; i < datas.size(); i++) {
                    String letter = datas.get(i).getLetter();
                    groupList.add(letter);
                    childs.put(letter, datas.get(i).getList());
                }
                adapter.setGroupList(groupList);
                adapter.setChildMaps(childs);
                for (int i = 0, count = datas.size(); i < count; i++) {
                    listView.expandGroup(i);
                }

            }

            @Override
            public void failure() {

            }
        });
        addHeadView();
    }

    /**
     * 添加头布局的方法
     */
    private void addHeadView() {
        View headView = LayoutInflater.from(context).inflate(R.layout.item_brandfragment_head, null);
        RecyclerView recycler = (RecyclerView) headView.findViewById(R.id.item_brand_head_recycler);
        final BrandRecyclerAdapter adapters = new BrandRecyclerAdapter(context);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapters);
        VolleyInstance.getInstance().startRequest(Url, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                BrandRecyclerBean bean = gson.fromJson(result, BrandRecyclerBean.class);
                List<BrandRecyclerBean.ResultBean.ListBean> datas = bean.getResult().getList();
                adapters.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });

        listView.addHeaderView(headView);
    }
}
