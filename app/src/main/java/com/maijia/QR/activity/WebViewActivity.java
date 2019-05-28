package com.maijia.QR.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.maijia.QR.R;
import com.maijia.QR.databinding.ActivityWebViewBinding;

import java.util.ArrayList;
import java.util.List;

public class WebViewActivity extends Activity {
    ActivityWebViewBinding binding;
    private List<String> listUrl = new ArrayList<>();
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        initView();
    }

    private void initView() {
        listUrl.add("https://www.baidu.com/?tn=62095104_7_oem_dg");
        listUrl.add("https://www.toutiao.com/");
        listUrl.add("http://coolmall.cc/");
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        WebSettings webSettings = binding.webView.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
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
        binding.webView.loadUrl(listUrl.get(position));
    }

}
