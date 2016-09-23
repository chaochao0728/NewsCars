package com.hanchao.newscars.ui.fragment.findcarsfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.BrandListBean;
import com.hanchao.newscars.mode.bean.BrandRecyclerBean;
import com.hanchao.newscars.ui.adapter.BrandListViewAdapter;
import com.hanchao.newscars.ui.adapter.BrandRecyclerAdapter;
import com.hanchao.newscars.ui.app.NewsCarsApp;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 品牌的fragment
 */
public class BrandFragment extends AbsBaseFragment {
    private ListView listView;
    private BrandListViewAdapter adapter;
    private List<BrandListBean.ResultBean.BrandlistBean> data;
    private List<BrandListBean.ResultBean> datas;
    private String Url="http://223.99.255.20/cars.app.autohome.com.cn/dealer_v5.7.0/dealer/hotbrands-pm2.json";

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
        listView = byView(R.id.fragment_brand_listView);
    }

    @Override
    protected void initDatas() {
        adapter = new BrandListViewAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String string = bundle.getString("URL");
        RequestQueue queue = Volley.newRequestQueue(NewsCarsApp.getContext());
        StringRequest stringRequest = new StringRequest(string, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                BrandListBean bean = gson.fromJson(response, BrandListBean.class);
                //解析字母
                List<BrandListBean.ResultBean.BrandlistBean> datas=bean.getResult().getBrandlist();
                adapter.setDatas(datas);
                //解析数据
                data = bean.getResult().getBrandlist();
                List<BrandListBean.ResultBean.BrandlistBean.ListBean> itemDatas = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        itemDatas.add(data.get(i).getList().get(j));
                        adapter.setData(itemDatas);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
        addHeadView();
    }

    private void addHeadView() {
        View headView = LayoutInflater.from(context).inflate(R.layout.item_brandfragment_head, null);
        RecyclerView recycler = (RecyclerView) headView.findViewById(R.id.item_brand_head_recycler);
        final BrandRecyclerAdapter adapters=new BrandRecyclerAdapter(context);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapters);
        RequestQueue queue=Volley.newRequestQueue(NewsCarsApp.getContext());
        StringRequest quest=new StringRequest(Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                BrandRecyclerBean bean=gson.fromJson(response,BrandRecyclerBean.class);
                List<BrandRecyclerBean.ResultBean.ListBean> datas=bean.getResult().getList();
                adapters.setDatas(datas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(quest);
        listView.addHeaderView(headView);
    }
}
