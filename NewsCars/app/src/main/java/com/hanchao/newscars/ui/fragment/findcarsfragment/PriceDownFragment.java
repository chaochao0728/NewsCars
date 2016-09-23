package com.hanchao.newscars.ui.fragment.findcarsfragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;
import com.hanchao.newscars.utils.ScreenSize;

/**
 * Created by dllo on 16/9/10.
 * 降价的fragment
 */
public class PriceDownFragment extends AbsBaseFragment implements View.OnClickListener {
    private TextView bradnTv, pricrTv, gradeTv, orderTv;
    private LinearLayout rootView;
    private ListView listView;

    public static PriceDownFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("URL", str);
        PriceDownFragment fragment = new PriceDownFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_pricedown;
    }

    @Override
    protected void initView() {
        bradnTv = byView(R.id.fragment_pricedow_brandpop);
        pricrTv = byView(R.id.fragment_pricedown_pricepop);
        gradeTv = byView(R.id.fragment_pricedown_gradepop);
        orderTv = byView(R.id.fragment_pricedown_orderpop);
        rootView = byView(R.id.fragment_pricedown_rootView);
        listView = byView(R.id.fragment_pricedown_listView);
    }

    @Override
    protected void initDatas() {
        bradnTv.setOnClickListener(this);
        pricrTv.setOnClickListener(this);
        gradeTv.setOnClickListener(this);
        orderTv.setOnClickListener(this);
        Bundle bundle = getArguments();
        String string = bundle.getString("URL");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_pricedow_brandpop:
                creatWindow();
                break;
            case R.id.fragment_pricedown_pricepop:
                creatWindow();
                break;
            case R.id.fragment_pricedown_gradepop:
                creatWindow();
                break;
            case R.id.fragment_pricedown_orderpop:
                creatWindow();
                break;
        }
    }

    /**
     * 自定义方法创建popupwindow
     */
    private void creatWindow() {
        PopupWindow pw = new PopupWindow(getContext());
        int height = ScreenSize.getHight(getContext());
        pw.setHeight(height);
        pw.setWidth(400);
        View v = LayoutInflater.from(context).inflate(R.layout.fastreport_fragment_popwindow, null);
        pw.setContentView(v);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.showAtLocation(rootView, Gravity.RIGHT, 0, 0);
    }
}
