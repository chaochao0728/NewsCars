package com.hanchao.newscars.utils;

/**
 * Created by dllo on 16/9/28.
 */
public interface OnRefreshListener {
    /**
     * 下拉刷新
     */
    void onDownPullRefresh();
    /**
     * 上拉加载更多
     */
    void onLoadingMore();

}
