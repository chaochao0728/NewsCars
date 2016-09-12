package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 */
public class YouchuangFragment extends AbsBaseFragment {
    private TextView tv;

    public static YouchuangFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("tv", str);
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
        tv = byView(R.id.fragment_youchuang_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("tv");
        tv.setText(string);
    }
}
