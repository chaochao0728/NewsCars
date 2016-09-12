package com.hanchao.newscars.ui.fragment.findcarsfragment;

import android.os.Bundle;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/10.
 * 找二手车的fragment
 */
public class FindSecondCarFragment extends AbsBaseFragment {
    private TextView tv;

    public static FindSecondCarFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("tv", str);
        FindSecondCarFragment fragment = new FindSecondCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_find_secondcar;
    }

    @Override
    protected void initView() {
        tv = byView(R.id.fragment_find_secondcar_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("tv");
        tv.setText(string);
    }
}
