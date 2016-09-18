package com.hanchao.newscars.ui.activity;

import android.content.Intent;
import android.widget.TextView;

import com.hanchao.newscars.R;

/**
 * Created by dllo on 16/9/13.
 * 最新的详情页
 */
public class NewFragmentToAty extends AbsBaseActivity {
    private TextView titleTv;

    @Override
    protected int setLayout() {
        return R.layout.aty_newfragment_to;
    }

    @Override
    protected void initView() {
        titleTv = byView(R.id.newFragmentToAty_titleTv);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        titleTv.setText(title);
    }
}
