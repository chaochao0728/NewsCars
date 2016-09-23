package com.hanchao.newscars.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.net.NetValues;
import com.hanchao.newscars.ui.adapter.FindCarAdapter;
import com.hanchao.newscars.ui.fragment.findcarsfragment.BrandFragment;
import com.hanchao.newscars.ui.fragment.findcarsfragment.FindSecondCarFragment;
import com.hanchao.newscars.ui.fragment.findcarsfragment.PickUpFragment;
import com.hanchao.newscars.ui.fragment.findcarsfragment.PriceDownFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/8.
 * 找车的fragment
 */
public class FindCarFragment extends AbsBaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> datas;
    private FindCarAdapter adapter;

    public static FindCarFragment newInstance() {
        FindCarFragment fragment = new FindCarFragment();
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_find_car;
    }

    @Override
    protected void initView() {
        tabLayout = byView(R.id.fragment_find_car_tabLayout);
        viewPager = byView(R.id.fragment_find_car_viewPager);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        datas.add(BrandFragment.newInstance(NetValues.BRAND_LISTVIEW));
        datas.add(PickUpFragment.newInstance(NetValues.PICKUP));
        datas.add(PriceDownFragment.newInstance("降价"));
        datas.add(FindSecondCarFragment.newInstance("找二手车"));
        adapter = new FindCarAdapter(getChildFragmentManager(), datas);
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabTextColors(Color.GRAY, Color.BLUE);
        tabLayout.setupWithViewPager(viewPager);
    }
}
