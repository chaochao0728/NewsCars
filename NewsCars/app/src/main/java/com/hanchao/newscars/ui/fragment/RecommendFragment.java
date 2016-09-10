package com.hanchao.newscars.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.adapter.RecommendAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/8.
 */
public class RecommendFragment extends AbsBaseFragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment>data;
    private RecommendAdapter adapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        viewPager=byView(R.id.fragment_recommend_viewPager);
        tabLayout=byView(R.id.fragment_recommend_tabLayout);

    }

    @Override
    protected void initDatas() {
        data=new ArrayList<>();
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
        adapter=new RecommendAdapter(getChildFragmentManager(),data);
        viewPager.setAdapter(adapter);
        tabLayout.setTabTextColors(Color.GREEN,Color.RED);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

    }

}
