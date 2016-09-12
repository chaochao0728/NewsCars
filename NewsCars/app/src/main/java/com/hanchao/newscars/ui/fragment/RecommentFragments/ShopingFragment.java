package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 */
public class ShopingFragment extends AbsBaseFragment {

    public static ShopingFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("tv", str);
        ShopingFragment fragment = new ShopingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_shoping;
    }

    @Override
    protected void initView() {
        tv = byView(R.id.fragment_shoping_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("tv");
        tv.setText(string);
    }


}
