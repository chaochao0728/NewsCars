package com.hanchao.newscars.mode.net;

/**
 * Created by dllo on 16/9/9.
 * 网络请求结果接口
 */
public interface VolleyResult {
    //接口方法
    void success(String result);

    void failure();
}
