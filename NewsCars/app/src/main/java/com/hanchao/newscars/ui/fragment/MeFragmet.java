package com.hanchao.newscars.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.activity.CollectActivity;

/**
 * Created by dllo on 16/9/8.
 * 我的fragment
 */
public class MeFragmet extends AbsBaseFragment {
    private LinearLayout collectView;

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
        collectView = byView(R.id.fragment_me_collect);
    }

    @Override
    protected void initDatas() {
        collectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CollectActivity.class);
                startActivity(intent);

            }
        });
    }
}
