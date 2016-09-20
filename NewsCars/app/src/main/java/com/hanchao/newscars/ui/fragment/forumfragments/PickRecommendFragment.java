package com.hanchao.newscars.ui.fragment.forumfragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.net.NetValues;
import com.hanchao.newscars.ui.adapter.PickRecommendRecyclerAdapter;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;
import com.hanchao.newscars.ui.fragment.ManyFragment;
import com.hanchao.newscars.utils.OnRecycleItemClik;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 精品推荐的fragment
 */
public class PickRecommendFragment extends AbsBaseFragment implements View.OnClickListener {
    private RecyclerView rv;
    private PickRecommendRecyclerAdapter adapter;
    private List<String> data;
    private ImageView choutiImageView;
    private DrawerLayout rootView;
    private LinearLayout drawerView;
    private RecyclerView rvDrawer;
    private String list[] = {NetValues.FORUM_ANSLESE_0, NetValues.FORUM_ANSLESE_1, NetValues.FORUM_ANSLESE_2, NetValues.FORUM_ANSLESE_3
            , NetValues.FORUM_ANSLESE_4, NetValues.FORUM_ANSLESE_5, NetValues.FORUM_ANSLESE_6, NetValues.FORUM_ANSLESE_7
            , NetValues.FORUM_ANSLESE_8, NetValues.FORUM_ANSLESE_9, NetValues.FORUM_ANSLESE_10, NetValues.FORUM_ANSLESE_11
            , NetValues.FORUM_ANSLESE_12, NetValues.FORUM_ANSLESE_13, NetValues.FORUM_ANSLESE_14, NetValues.FORUM_ANSLESE_15
            , NetValues.FORUM_ANSLESE_16, NetValues.FORUM_ANSLESE_17, NetValues.FORUM_ANSLESE_18, NetValues.FORUM_ANSLESE_19
            , NetValues.FORUM_ANSLESE_20, NetValues.FORUM_ANSLESE_21, NetValues.FORUM_ANSLESE_22, NetValues.FORUM_ANSLESE_23
            , NetValues.FORUM_ANSLESE_24, NetValues.FORUM_ANSLESE_25, NetValues.FORUM_ANSLESE_26, NetValues.FORUM_ANSLESE_27
            , NetValues.FORUM_ANSLESE_28, NetValues.FORUM_ANSLESE_29, NetValues.FORUM_ANSLESE_30, NetValues.FORUM_ANSLESE_31
            , NetValues.FORUM_ANSLESE_32, NetValues.FORUM_ANSLESE_33, NetValues.FORUM_ANSLESE_34, NetValues.FORUM_ANSLESE_35
            , NetValues.FORUM_ANSLESE_36, NetValues.FORUM_ANSLESE_37, NetValues.FORUM_ANSLESE_38, NetValues.FORUM_ANSLESE_39, NetValues.FORUM_ANSLESE_40};

    @Override
    protected int setLayout() {
        return R.layout.fragment_pick_recommend;
    }

    @Override
    protected void initView() {
        rv = byView(R.id.fragment_picRecommend_recycler);
        choutiImageView = byView(R.id.fragment_pick_recommend_image_chouTi);
        rootView = byView(R.id.main_foot);
        drawerView = byView(R.id.fragment_pick_recommend_drawer);
        rvDrawer = byView(R.id.fragment_pick_recommend_drawer_recyclerView);
    }

    @Override
    protected void initDatas() {
        buildDatas();
        adapter = new PickRecommendRecyclerAdapter(data, context);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        /**
         * 抽屉的recycle
         */
        LinearLayoutManager managers = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvDrawer.setLayoutManager(managers);
        rvDrawer.setAdapter(adapter);
        adapter.setOnRecycleItemClik(new OnRecycleItemClik() {
            @Override
            public void OnRvItemClicListener(int pos, String str) {
                FragmentManager managers = getChildFragmentManager();
                FragmentTransaction transaction = managers.beginTransaction();
                transaction.replace(R.id.fragment_picRecommend_replaceView, ManyFragment.newInstance(list[pos]));
                transaction.commit();
            }
        });
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_picRecommend_replaceView, ManyFragment.newInstance(NetValues.FORUM_ANSLESE_0));
        fragmentTransaction.commit();
        /**
         * 抽屉点击事件
         */
        choutiImageView.setOnClickListener(this);
    }

    private void buildDatas() {
        data = new ArrayList<>();
        data.add("全部");
        data.add("媳妇当车模");
        data.add("美人" + "'记'");
        data.add("论坛红人馆");
        data.add("论坛讲师");
        data.add("汽车之家十年");
        data.add("精挑细选");
        data.add("现身说法");
        data.add("高端阵地");
        data.add("电动车");
        data.add("汇买车");
        data.add("行车点评");
        data.add("超级驾驶员");
        data.add("海外购车");
        data.add("经典老车");
        data.add("妹子选车");
        data.add("优惠购车");
        data.add("原创大片");
        data.add("顶配风采");
        data.add("改装有理");
        data.add("养车有道");
        data.add("首发阵营");
        data.add("新车直播");
        data.add("历史选题");
        data.add("摩友天地");
        data.add("蜜月之旅");
        data.add("甜蜜婚礼");
        data.add("摄影课堂");
        data.add("车友聚会");
        data.add("单车部落");
        data.add("杂谈俱乐部");
        data.add("华北游记");
        data.add("西南游记");
        data.add("东北游记");
        data.add("西北游记");
        data.add("华中游记");
        data.add("华南游记");
        data.add("华东游记");
        data.add("港澳台游记");
        data.add("海外游记");
        data.add("沧海遗珠");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_pick_recommend_image_chouTi:
//                int height= ScreenSize.getHight(context);
//                rootView.setMinimumHeight(height);
                rootView.openDrawer(drawerView);
                break;
        }
    }
}
