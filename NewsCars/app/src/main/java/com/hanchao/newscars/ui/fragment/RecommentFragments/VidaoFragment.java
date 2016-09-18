package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

/**
 * 视频的fragment
 */

public class VidaoFragment extends AbsBaseFragment {
    private ListView listView;
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
        listView=byView(R.id.fragment_video_listView);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("URL");

    }
}
