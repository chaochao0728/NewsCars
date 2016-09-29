package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.hanchao.newscars.ui.activity.NewsFragmentToAty;
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
    private List<NewsBean.ResultBean.NewslistBean> data;

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
                data = bean.getResult().getNewslist();
                adapter.setData(data);
            }

            @Override
            public void failure() {

            }
        });
        //点击进入二级界面 没有显示
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Id = data.get(position).getId() + "";
                Intent intent = new Intent(context, NewsFragmentToAty.class);
                intent.putExtra("newsId", Id);
                startActivity(intent);
            }
        });
    }
}
