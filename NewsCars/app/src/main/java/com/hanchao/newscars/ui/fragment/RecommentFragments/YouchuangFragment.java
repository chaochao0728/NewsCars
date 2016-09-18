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
import com.hanchao.newscars.mode.bean.YouchuangBean;
import com.hanchao.newscars.ui.adapter.YouchuangFfragmentAdapter;
import com.hanchao.newscars.ui.app.NewsCarsApp;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 优创fragment
 */
public class YouchuangFragment extends AbsBaseFragment {
    private ListView listView;
    private YouchuangFfragmentAdapter adapter;

    public static YouchuangFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("URL", str);
        YouchuangFragment fragment = new YouchuangFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_youchuang;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.fragment_youchuan_listView);
    }

    @Override
    protected void initDatas() {
        adapter = new YouchuangFfragmentAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String YouchuangFragmentURL = bundle.getString("URL");
        RequestQueue queue = Volley.newRequestQueue(NewsCarsApp.getContext());
        StringRequest request = new StringRequest(YouchuangFragmentURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("111111", response);
                Gson gson = new Gson();
                YouchuangBean bean = gson.fromJson(response, YouchuangBean.class);
                List<YouchuangBean.ResultBean.NewslistBean> data = bean.getResult().getNewslist();
                Log.d("111111", "data:" + data);
                adapter.setData(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}
