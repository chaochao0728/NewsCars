package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.NewsBean;
import com.hanchao.newscars.ui.adapter.PriceAdapter;
import com.hanchao.newscars.ui.app.NewsCarsApp;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 行情fragment
 */
public class PriceFragment extends AbsBaseFragment {
    private ListView listView;
    private PriceAdapter adapter;
    public static PriceFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("URL", str);
        PriceFragment fragment = new PriceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_price;
    }

    @Override
    protected void initView() {
        listView=byView(R.id.fragment_price_listView);
    }

    @Override
    protected void initDatas() {
        adapter=new PriceAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String TheUrl = bundle.getString("URL");
        RequestQueue queue = Volley.newRequestQueue(NewsCarsApp.getContext());
        StringRequest request = new StringRequest(TheUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Log.d("2222", response);
                NewsBean bean = gson.fromJson(response, NewsBean.class);
                List<NewsBean.ResultBean.NewslistBean> data = bean.getResult().getNewslist();
                adapter.setData(data);
                Log.d("2222", "data:" + data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}
