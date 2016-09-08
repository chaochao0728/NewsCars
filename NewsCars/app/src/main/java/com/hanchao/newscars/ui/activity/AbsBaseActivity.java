package com.hanchao.newscars.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/9/8.
 */
public abstract class AbsBaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initDatas();
    }
    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void initDatas();
    /**
     * 简化findViewById
     */
    protected <T extends View> T byView(int resId){
        return (T)findViewById(resId);
    }



    /**
     * 跳转传值
     */
    protected void goTo(Context from,Class<? extends AbsBaseActivity> to){
        startActivity(new Intent(from,to));
    }
    /**
     * 跳转传值
     */
    protected void goTo(Context from,Class<? extends AbsBaseActivity> to,Bundle extra){
        Intent intent=new Intent();
        intent.putExtras(extra);
        startActivity(intent);
    }
}
