package com.hanchao.newscars.ui.fragment;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.ManyBean;
import com.hanchao.newscars.ui.adapter.ManyAdapter;
import com.hanchao.newscars.ui.app.NewsCarsApp;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 精品推荐的上面的40个fragment通用fragment
 */
public class ManyFragment extends AbsBaseFragment {
    private ListView listView;
    private ManyAdapter adapter;
    public static ManyFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("URL",str);
        ManyFragment fragment = new ManyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_many;
    }

    @Override
    protected void initView() {
        listView=byView(R.id.fragment_many_listView);
    }

    @Override
    protected void initDatas() {
        adapter=new ManyAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle=getArguments();
        String theUrl=bundle.getString("URL");
        RequestQueue queue= Volley.newRequestQueue(NewsCarsApp.getContext());
        StringRequest stringRequest=new StringRequest(theUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                ManyBean bean=gson.fromJson(response,ManyBean.class);
                List<ManyBean.ResultBean.ListBean> data=bean.getResult().getList();
                adapter.setDatas(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
