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
import com.hanchao.newscars.mode.bean.NewBean;
import com.hanchao.newscars.ui.activity.NewFragmentToAty;
import com.hanchao.newscars.ui.adapter.NewFragmentAdapter;
import com.hanchao.newscars.ui.app.NewsCarsApp;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.net.URL;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class NewFragment extends AbsBaseFragment {
    private ListView listView;
    private NewFragmentAdapter adapter;

    public static NewFragment newInstance(String string) {

        Bundle args = new Bundle();
        args.putString("URL", string);
        NewFragment fragment = new NewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_new;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.fragment_new_listView);
    }

    @Override
    protected void initDatas() {
        adapter = new NewFragmentAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String NewFragmentUrl = bundle.getString("URL");
        RequestQueue queue = Volley.newRequestQueue(NewsCarsApp.getContext());
        StringRequest sr = new StringRequest(NewFragmentUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("1111", response);
                Gson gson = new Gson();
                NewBean bean = gson.fromJson(response, NewBean.class);
                Log.d("1111", "bean:" + bean);
                List<NewBean.ResultBean.NewslistBean> datas = bean.getResult().getNewslist();
//                Log.d("1111", "datas:" + datas);
                adapter.setData(datas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(sr);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewBean.ResultBean.NewslistBean bean = (NewBean.ResultBean.NewslistBean) parent.getItemAtPosition(position);
                String title = bean.getTitle();
                Intent intent = new Intent(getContext(), NewFragmentToAty.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }
}
