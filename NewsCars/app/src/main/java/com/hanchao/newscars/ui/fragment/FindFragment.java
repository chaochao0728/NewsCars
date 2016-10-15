package com.hanchao.newscars.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.FindFragmentListBean;
import com.hanchao.newscars.mode.bean.NewFragmentRoateBean;
import com.hanchao.newscars.mode.bean.RoateBean;
import com.hanchao.newscars.mode.net.NetValues;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.activity.RoateActivity;
import com.hanchao.newscars.ui.adapter.FindFragmentLikeAdapter;
import com.hanchao.newscars.ui.adapter.FindFragmentListViewAdapter;
import com.hanchao.newscars.ui.adapter.FindFragmentRecommendAdapter;
import com.hanchao.newscars.ui.adapter.NewFragmentRotateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/8.
 * 发现的fragment
 */
public class FindFragment extends AbsBaseFragment {
    private ListView listView;
    private FindFragmentListViewAdapter adapter;
    private String listUrl = NetValues.FIND;
    //轮播图相关
    private ViewPager newFragmentvp;
    private LinearLayout pointLl;//轮播图状态改变的小圆点
    private List<NewFragmentRoateBean> data;
    private NewFragmentRotateAdapter vpadapter;
    private static final int TIME = 3000;
    private String oneTurnViewUrl, twoTurnViewUrl, threeTurnViewUrl, fourTurnViewUrl;
    private String rotateUrl = NetValues.FIND_ROTATE;
    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.find_fragment_list_view);
    }

    @Override
    protected void initDatas() {
        adapter = new FindFragmentListViewAdapter(context);
        listView.setAdapter(adapter);
        /**
         * 底下的listView
         */
        VolleyInstance.getInstance().startRequest(listUrl, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                FindFragmentListBean bean = gson.fromJson(result, FindFragmentListBean.class);
                List<FindFragmentListBean.ResultBean.GoodslistBean.ListBean> datas = bean.getResult().getGoodslist().getList();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
        //头布局
        View headView = LayoutInflater.from(context).inflate(R.layout.item_newfragment_head, null);
        newFragmentvp = (ViewPager) headView.findViewById(R.id.new_fragment_rotate_vp);
        pointLl = (LinearLayout) headView.findViewById(R.id.new_fragemnt_rotate_point_container);
        vpadapter = new NewFragmentRotateAdapter(data, getContext());
        newFragmentvp.setAdapter(vpadapter);
        /**
         *猜你喜欢的recycleView的头布局
         */
        View headLikeView = LayoutInflater.from(context).inflate(R.layout.item_find_like_head, null);
        RecyclerView recyclerView = (RecyclerView) headLikeView.findViewById(R.id.item_fragment_find_like_recycler_view);
        final FindFragmentLikeAdapter likeAdapter = new FindFragmentLikeAdapter(context);
        StaggeredGridLayoutManager likeManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(likeManager);
        recyclerView.setAdapter(likeAdapter);
        VolleyInstance.getInstance().startRequest(listUrl, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                FindFragmentListBean bean = gson.fromJson(result, FindFragmentListBean.class);
                List<FindFragmentListBean.ResultBean.ModulelistBean.ListBean> datas = bean.getResult().getModulelist().get(0).getList();
                likeAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
        /**
         * 为我推荐的头布局
         */
        View headRecommendView = LayoutInflater.from(context).inflate(R.layout.item_find_like_head, null);
        RecyclerView recyclerViews = (RecyclerView) headRecommendView.findViewById(R.id.item_fragment_find_like_recycler_view);
        TextView textView = (TextView) headRecommendView.findViewById(R.id.item_fragment_find_head_item_tv);
        textView.setText("为我推荐");
        StaggeredGridLayoutManager RecommendManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViews.setLayoutManager(RecommendManager);
        final FindFragmentRecommendAdapter recommendAdapter = new FindFragmentRecommendAdapter(context);
        recyclerViews.setAdapter(recommendAdapter);
        VolleyInstance.getInstance().startRequest(listUrl, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                FindFragmentListBean bean = gson.fromJson(result, FindFragmentListBean.class);
                List<FindFragmentListBean.ResultBean.ModulelistBean.ListBean> data = bean.getResult().getModulelist().get(1).getList();
                recommendAdapter.setData(data);
            }

            @Override
            public void failure() {

            }
        });

        listView.addHeaderView(headView);
        listView.addHeaderView(headLikeView);
        listView.addHeaderView(headRecommendView);
        //轮播图数据
        VolleyInstance.getInstance().startRequest(rotateUrl, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                RoateBean bean = gson.fromJson(result, RoateBean.class);
                List<RoateBean.ResultBean.ListBean> date = bean.getResult().getList();
                //轮播图跳转数据
                vpadapter.setData(date);
                //轮播图数据
                oneTurnViewUrl = date.get(0).getImgurl();
                twoTurnViewUrl = date.get(1).getImgurl();
                threeTurnViewUrl = date.get(2).getImgurl();
                fourTurnViewUrl = date.get(3).getImgurl();
//                fiveTurnViewUrl = date.get(4).getImgurl();
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
                newFragmentvp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), RoateActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void failure() {

            }
        });
    }


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
    private boolean isRotate = false;
    private Runnable rotateRunnable;

    /**
     * 开始轮播
     */
    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = newFragmentvp.getCurrentItem();
                newFragmentvp.setCurrentItem(++nowIndex);
                if (isRotate) {
                    handler.postDelayed(rotateRunnable, TIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable, TIME);
    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
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
//        data.add(new NewFragmentRoateBean(fiveTurnViewUrl));
    }
    //轮播结束
}
