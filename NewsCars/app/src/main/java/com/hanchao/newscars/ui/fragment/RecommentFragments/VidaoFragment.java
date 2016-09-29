package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.NewsBean;
import com.hanchao.newscars.mode.bean.VideoBean;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.activity.VidaoFragmentToAty;
import com.hanchao.newscars.ui.adapter.NewsAdapter;
import com.hanchao.newscars.ui.adapter.VidaoFragmentAdapter;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * 视频的fragment
 */

public class VidaoFragment extends AbsBaseFragment {
    private ListView listView;
    private VidaoFragmentAdapter adapter;
    private List<VideoBean.ResultBean.ListBean> data;

    public static VidaoFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("URL", str);
        VidaoFragment fragment = new VidaoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_vidao;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.fragment_video_listView);
    }

    @Override
    protected void initDatas() {
        adapter = new VidaoFragmentAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String TheUrl = bundle.getString("URL");
        VolleyInstance.getInstance().startRequest(TheUrl, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                VideoBean bean = gson.fromJson(result, VideoBean.class);
                data = bean.getResult().getList();
                adapter.setData(data);
            }

            @Override
            public void failure() {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Id = data.get(position).getId() + "";
                Intent intent = new Intent(context, VidaoFragmentToAty.class);
                intent.putExtra("vidaoId", Id);
                startActivity(intent);
            }
        });
    }
}
