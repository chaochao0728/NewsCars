package com.hanchao.newscars.ui.fragment.findcarsfragment;

import android.os.Bundle;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/10.
 * 品牌的fragment
 */
public class BrandFragment extends AbsBaseFragment {
    private TextView tv;

    public static BrandFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("tv", str);
        BrandFragment fragment = new BrandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_brand;
    }

    @Override
    protected void initView() {
        tv = byView(R.id.fragment_brand_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("tv");
        tv.setText(string);
    }
}
