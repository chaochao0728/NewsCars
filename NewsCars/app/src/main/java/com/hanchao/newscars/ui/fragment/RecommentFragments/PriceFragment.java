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
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
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
        listView = byView(R.id.fragment_price_listView);
    }

    @Override
    protected void initDatas() {
        adapter = new PriceAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String TheUrl = bundle.getString("URL");
        VolleyInstance.getInstance().startRequest(TheUrl, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                NewsBean bean = gson.fromJson(result, NewsBean.class);
                List<NewsBean.ResultBean.NewslistBean> data = bean.getResult().getNewslist();
                adapter.setData(data);
            }

            @Override
            public void failure() {

            }
        });
    }
}
