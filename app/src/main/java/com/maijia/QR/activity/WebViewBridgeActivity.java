package com.maijia.QR.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.maijia.QR.R;
import com.maijia.QR.bean.UserInfo;
import com.maijia.QR.jsbridge.BridgeHandler;
import com.maijia.QR.jsbridge.BridgeWebView;
import com.maijia.QR.jsbridge.BridgeWebViewClient;
import com.maijia.QR.jsbridge.CallBackFunction;
import com.maijia.QR.jsbridge.DefaultHandler;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * @author：created by zhaoliang
 * @date 2019/4/12 20:03
 * @desc：
 */
public class WebViewBridgeActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private final String CODING = "UTF-8"; // 编码格式
    private String loadUrl = "file:///android_asset/demo.html";
    private BridgeWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_bridge);
        mWebView = (BridgeWebView) findViewById(R.id.web_view);
        //垂直滚动条不显示
//        webView.setVerticalScrollBarEnabled(false);
        IX5WebViewExtension x5WebViewExtension = mWebView.getX5WebViewExtension();
        if (x5WebViewExtension != null) {
            x5WebViewExtension.setScrollBarFadingEnabled(false);
        }
        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
        WebSettings settings = mWebView.getSettings();
        //		settings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
//		settings.setUseWideViewPort(true);
        settings.setUseWideViewPort(true);
        //设置运行加载js
//        settings.setJavaScriptEnabled(true);
        // 允许js弹出窗口
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置编码
        settings.setDefaultTextEncodingName(CODING);
        //设置支持DomStorage
        settings.setDomStorageEnabled(true);
        // 实现8倍缓存
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setAllowFileAccess(true);
        // 开启Application Cache功能
        settings.setAppCacheEnabled(true);
        //取得缓存路径
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
//        String chejusPath = getFilesDir().getAbsolutePath()+ APP_CACHE_DIRNAME;
        //设置路径
        //API 19 deprecated
        settings.setDatabasePath(appCachePath);
        // 设置Application caches缓存目录
        settings.setAppCachePath(appCachePath);
        //是否启用数据库
        settings.setDatabaseEnabled(true);
        //设置存储模式 建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT,无网络时，使用LOAD_CACHE_ELSE_NETWORK
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //设置不支持字体缩放
        settings.setSupportZoom(false);
        //设置对应的cookie具体设置有子类重写该方法来实现
        setCookie(loadUrl);
        //还有一种是加载https的URL时在5.0以上加载不了，5.0以下可以加载，这种情况可能是网页中存在非https得资源，在5.0以上是默认关闭，需要设置，
//		loadWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WebView.setWebContentsDebuggingEnabled(true);
//        }
// 设置具体WebViewClient
        mWebView.setWebViewClient(new MyWebViewClient(mWebView));
        // set HadlerCallBack
        mWebView.setDefaultHandler(new myHadlerCallBack());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }
        });
        try {
            mWebView.loadUrl(loadUrl);
            mWebView.setBackgroundColor(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //js调用Android方法  如果页面上面有多个的话，可以注册多个方法
        //submitFromWeb 要和js那边定义的一样就可以了
        mWebView.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Toast.makeText(WebViewBridgeActivity.this, data, Toast.LENGTH_LONG).show();
                //如果js那边调用后又 进行回调的话可以在这里进行回调的
                function.onCallBack("submitFromWeb-----------------");
            }
        });


        UserInfo user = new UserInfo();
        user.name = "SDU";
        user.pwd = "123456";
        //Android发送消息给js，也可以注册多个
        //functionInJs和js定义的要一致
        mWebView.callHandler("functionInJs", new Gson().toJson(user), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                //这里也是可以进行js回传的
            }
        });

        mWebView.send("hello");
    }

    /**
     * 自定义的WebViewClient
     */
    class MyWebViewClient extends BridgeWebViewClient {

        public MyWebViewClient(BridgeWebView webView) {
            super(webView);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);

        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

        }
    }


    /**
     * 自定义回调
     */
    class myHadlerCallBack extends DefaultHandler {

        @Override
        public void handler(String data, CallBackFunction function) {

        }
    }

    /**
     * 设置对应的cookie具体设置有子类重写该方法来实现
     * "age=20;sex=1;time=today"
     */
    protected void setCookie(String loadUrl) {
        UserInfo info = new UserInfo();
        info.name = "1111";
        info.pwd = "123456789";
        Gson gs = new Gson();
        String toJson = gs.toJson(info);
        synCookies(loadUrl, "user=" + toJson);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewParent parent = mWebView.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(mWebView);
        }
        mWebView.stopLoading();
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.clearHistory();
        mWebView.clearView();
        mWebView.removeAllViews();
        mWebView.destroy();
        mWebView = null;

    }

    /**
     * 设置Cookie
     *
     * @param url
     * @param cookie 格式：uid=21233 如需设置多个，需要多次调用
     *               synCookies(this, url, "age=20;sex=1;time=today");
     */
    protected void synCookies(String url, String cookie) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            CookieSyncManager.createInstance(this);
//        }
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, cookie);//cookies是在HttpClient中获得的cookie
//        CookieSyncManager.getInstance().sync();
    }
}
