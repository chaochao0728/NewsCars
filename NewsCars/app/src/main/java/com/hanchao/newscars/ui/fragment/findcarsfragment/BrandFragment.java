package com.hanchao.newscars.ui.fragment.findcarsfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.BrandListBean;
import com.hanchao.newscars.mode.bean.BrandPopBean;
import com.hanchao.newscars.mode.bean.BrandRecyclerBean;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.adapter.BrandFragmentAdapter;
import com.hanchao.newscars.ui.adapter.BrandPopAdapter;
import com.hanchao.newscars.ui.adapter.BrandRecyclerAdapter;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;
import com.hanchao.newscars.utils.OnRecycleItemClik;
import com.hanchao.newscars.utils.ScreenSize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/9/10.
 * 品牌的fragment
 */
public class BrandFragment extends AbsBaseFragment implements RadioGroup.OnCheckedChangeListener {
    private ExpandableListView listView;
    private BrandFragmentAdapter adapter;
    private List<String> groupList;
    private Map<String, List<BrandListBean.ResultBean.BrandlistBean.ListBean>> childs;
    private List<String> popgroulists;
    private Map<String, List<BrandPopBean.ResultBean.FctlistBean.SerieslistBean>> popchilds;
    private String Url = "http://223.99.255.20/cars.app.autohome.com.cn/dealer_v5.7.0/dealer/hotbrands-pm2.json";
    private List<BrandListBean.ResultBean.BrandlistBean> datas;
    private List<BrandPopBean.ResultBean.FctlistBean> data;
    private LinearLayout rootLayout;
    private RadioButton sellBtn, allBtn;
    private RadioGroup radioGroup;
    private String sellurl, allUrl;
    private ExpandableListView poplistView;
    private BrandPopAdapter brandPopAdapter;

