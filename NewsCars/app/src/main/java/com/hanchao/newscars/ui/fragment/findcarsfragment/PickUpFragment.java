package com.hanchao.newscars.ui.fragment.findcarsfragment;

import android.os.Bundle;
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
import com.hanchao.newscars.mode.bean.PickUpBean;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.adapter.PickUpAdapter;
import com.hanchao.newscars.ui.app.NewsCarsApp;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 筛选的fragment
 */
public class PickUpFragment extends AbsBaseFragment implements View.OnClickListener {
    private TextView pickUpTv;
    private ListView listView;
    private PickUpAdapter adapter;

    public static PickUpFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("URL", str);
        PickUpFragment fragment = new PickUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_pick_up;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.fragment_pickUp_listView);
        pickUpTv = byView(R.id.fragment_pickUp_Tv);
    }

    @Override
    protected void initDatas() {
        pickUpTv.setOnClickListener(this);
        adapter = new PickUpAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String pickUpUrl = bundle.getString("URL");
        VolleyInstance.getInstance().startRequest(pickUpUrl, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                PickUpBean bean = gson.fromJson(result, PickUpBean.class);
                List<PickUpBean.ResultBean.SeriesBean> datas = bean.getResult().getSeries();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_pickUp_Tv:
                break;
        }
    }
}
