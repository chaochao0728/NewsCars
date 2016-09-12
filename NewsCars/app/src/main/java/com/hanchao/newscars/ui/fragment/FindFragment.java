package com.hanchao.newscars.ui.fragment;

import android.os.Bundle;

import com.hanchao.newscars.R;

/**
 * Created by dllo on 16/9/8.
 * 发现的fragment
 */
public class FindFragment extends AbsBaseFragment {
    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
