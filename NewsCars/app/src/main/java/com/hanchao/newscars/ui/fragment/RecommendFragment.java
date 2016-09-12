package com.hanchao.newscars.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.adapter.RecommendAdapter;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.fragment.RecommentFragments.CultureFragment;
import com.hanchao.newscars.ui.fragment.RecommentFragments.FastReportFragment;
import com.hanchao.newscars.ui.fragment.RecommentFragments.NewFragment;
import com.hanchao.newscars.ui.fragment.RecommentFragments.NewsFargent;
import com.hanchao.newscars.ui.fragment.RecommentFragments.PriceFragment;
import com.hanchao.newscars.ui.fragment.RecommentFragments.ShopingFragment;
import com.hanchao.newscars.ui.fragment.RecommentFragments.VidaoFragment;
import com.hanchao.newscars.ui.fragment.RecommentFragments.YouchuangFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/8.
 */
public class RecommendFragment extends AbsBaseFragment implements VolleyResult {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> data;
    private RecommendAdapter adapter;
    private String recommendUrl = "http://app.api.autohome.co" +
            "m.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l0.json";

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        viewPager = byView(R.id.fragment_recommend_viewPager);
        tabLayout = byView(R.id.fragment_recommend_tabLayout);

    }

    @Override
    protected void initDatas() {
        buildDatas();
        adapter = new RecommendAdapter(getChildFragmentManager(), data);
        viewPager.setAdapter(adapter);
        tabLayout.setTabTextColors(Color.GRAY, Color.BLUE);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        VolleyInstance.getInstance().startRequest(recommendUrl, this);
    }

    private void buildDatas() {
        data = new ArrayList<>();
        data.add(new NewFragment());
        data.add(new YouchuangFragment());
        data.add(new FastReportFragment());
        data.add(new VidaoFragment());
        data.add(new NewsFargent());
        data.add(new NewsFargent());
        data.add(new ShopingFragment());
        data.add(new PriceFragment());
        data.add(new NewsFargent());
        data.add(new NewsFargent());
        data.add(new CultureFragment());
        data.add(new CultureFragment());
        data.add(new NewsFargent());
        data.add(new VidaoFragment());
        data.add(new NewsFargent());
    }

    @Override
    public void success(String result) {
        Log.d("RecommendFragment", result);
    }

    @Override
    public void failure() {

    }
}
