package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import com.hanchao.newscars.utils.ScreenSize;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 快报fragment
 */
public class FastReportFragment extends AbsBaseFragment implements View.OnClickListener {
    private ListView listView;
    private LinearLayout rootView;
    private FastReportAdapter adapter;
    private TextView allBrandTv, allGradeTv;

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
        listView = byView(R.id.fragment_fastreport_listView);
        allBrandTv = byView(R.id.fragment_fastReport_allBrandTv);
        allGradeTv = byView(R.id.fragment_fastReport_allGradeTv);
        rootView = byView(R.id.fastReportFragment_rootView);
    }

    @Override
    protected void initDatas() {
        adapter = new FastReportAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String fastReportURL = bundle.getString("URL");
        RequestQueue queue = Volley.newRequestQueue(NewsCarsApp.getContext());
        StringRequest stringRequest = new StringRequest(fastReportURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                FastReportBean bean = gson.fromJson(response, FastReportBean.class);
                List<FastReportBean.ResultBean.ListBean> data = bean.getResult().getList();
                adapter.setData(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
        allBrandTv.setOnClickListener(this);
        allGradeTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_fastReport_allBrandTv:
                CreatWindow();
                break;
            case R.id.fragment_fastReport_allGradeTv:
                CreatWindow();
                break;
        }
    }

    /**
     * 自定义方法创建一个popupwindow
     */
    private void CreatWindow() {
        PopupWindow pw = new PopupWindow(getContext());
        int height = ScreenSize.getHight(getContext());
        pw.setHeight(height);
        pw.setWidth(400);
        View v = LayoutInflater.from(context).inflate(R.layout.fastreport_fragment_popwindow, null);
        pw.setContentView(v);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.showAtLocation(rootView, Gravity.RIGHT, 0, 0);
    }
}
