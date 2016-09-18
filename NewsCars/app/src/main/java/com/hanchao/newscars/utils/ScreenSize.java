package com.hanchao.newscars.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.hanchao.newscars.ui.app.NewsCarsApp;

/**
 * Created by dllo on 16/9/13.
 * 屏幕尺寸工具类
 */
public class ScreenSize {
    public static int getHight(Context context) {
        //获取窗口管理者
        WindowManager manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        //创建显示尺寸类
        DisplayMetrics metrics = new DisplayMetrics();
        //将窗口的尺寸设置给显示尺寸类
        manager.getDefaultDisplay().getMetrics(metrics);
        //返回屏幕宽度
        return metrics.heightPixels;
    }

    public enum screenState {
        WIDTH, HEIGHT
    }

    public static int getScreenSize(screenState state) {
        //获取窗口管理者
        WindowManager manager = (WindowManager) NewsCarsApp.getContext().getSystemService(NewsCarsApp.getContext().WINDOW_SERVICE);
        //创建显示尺寸类
        DisplayMetrics metrics = new DisplayMetrics();
        //将窗口的尺寸设置给显示的尺寸类
        manager.getDefaultDisplay().getMetrics(metrics);
        if (state.equals(screenState.HEIGHT)) {
            return metrics.heightPixels;
        } else {
            return metrics.widthPixels;
        }
    }
}
