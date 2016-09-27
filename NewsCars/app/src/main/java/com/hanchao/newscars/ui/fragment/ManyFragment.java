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
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
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
        args.putString("URL", str);
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
        listView = byView(R.id.fragment_many_listView);
    }

    @Override
    protected void initDatas() {
        adapter = new ManyAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String theUrl = bundle.getString("URL");
        VolleyInstance.getInstance().startRequest(theUrl, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                ManyBean bean = gson.fromJson(result, ManyBean.class);
                List<ManyBean.ResultBean.ListBean> data = bean.getResult().getList();
                adapter.setDatas(data);
            }

            @Override
            public void failure() {

            }
        });
    }
}
