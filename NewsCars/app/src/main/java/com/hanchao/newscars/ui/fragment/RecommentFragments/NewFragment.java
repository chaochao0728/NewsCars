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
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.NewBean;
import com.hanchao.newscars.mode.bean.NewFragmentRoateBean;
import com.hanchao.newscars.ui.activity.NewFragmentToAty;
import com.hanchao.newscars.ui.adapter.NewFragmentAdapter;
import com.hanchao.newscars.ui.adapter.NewFragmentRotateAdapter;
import com.hanchao.newscars.ui.app.NewsCarsApp;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;

import java.net.URL;
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
    }

    @Override
    protected void initDatas() {
        adapter = new NewFragmentAdapter(context);
        listView.setAdapter(adapter);
        Bundle bundle = getArguments();
        String NewFragmentUrl = bundle.getString("URL");
        RequestQueue queue = Volley.newRequestQueue(NewsCarsApp.getContext());
        StringRequest sr = new StringRequest(NewFragmentUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("1111", response);
                Gson gson = new Gson();
                NewBean bean = gson.fromJson(response, NewBean.class);
                Log.d("1111", "bean:" + bean);
                List<NewBean.ResultBean.NewslistBean> datas = bean.getResult().getNewslist();
//                Log.d("1111", "datas:" + datas);
                adapter.setData(datas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(sr);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewBean.ResultBean.NewslistBean bean = (NewBean.ResultBean.NewslistBean) parent.getItemAtPosition(position);
                String title = bean.getTitle();
                Intent intent = new Intent(getContext(), NewFragmentToAty.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        //头布局
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
        data.add(new NewFragmentRoateBean("http://www3.autoimg.cn/newsdfs/g16/M09/4C/16/640x320_0_autohomecar__wKgH11ff_siAT39EAAb11cjL4MY067.jpg"));
        data.add(new NewFragmentRoateBean("http://www3.autoimg.cn/newsdfs/g6/M13/4C/41/640x320_0_autohomecar__wKjB0VffV46AVS3xAAr_7RZVkJo343.jpg"));
        data.add(new NewFragmentRoateBean("http://www2.autoimg.cn/newsdfs/g18/M01/48/51/640x320_0_autohomecar__wKgH2VfdXsOAENBxAAHlhJ5YO-I087.jpg"));
        data.add(new NewFragmentRoateBean("http://www2.autoimg.cn/newsdfs/g17/M13/45/3D/640x320_0_autohomecar__wKgH2FfeYnWAAOUJAALmvKVjQsI150.jpg"));
        data.add(new NewFragmentRoateBean("http://www3.autoimg.cn/newsdfs/g17/M09/47/9E/640x320_0_autohomecar__wKgH51feKYCAQ02oAAhFNzn7tVU182.jpg"));
        data.add(new NewFragmentRoateBean("http://www3.autoimg.cn/newsdfs/g17/M06/48/EB/640x320_0_autohomecar__wKgH51ffQBSASzHZAAfasiEN3g4753.jpg"));
    }

}
