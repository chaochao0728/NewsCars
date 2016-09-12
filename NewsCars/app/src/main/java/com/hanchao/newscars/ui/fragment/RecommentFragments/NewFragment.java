package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 */
public class NewFragment extends AbsBaseFragment {
    private TextView tv;

    public static NewFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("tv", str);
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
        tv = byView(R.id.fragment_new_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("tv");
        tv.setText(string);
    }
}
