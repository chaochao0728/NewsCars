package com.hanchao.newscars.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.adapter.FindCarAdapter;
import com.hanchao.newscars.ui.fragment.findcarsfragment.BrandFragment;
import com.hanchao.newscars.ui.fragment.findcarsfragment.FindSecondCarFragment;
import com.hanchao.newscars.ui.fragment.findcarsfragment.PickUpFragment;
import com.hanchao.newscars.ui.fragment.findcarsfragment.PriceDownFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/8.
 */
public class FindCarFragment extends AbsBaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> datas;
    private FindCarAdapter adapter;

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
        datas.add(new BrandFragment());
        datas.add(new PickUpFragment());
        datas.add(new PriceDownFragment());
        datas.add(new FindSecondCarFragment());
        adapter = new FindCarAdapter(getChildFragmentManager(), datas);
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabTextColors(Color.GRAY, Color.BLUE);
        tabLayout.setupWithViewPager(viewPager);
    }
}
