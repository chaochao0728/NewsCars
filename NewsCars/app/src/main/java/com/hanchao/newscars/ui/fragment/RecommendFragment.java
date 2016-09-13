package com.hanchao.newscars.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.adapter.RecommendAdapter;
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
 * 推荐的fragment
 */
public class RecommendFragment extends AbsBaseFragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> data;
    private RecommendAdapter adapter;
    private String recommendUrl = "http://app.api.autohome.com." +
            "cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l0.json";

    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();
        return fragment;
    }

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
    }

    private void buildDatas() {
        data = new ArrayList<>();
        data.add(NewFragment.newInstance(recommendUrl));
        data.add(YouchuangFragment.newInstance("优创+"));
        data.add(FastReportFragment.newInstance("快报"));
        data.add(VidaoFragment.newInstance("视频"));
        data.add(NewsFargent.newInstance("新闻"));
        data.add(NewsFargent.newInstance("评测"));
        data.add(ShopingFragment.newInstance("导购"));
        data.add(PriceFragment.newInstance("行情"));
        data.add(NewsFargent.newInstance("用车"));
        data.add(NewsFargent.newInstance("技术"));
        data.add(CultureFragment.newInstance("文化"));
        data.add(CultureFragment.newInstance("改装"));
        data.add(NewsFargent.newInstance("游记"));
        data.add(VidaoFragment.newInstance("原创视频"));
        data.add(NewsFargent.newInstance("说客"));
    }


}
