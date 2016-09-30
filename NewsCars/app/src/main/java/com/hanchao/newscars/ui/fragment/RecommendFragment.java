package com.hanchao.newscars.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.net.NetValues;
import com.hanchao.newscars.ui.adapter.RecommendAdapter;
import com.hanchao.newscars.ui.fragment.RecommentFragments.CultureFragment;
import com.hanchao.newscars.ui.fragment.RecommentFragments.FastReportFragment;
import com.hanchao.newscars.ui.fragment.RecommentFragments.NewFragment;
import com.hanchao.newscars.ui.fragment.RecommentFragments.NewsFargent;
import com.hanchao.newscars.ui.fragment.RecommentFragments.PriceFragment;
import com.hanchao.newscars.ui.fragment.RecommentFragments.TalkFragment;
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

    /**
     * 自定义data集合
     */
    private void buildDatas() {
        data = new ArrayList<>();
        data.add(NewFragment.newInstance(NetValues.RECOMMEND_URL));
        data.add(YouchuangFragment.newInstance(NetValues.YOUCHUANG));
        data.add(FastReportFragment.newInstance(NetValues.FAST_REPORT));
        data.add(VidaoFragment.newInstance(NetValues.VIDEO));
        data.add(NewsFargent.newInstance(NetValues.NEWS));
        data.add(NewsFargent.newInstance(NetValues.EVALUATION));
        data.add(NewsFargent.newInstance(NetValues.SHOPPING));
        data.add(PriceFragment.newInstance(NetValues.HANG_QING));
        data.add(NewsFargent.newInstance(NetValues.USE_CAR));
        data.add(NewsFargent.newInstance(NetValues.SKILL));
        data.add(CultureFragment.newInstance(NetValues.CULTURE));
        data.add(CultureFragment.newInstance(NetValues.CHANGGE_DRESS));
        data.add(NewsFargent.newInstance(NetValues.TRALE));
        data.add(VidaoFragment.newInstance(NetValues.ORIGINAL_VIDEO));
        data.add(TalkFragment.newInstance(NetValues.TALKPERSON));
    }


}
