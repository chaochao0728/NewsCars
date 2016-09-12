package com.hanchao.newscars.ui.fragment.forumfragments;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.adapter.PickRecommendRecyclerAdapter;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;
import com.hanchao.newscars.ui.fragment.ManyFragment;
import com.hanchao.newscars.utils.OnRecycleItemClik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class PickRecommendFragment extends AbsBaseFragment implements View.OnClickListener {
    private RecyclerView rv;
    private PickRecommendRecyclerAdapter adapter;
    private List<String> data;
    private ImageView choutiImageView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_pick_recommend;
    }

    @Override
    protected void initView() {
        rv = byView(R.id.fragment_picRecommend_recycler);
        choutiImageView = byView(R.id.fragment_pick_recommend_image_chouTi);
    }

    @Override
    protected void initDatas() {
        buildDatas();
        adapter = new PickRecommendRecyclerAdapter(data, context);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        adapter.setOnRecycleItemClik(new OnRecycleItemClik() {
            @Override
            public void OnRvItemClicListener(int pos, String str) {
                Toast.makeText(context, "pos:" + pos, Toast.LENGTH_SHORT).show();
                FragmentManager managers = getChildFragmentManager();
                FragmentTransaction transaction = managers.beginTransaction();
                transaction.replace(R.id.fragment_picRecommend_replaceView, new ManyFragment());
                transaction.commit();
            }
        });
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_picRecommend_replaceView, new ManyFragment());
        fragmentTransaction.commit();
        choutiImageView.setOnClickListener(this);
    }

    private void buildDatas() {
        data = new ArrayList<>();
        data.add("全部");
        data.add("媳妇当车模");
        data.add("美人" + "'记'");
        data.add("论坛红人馆");
        data.add("论坛讲师");
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

                break;
        }
    }
}
