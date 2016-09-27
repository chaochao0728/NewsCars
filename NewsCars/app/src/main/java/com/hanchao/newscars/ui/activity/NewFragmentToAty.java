package com.hanchao.newscars.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.hanchao.newscars.R;

/**
 * Created by dllo on 16/9/13.
 * 最新的详情页
 */
public class NewFragmentToAty extends AbsBaseActivity {
    private WebView webView;

    @Override
    protected int setLayout() {
        return R.layout.aty_newfragment_to;
    }

    @Override
    protected void initView() {
        webView = byView(R.id.aty_newfragment_to_web_view);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String Url = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n" + intent.getStringExtra("title") + "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
        webView.loadUrl(Url);
        webView = (WebView) findViewById(R.id.aty_newfragment_to_web_view);
        //跳转到浏览器
        //设置不设置到浏览器  在当前activity显示
        webView.setWebViewClient(new WebViewClient(){

        });
        //设置WebView加载网页的属性
        //WebSettings
        WebSettings webSettings = webView.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(12);
    }
}
