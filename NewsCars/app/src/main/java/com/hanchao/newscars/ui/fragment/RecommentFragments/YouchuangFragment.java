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
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
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
        VolleyInstance.getInstance().startRequest(YouchuangFragmentURL, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                YouchuangBean bean = gson.fromJson(result, YouchuangBean.class);
                List<YouchuangBean.ResultBean.NewslistBean> data = bean.getResult().getNewslist();
                adapter.setData(data);
            }

            @Override
            public void failure() {

            }
        });
    }
}
