package com.hanchao.newscars.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.adapter.ForumAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/8.
 */
public class ForumFragment extends AbsBaseFragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment>data;
    private ForumAdapter adapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void initView() {
        viewPager=byView(R.id.fragment_forum_viewPager);
        tabLayout=byView(R.id.fragment_forum_tabLayout);
    }

    @Override
    protected void initDatas() {
        data=new ArrayList<>();
        data.add(new PickRecommendFragment());
        data.add(new HotTopicFragment());
        data.add(new CommonForumFragment());
        adapter=new ForumAdapter(getChildFragmentManager(),data);
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabTextColors(Color.GRAY,Color.BLUE);
        tabLayout.setupWithViewPager(viewPager);

    }
}
