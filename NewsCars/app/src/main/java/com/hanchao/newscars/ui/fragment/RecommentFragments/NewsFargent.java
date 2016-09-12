package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 */
public class NewsFargent extends AbsBaseFragment {
    public static NewsFargent newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("tv", str);
        NewsFargent fragment = new NewsFargent();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        tv = byView(R.id.fragment_news_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("tv");
        tv.setText(string);
    }
}