    public static BrandFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("URL", str);
        BrandFragment fragment = new BrandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_brand;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.fragment_brand_expandableListView);
        rootLayout = byView(R.id.brand_rootView);

    }

    @Override
    protected void initDatas() {
        adapter = new BrandFragmentAdapter(context);
        listView.setGroupIndicator(null);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        final String string = bundle.getString("URL");
        VolleyInstance.getInstance().startRequest(string, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                groupList = new ArrayList<>();
                childs = new HashMap<>();
                BrandListBean bean = gson.fromJson(result, BrandListBean.class);
                //A-Z的解析
                datas = bean.getResult().getBrandlist();
                for (int i = 0; i < datas.size(); i++) {
                    String letter = datas.get(i).getLetter();
                    groupList.add(letter);
                    childs.put(letter, datas.get(i).getList());
                }
                adapter.setGroupList(groupList);
                adapter.setChildMaps(childs);
                for (int i = 0, count = datas.size(); i < count; i++) {
                    listView.expandGroup(i);
                }

            }

            @Override
            public void failure() {

            }
        });

        addHeadView();
    }

    private void createWindow() {
        PopupWindow pw = new PopupWindow(getContext());
        int height = ScreenSize.getHight(getContext());
        pw.setHeight(height);
        pw.setWidth(600);
        View v = LayoutInflater.from(context).inflate(R.layout.frament_brand_popwindow, null);
        sellBtn = (RadioButton) v.findViewById(R.id.fragment_brand_popwindow_sellBtn);
        allBtn = (RadioButton) v.findViewById(R.id.fragment_brand_popwindow_allBtn);
        radioGroup = (RadioGroup) v.findViewById(R.id.fragment_brand_popwindow_radiogroup);
        poplistView = (ExpandableListView) v.findViewById(R.id.fragment_brand_popwindow_recycler);
        brandPopAdapter = new BrandPopAdapter(context);
        poplistView.setGroupIndicator(null);
        poplistView.setAdapter(brandPopAdapter);
        radioGroup.check(R.id.fragment_brand_popwindow_sellBtn);
        radioGroup.setOnCheckedChangeListener(this);
        pw.setContentView(v);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.showAtLocation(rootLayout, Gravity.RIGHT, 0, 0);
    }

    /**
     * 添加头布局的方法
     */
    private void addHeadView() {
        View headView = LayoutInflater.from(context).inflate(R.layout.item_brandfragment_head, null);
        final RecyclerView recycler = (RecyclerView) headView.findViewById(R.id.item_brand_head_recycler);
        final BrandRecyclerAdapter adapters = new BrandRecyclerAdapter(context);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapters);
        VolleyInstance.getInstance().startRequest(Url, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                BrandRecyclerBean bean = gson.fromJson(result, BrandRecyclerBean.class);
                List<BrandRecyclerBean.ResultBean.ListBean> datas = bean.getResult().getList();
                adapters.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
        adapters.setOnRecycleItemClik(new OnRecycleItemClik() {
            @Override
            public void OnRvItemClicListener(int pos, String str) {
                allUrl = "http://app.api.autohome.com.cn/autov5.0.0/cars/seriesprice-pm2-b" + str + "-t2.json";
                sellurl = "http://app.api.autohome.com.cn/autov5.0.0/cars/seriesprice-pm1-b" + str + "-t1.json";
                //进去的时候直接出数据
                VolleyInstance.getInstance().startRequest(sellurl, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        popgroulists = new ArrayList<>();
                        popchilds = new HashMap<>();
                        BrandPopBean bean = gson.fromJson(result, BrandPopBean.class);
                        //父布局
                        data = bean.getResult().getFctlist();
                        for (int i = 0; i < data.size(); i++) {
                            String name = data.get(i).getName();
                            popgroulists.add(name);
                            popchilds.put(name, data.get(i).getSerieslist());
                        }
                        brandPopAdapter.setGroupList(popgroulists);
                        brandPopAdapter.setChildMaps(popchilds);
                        for (int i = 0, count = data.size(); i < count; i++) {
                            poplistView.expandGroup(i);
                        }

                    }

                    @Override
                    public void failure() {

                    }
                });

                createWindow();
            }
        });

        listView.addHeaderView(headView);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.fragment_brand_popwindow_sellBtn:
                VolleyInstance.getInstance().startRequest(sellurl, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        popgroulists = new ArrayList<>();
                        popchilds = new HashMap<>();
                        BrandPopBean bean = gson.fromJson(result, BrandPopBean.class);
                        //父布局
                        data = bean.getResult().getFctlist();
                        for (int i = 0; i < data.size(); i++) {
                            String name = data.get(i).getName();
                            popgroulists.add(name);
                            popchilds.put(name, data.get(i).getSerieslist());
                        }
                        brandPopAdapter.setGroupList(popgroulists);
                        brandPopAdapter.setChildMaps(popchilds);
                        for (int i = 0, count = data.size(); i < count; i++) {
                            poplistView.expandGroup(i);
                        }

                    }

                    @Override
                    public void failure() {

                    }
                });
                break;
            case R.id.fragment_brand_popwindow_allBtn:
                VolleyInstance.getInstance().startRequest(allUrl, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        popgroulists = new ArrayList<>();
                        popchilds = new HashMap<>();
                        BrandPopBean bean = gson.fromJson(result, BrandPopBean.class);
                        //父布局
                        data = bean.getResult().getFctlist();
                        for (int i = 0; i < data.size(); i++) {
                            String name = data.get(i).getName();
                            popgroulists.add(name);
                            popchilds.put(name, data.get(i).getSerieslist());
                        }
                        brandPopAdapter.setGroupList(popgroulists);
                        brandPopAdapter.setChildMaps(popchilds);
                        for (int i = 0, count = data.size(); i < count; i++) {
                            poplistView.expandGroup(i);
                        }
                    }

                    @Override
                    public void failure() {

                    }
                });
                break;
        }
        transaction.commit();
    }
}
