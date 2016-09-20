package com.hanchao.newscars.ui.fragment.forumfragments;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.HotTopickBean;
import com.hanchao.newscars.mode.net.NetValues;
import com.hanchao.newscars.ui.adapter.HotTopicAdapter;
import com.hanchao.newscars.ui.app.NewsCarsApp;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 热帖的fragment
 */
public class HotTopicFragment extends AbsBaseFragment {
    private ListView listView;
    private HotTopicAdapter adapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_hot_topic;
    }

    @Override
    protected void initView() {
        listView=byView(R.id.hot_topic_listView);
    }

    @Override
    protected void initDatas() {
        adapter=new HotTopicAdapter(context);
        listView.setAdapter(adapter);
        RequestQueue requestQueue= Volley.newRequestQueue(NewsCarsApp.getContext());
        StringRequest stringRequest=new StringRequest(NetValues.HOTTOPIC, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                HotTopickBean bean=gson.fromJson(response,HotTopickBean.class);
                List<HotTopickBean.ResultBean.ListBean> data=bean.getResult().getList();
                adapter.setData(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }
}
