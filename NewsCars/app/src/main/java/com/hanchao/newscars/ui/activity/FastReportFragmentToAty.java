package com.hanchao.newscars.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.FastReportToAtyBean;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.adapter.FastReportSecondToAtyAdapter;
import com.hanchao.newscars.ui.adapter.FastReportToAtyAdapter;

import java.util.List;

public class FastReportFragmentToAty extends AbsBaseActivity {
    //    private WebView webView;
    //第二界面的listView
    private ListView listView;
    private List<FastReportToAtyBean.ResultBean.MessagelistBean> data;

    @Override
    protected int setLayout() {
        return R.layout.activity_fast_report_fragment_to_aty;
    }

    @Override
    protected void initView() {
//        webView = byView(R.id.activity_fast_report_fragment_to_aty_web_view);
        listView = byView(R.id.activity_fast_report_to_aty_list_view);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String Url = "http://cont.app.autohome.com.cn/autov5.0.0/content/News/fastnewscontent-n" +
                intent.getStringExtra("fastReportId") + "-lastid0-o1.json";
//        webView.loadUrl(Url);
        Log.d("1111", Url);
        final FastReportToAtyAdapter atyAdapter = new FastReportToAtyAdapter(this);
        listView.setAdapter(atyAdapter);
        VolleyInstance.getInstance().startRequest(Url, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                FastReportToAtyBean bean = gson.fromJson(result, FastReportToAtyBean.class);
                data = bean.getResult().getMessagelist();
                atyAdapter.setData(data);
            }

            @Override
            public void failure() {

            }
        });


    }
}
