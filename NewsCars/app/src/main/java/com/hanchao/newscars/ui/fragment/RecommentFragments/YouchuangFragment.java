package com.hanchao.newscars.ui.fragment.RecommentFragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.NewFragmentRoateBean;
import com.hanchao.newscars.mode.bean.YouchuangBean;
import com.hanchao.newscars.mode.net.NetValues;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;
import com.hanchao.newscars.ui.adapter.NewFragmentRotateAdapter;
import com.hanchao.newscars.ui.adapter.YouchuangFfragmentAdapter;
import com.hanchao.newscars.ui.app.NewsCarsApp;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 优创fragment
 */
public class YouchuangFragment extends AbsBaseFragment {
    private static final int TIME = 3000;
    private ListView listView;
    private YouchuangFfragmentAdapter adapter;
    private ViewPager newFragmentvp;
    private NewFragmentRotateAdapter vpadapter;
    private LinearLayout pointLl;//轮播图状态改变的小圆点
    private List<NewFragmentRoateBean> data;

    public static YouchuangFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("URL", str);
        YouchuangFragment fragment = new YouchuangFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_youchuang;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.fragment_youchuan_listView);
    }

    @Override
    protected void initDatas() {
        adapter = new YouchuangFfragmentAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String YouchuangFragmentURL = bundle.getString("URL");
        VolleyInstance.getInstance().startRequest(YouchuangFragmentURL, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                YouchuangBean bean = gson.fromJson(result, YouchuangBean.class);
                List<YouchuangBean.ResultBean.NewslistBean> data = bean.getResult().getNewslist();
                adapter.setData(data);
            }

            @Override
            public void failure() {

            }
        });
        View headView = LayoutInflater.from(context).inflate(R.layout.item_newfragment_head, null);
        newFragmentvp = (ViewPager) headView.findViewById(R.id.new_fragment_rotate_vp);
        pointLl = (LinearLayout) headView.findViewById(R.id.new_fragemnt_rotate_point_container);
        builDatas();
        vpadapter = new NewFragmentRotateAdapter(data, getContext());
        newFragmentvp.setAdapter(vpadapter);
        vpadapter.setDatas(data);
        newFragmentvp.setCurrentItem(data.size() * 100);
        //开始轮播
        handler = new Handler();
        startRotate();
        //添加小圆点
        addPoints();
        //随着轮播改变小点
        changePoints();
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
        data.add(new NewFragmentRoateBean(NetValues.YOneroatUrl));
        data.add(new NewFragmentRoateBean(NetValues.YTworoatUrl));
        data.add(new NewFragmentRoateBean(NetValues.YThreeroatUrl));
        data.add(new NewFragmentRoateBean(NetValues.YForeroatUrl));
        data.add(new NewFragmentRoateBean(NetValues.YFiveroatUrl));
    }
}
