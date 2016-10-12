package com.hanchao.newscars.ui.fragment.forumfragments;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.AbsBaseFragment;
import com.hanchao.newscars.utils.ScreenSize;

/**
 * Created by dllo on 16/9/10.
 * 常用论坛的fragment
 */
public class CommonForumFragment extends AbsBaseFragment implements View.OnClickListener {
    private LinearLayout carforumTv, areaforumTv, topicforumTv;
    private LinearLayout rootView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_common_forum;
    }

    @Override
    protected void initView() {
        carforumTv = byView(R.id.fragment_common_four_carforumTv);
        areaforumTv = byView(R.id.fragment_common_four_areaforumTv);
        topicforumTv = byView(R.id.fragment_common_four_topicforumTv);
        rootView = byView(R.id.fragment_common_forum_footView);
    }

    @Override
    protected void initDatas() {
        carforumTv.setOnClickListener(this);
        areaforumTv.setOnClickListener(this);
        topicforumTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_common_four_carforumTv:
                createWindow();
                break;
            case R.id.fragment_common_four_areaforumTv:
                createWindow();
                break;
            case R.id.fragment_common_four_topicforumTv:
                createWindow();
                break;
        }
    }

    //自定义的方法创建popupwindow
    private void createWindow() {
        PopupWindow pw = new PopupWindow(getContext());
        int height = ScreenSize.getHight(getContext());
        pw.setHeight(height);
        pw.setWidth(400);
        View v = LayoutInflater.from(context).inflate(R.layout.fastreport_fragment_popwindow, null);
        pw.setContentView(v);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.showAtLocation(rootView, Gravity.RIGHT, 0, 0);
    }
}
