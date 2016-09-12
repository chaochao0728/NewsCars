package com.hanchao.newscars.ui.fragment;

import android.util.Log;

import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;

/**
 * Created by dllo on 16/9/9.
 */
public class ShopingFragment extends AbsBaseFragment implements VolleyResult {
    private String dataUrl="http://app.api.autohome.com.cn/autov" +
            "4.8.8/news/newslist-pm1-c0-nt60-p1-s30-l0.json";
    @Override
    protected int setLayout() {
        return R.layout.fragment_shoping;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startRequest(dataUrl,this);
    }

    @Override
    public void success(String result) {
        Log.d("ShopingFragment", result);
    }

    @Override
    public void failure() {

    }
}
