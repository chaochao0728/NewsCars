package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 */
public class FastReportFragment extends AbsBaseFragment {
    public static FastReportFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("tv", str);
        FastReportFragment fragment = new FastReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_fastreport;
    }

    @Override
    protected void initView() {
        tv = byView(R.id.fragment_fastreport_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("tv");
        tv.setText(string);
    }
}
