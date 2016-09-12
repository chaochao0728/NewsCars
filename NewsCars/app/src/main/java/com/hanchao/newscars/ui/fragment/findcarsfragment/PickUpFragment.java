package com.hanchao.newscars.ui.fragment.findcarsfragment;

import android.os.Bundle;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/10.
 * 筛选的fragment
 */
public class PickUpFragment extends AbsBaseFragment {
    private TextView tv;

    public static PickUpFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("tv", str);
        PickUpFragment fragment = new PickUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_pick_up;
    }

    @Override
    protected void initView() {
        tv = byView(R.id.fragment_pick_up_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("tv");
        tv.setText(string);
    }
}
