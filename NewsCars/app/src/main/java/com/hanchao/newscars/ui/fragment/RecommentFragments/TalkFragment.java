package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.NewsBean;
import com.hanchao.newscars.mode.bean.TalkBean;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.activity.NewsFragmentToAty;
import com.hanchao.newscars.ui.adapter.TalkAdapter;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class TalkFragment extends AbsBaseFragment{
    private ListView listview;
    private List<TalkBean.ResultBean.ListBean> datas;
    private TalkAdapter adapter;
    public static TalkFragment newInstance(String string) {

        Bundle args = new Bundle();
        args.putString("URL",string);
        TalkFragment fragment = new TalkFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        listview = byView(R.id.fragment_news_listView);
    }

    @Override
    protected void initDatas() {
        adapter=new TalkAdapter(context);
        listview.setAdapter(adapter);
        Bundle bundle = getArguments();
        String TheUrl = bundle.getString("URL");
        VolleyInstance.getInstance().startRequest(TheUrl, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                TalkBean bean = gson.fromJson(result, TalkBean.class);
                datas = bean.getResult().getList();
                adapter.setData(datas);
            }

            @Override
            public void failure() {

            }
        });
        //点击进入二级界面
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Id = datas.get(position).getId() + "";
                Intent intent = new Intent(context, NewsFragmentToAty.class);
                intent.putExtra("newsId", Id);
                startActivity(intent);
            }
        });
    }
}
