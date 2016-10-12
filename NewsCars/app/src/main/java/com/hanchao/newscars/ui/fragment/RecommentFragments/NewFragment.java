package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.NewBean;
import com.hanchao.newscars.mode.bean.NewFragmentRoateBean;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.activity.NewFragmentToAty;
import com.hanchao.newscars.ui.adapter.NewFragmentAdapter;
import com.hanchao.newscars.ui.adapter.NewFragmentRotateAdapter;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;
import com.hanchao.newscars.utils.OnRefreshListener;
import com.hanchao.newscars.view.ReFlashListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 最新的fragment
 */
public class NewFragment extends AbsBaseFragment {
    private static final int TIME = 3000;
    private ListView listView;
    private NewFragmentAdapter adapter;
    private ViewPager newFragmentvp;
    private LinearLayout pointLl;//轮播图状态改变的小圆点
    private List<NewFragmentRoateBean> data;
    private NewFragmentRotateAdapter vpadapter;
    private NewBean bean;
    private String oneTurnViewUrl, twoTurnViewUrl, threeTurnViewUrl, fourTurnViewUrl,
            fiveTurnViewUrl, sixTurnViewUrl;
    private ReFlashListView reFlashListView;
    //上啦刷新适配器
    private boolean flag = true;

    public static NewFragment newInstance(String string) {

        Bundle args = new Bundle();
        args.putString("URL", string);
        NewFragment fragment = new NewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_new;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.fragment_new_listView);
        reFlashListView = byView(R.id.fragment_new_listView);
    }

    @Override
    protected void initDatas() {
        adapter = new NewFragmentAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String NewFragmentUrl = bundle.getString("URL");
        VolleyInstance.getInstance().startRequest(NewFragmentUrl, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                bean = gson.fromJson(result, NewBean.class);
                List<NewBean.ResultBean.NewslistBean> datas = bean.getResult().getNewslist();
                //轮播图网址
                List<NewBean.ResultBean.FocusimgBean> dates = bean.getResult().getFocusimg();
                oneTurnViewUrl = dates.get(0).getImgurl();
//                Log.d("1111", oneTurnViewUrl);
                twoTurnViewUrl = dates.get(1).getImgurl();
                threeTurnViewUrl = dates.get(2).getImgurl();
                fourTurnViewUrl = dates.get(3).getImgurl();
                fiveTurnViewUrl = dates.get(4).getImgurl();
                sixTurnViewUrl = dates.get(5).getImgurl();
                adapter.setData(datas);
                /**
                 * 获取到数据之后就添加到轮播图中
                 */
                builDatas();
                vpadapter.setDatas(data);
                newFragmentvp.setCurrentItem(data.size() * 100);
                //开始轮播
                handler = new Handler();
                startRotate();
                //添加小圆点
                addPoints();
                //随着轮播改变小点
                changePoints();
            }

            @Override
            public void failure() {

            }
        });
        //下拉刷新
        reFlashListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onDownPullRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String Url = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l890560.json";
                        VolleyInstance.getInstance().startRequest(Url, new VolleyResult() {
                            @Override
                            public void success(String result) {
                                Gson gson = new Gson();
                                bean = gson.fromJson(result, NewBean.class);
                                List<NewBean.ResultBean.NewslistBean> datas = bean.getResult().getNewslist();
                                adapter.setData(datas);
//                                try {
//                                    Thread.sleep(2000);
                                reFlashListView.hideHeaderView();
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
                            }

                            @Override
                            public void failure() {

                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onLoadingMore() {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewBean.ResultBean.NewslistBean bean = (NewBean.ResultBean.NewslistBean) parent.getItemAtPosition(position);
                String Id = bean.getId() + "";
                Intent intent = new Intent(context, NewFragmentToAty.class);
                intent.putExtra("title", Id);
                startActivity(intent);
            }
        });
        //头布局 轮播图
        View headView = LayoutInflater.from(context).inflate(R.layout.item_newfragment_head, null);
        newFragmentvp = (ViewPager) headView.findViewById(R.id.new_fragment_rotate_vp);
        pointLl = (LinearLayout) headView.findViewById(R.id.new_fragemnt_rotate_point_container);
        //添加数据的原来在这 但是没有获取完毕就添加了所以是null的
        vpadapter = new NewFragmentRotateAdapter(data, getContext());
        newFragmentvp.setAdapter(vpadapter);
        listView.addHeaderView(headView);
    }

    /**
     * 改变小点的自定义方法
     */
    private void changePoints() {
        newFragmentvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isRotate) {
                    // 把所有小点设置为白色
                    for (int i = 0; i < data.size(); i++) {
                        ImageView pointIv = (ImageView) pointLl.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.point_white);
                    }
                    // 设置当前位置小点为灰色
                    ImageView iv = (ImageView) pointLl.getChildAt(position % data.size());
                    iv.setImageResource(R.mipmap.point_grey);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 添加小点的方法
     */
    private void addPoints() {
        // 有多少张图加载多少个小点
        for (int i = 0; i < data.size(); i++) {
            ImageView pointIv = new ImageView(getContext());
            pointIv.setPadding(5, 5, 5, 5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            pointIv.setLayoutParams(params);

            // 设置第0页小点的为灰色
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.point_grey);
            } else {
                pointIv.setImageResource(R.mipmap.point_white);
            }
            pointLl.addView(pointIv);
        }
    }

    private Handler handler;
    private boolean isRotate = true;
    private Runnable rotateRunnable;

    /**
     * 开始轮播
     */
    private void startRotate() {

        if (flag) {
            rotateRunnable = new Runnable() {
                @Override
                public void run() {
                    int nowIndex = newFragmentvp.getCurrentItem();
                    newFragmentvp.setCurrentItem(++nowIndex);
                    handler.postDelayed(rotateRunnable, TIME);
                }
            };
            handler.postDelayed(rotateRunnable, TIME);
            flag = false;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        //isRotate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRotate = false;
    }

    /**
     * 添加数据的自定义方法
     */
    private void builDatas() {
        data = new ArrayList<>();
        data.add(new NewFragmentRoateBean(oneTurnViewUrl));
        data.add(new NewFragmentRoateBean(twoTurnViewUrl));
        data.add(new NewFragmentRoateBean(threeTurnViewUrl));
        data.add(new NewFragmentRoateBean(fourTurnViewUrl));
        data.add(new NewFragmentRoateBean(fiveTurnViewUrl));
        data.add(new NewFragmentRoateBean(sixTurnViewUrl));
    }


}
