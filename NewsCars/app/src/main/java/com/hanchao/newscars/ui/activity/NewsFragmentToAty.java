package com.hanchao.newscars.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.CollectBean;
import com.hanchao.newscars.mode.db.LiteOrmInstance;
import com.hanchao.newscars.mode.net.VolleyInstance;
import com.hanchao.newscars.mode.net.VolleyResult;

import java.util.List;

/**
 * 新闻详情
 */

public class NewsFragmentToAty extends AbsBaseActivity {
    private WebView webView;
    private ImageView collectIv;
    private List<CollectBean> bean;
    private ImageView imageView;
    private TextView turnIv;

    @Override
    protected int setLayout() {
        return R.layout.activity_news_fragment_to_aty;
    }

    @Override
    protected void initView() {
        webView = byView(R.id.activity_news_fragment_to_aty_vew_view);
        collectIv = byView(R.id.collect_radioBtn);
        imageView = byView(R.id.activity_news_fragment_to_aty_iv);
        turnIv = byView(R.id.activity_new_fragment_to_aty_tv);
    }

    @Override
    protected void initDatas() {
        final Intent intent = getIntent();
        final String Title = intent.getStringExtra("Title");
        final String Urls = intent.getStringExtra("Url");
        final String Id = intent.getStringExtra("newsId");
        final String Url = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n" +
                intent.getStringExtra("newsId") + "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";
        webView.loadUrl(Url);
        //跳转到浏览器
        //设置不设置到浏览器  在当前activity显示
        webView.setWebViewClient(new WebViewClient() {

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
        bean = LiteOrmInstance.getInstance().quryById(Id);
        if (bean.size() > 0) {
            collectIv.setSelected(true);
        } else {
            collectIv.setSelected(false);
        }

        collectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LiteOrmInstance.getInstance().quryById(Id).size() == 0) {
                    LiteOrmInstance.getInstance().insert(new CollectBean(Integer.valueOf(Id), Urls, Title));
                    collectIv.setSelected(true);
                    Toast.makeText(NewsFragmentToAty.this, "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    LiteOrmInstance.getInstance().deletedTitle(Id);
                    collectIv.setSelected(false);
                    Toast.makeText(NewsFragmentToAty.this, "取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //点击销毁二级界面
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        turnIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
