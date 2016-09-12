package com.hanchao.newscars.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.hanchao.newscars.R;

/**
 * Created by dllo on 16/9/10.
 * 精品推荐的上面的40个fragment通用fragment
 */
public class ManyFragment extends AbsBaseFragment {
    private TextView tv;
    public static ManyFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("tv",str);
        ManyFragment fragment = new ManyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_many;
    }

    @Override
    protected void initView() {
        tv=byView(R.id.fragment_many_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle=getArguments();
        String string=bundle.getString("tv");
        tv.setText(string);
    }
}
