package com.hanchao.newscars.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hanchao.newscars.R;
import com.hanchao.newscars.ui.fragment.FindCarFragment;
import com.hanchao.newscars.ui.fragment.FindFragment;
import com.hanchao.newscars.ui.fragment.ForumFragment;
import com.hanchao.newscars.ui.fragment.MeFragmet;
import com.hanchao.newscars.ui.fragment.RecommendFragment;

public class MainActivity extends AbsBaseActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioButton recommentBtn,forumBtn,findCarBtn,findBtn,meBtn;
    private RadioGroup radioGroup;
    private FrameLayout replaceView;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        recommentBtn=byView(R.id.mainBtn_recommend);
        forumBtn=byView(R.id.mainBtn_forum);
        findCarBtn=byView(R.id.mainBtn_findCar);
        findBtn=byView(R.id.mainBtn_find);
        meBtn=byView(R.id.mainBtn_me);
        radioGroup=byView(R.id.main_radioGroup);
    }

    @Override
    protected void initDatas() {
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.mainBtn_recommend);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        switch (checkedId){
            case R.id.mainBtn_recommend:
                transaction.replace(R.id.main_replaceView,new RecommendFragment());
                break;
            case R.id.mainBtn_forum:
                transaction.replace(R.id.main_replaceView,new ForumFragment());
                break;
            case R.id.mainBtn_findCar:
                transaction.replace(R.id.main_replaceView,new FindCarFragment());
                break;
            case R.id.mainBtn_find:
                transaction.replace(R.id.main_replaceView,new FindFragment());
                break;
            case R.id.mainBtn_me:
                transaction.replace(R.id.main_replaceView,new MeFragmet());
                break;
        }
        transaction.commit();
    }
}
