package com.hanchao.newscars.ui.fragment;

import android.os.Bundle;

import com.hanchao.newscars.R;

/**
 * Created by dllo on 16/9/8.
 * 我的fragment
 */
public class MeFragmet extends AbsBaseFragment {
    public static MeFragmet newInstance() {
        MeFragmet fragment = new MeFragmet();
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
