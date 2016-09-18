package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.FastReportBean;
import com.hanchao.newscars.ui.adapter.FastReportAdapter;
import com.hanchao.newscars.ui.app.NewsCarsApp;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 快报fragment
 */
public class FastReportFragment extends AbsBaseFragment {
    private ListView listView;
    private FastReportAdapter adapter;
    public static FastReportFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("URL", str);
        FastReportFragment fragment = new FastReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_fastreport;
    }

    @Override
    protected void initView() {
        listView=byView(R.id.fragment_fastreport_listView);
    }

    @Override
    protected void initDatas() {
        adapter=new FastReportAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String fastReportURL = bundle.getString("URL");
        RequestQueue queue= Volley.newRequestQueue(NewsCarsApp.getContext());
        StringRequest stringRequest=new StringRequest(fastReportURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                FastReportBean bean=gson.fromJson(response,FastReportBean.class);
                List<FastReportBean.ResultBean.ListBean> data=bean.getResult().getList();
                adapter.setData(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
