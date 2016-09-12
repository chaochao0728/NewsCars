package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

public class VidaoFragment extends AbsBaseFragment {
    public static VidaoFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("tv", str);
        VidaoFragment fragment = new VidaoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_vidao;
    }

    @Override
    protected void initView() {
        tv = byView(R.id.fragment_vidao_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("tv");
        tv.setText(string);
    }
}
