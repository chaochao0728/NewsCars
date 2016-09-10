package com.hanchao.newscars.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class RecommendAdapter extends FragmentPagerAdapter{
    private List<Fragment>datas;
    private String title[]={"最新","优创+","快报","视频",
            "新闻","评测","导购","行情","用车","技术","文化",
    "改装","游记","原创视频","说客"};
    public RecommendAdapter(FragmentManager fm, List<Fragment> datas) {
        super(fm);
        this.datas = datas;
    }
    public RecommendAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return datas!=null?datas.get(position):null;
    }

    @Override
    public int getCount() {
        return datas!=null? datas.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
