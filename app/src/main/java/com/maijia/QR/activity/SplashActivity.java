package com.maijia.QR.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.chechezhi.ui.guide.AbsGuideActivity;
import com.chechezhi.ui.guide.SingleElement;
import com.chechezhi.ui.guide.SinglePage;
import com.maijia.QR.R;
import com.maijia.QR.fragment.EntryFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AbsGuideActivity {
    private List<SinglePage> guideContent;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//    }

    @Override
    public List<SinglePage> buildGuideContent() {
        setStatusBarColor();
        guideContent = new ArrayList<>();
        SinglePage page1 = new SinglePage();
        page1.mBackground = getResources().getDrawable(R.mipmap.ic_1);
        SingleElement singleElement1 = new SingleElement(200, 200, 400, 400, 0.0f, 1.0f, BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_stuff));
        SingleElement singleElement2 = new SingleElement(700, 800, 700, 100, 0.0f, 1.0f, BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_stuff));
        page1.mElementsList.add(singleElement1);
        page1.mElementsList.add(singleElement2);
        guideContent.add(page1);
//
//        SinglePage page2 = new SinglePage();
//        page2.mBackground = getResources().getDrawable(R.mipmap.ic_2);
//        SingleElement singleElement3 = new SingleElement(400, 400, -100, -100, 1.0f, 0.0f, BitmapFactory.decodeResource(
//                getResources(), R.mipmap.ic_stuff));
//        SingleElement singleElement4 = new SingleElement(700, 100, 700, -200, 1.0f, 0.0f, BitmapFactory.decodeResource(
//                getResources(), R.mipmap.ic_stuff));
//        page2.mElementsList.add(singleElement3);
//        page2.mElementsList.add(singleElement4);
//        guideContent.add(page2);

//        SinglePage page3 = new SinglePage();
//        page3.mBackground = getResources().getDrawable(R.mipmap.ic_3);
//        SingleElement singleElement5 = new SingleElement(200, 2000, 600, 140, 1.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.mipmap.ic_stuff));
//        SingleElement singleElement6 = new SingleElement(300, 2000, 900, 160, 1.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.mipmap.ic_stuff));
//        page3.mElementsList.add(singleElement5);
//        page3.mElementsList.add(singleElement6);
//        guideContent.add(page3);

        SinglePage page4 = new SinglePage();
        page4.mCustomFragment = new EntryFragment();
        guideContent.add(page4);

        return guideContent;
    }

    @Override
    public boolean drawDot() {
        return true;
    }

    @Override
    public Bitmap dotDefault() {
        return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_dot_default);
    }

    @Override
    public Bitmap dotSelected() {
        return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_dot_selected);
    }

    @Override
    public int getPagerId() {
        return R.id.guide_container;
    }

    /**
     * 设置沉浸式状态栏
     */
    public void setStatusBarColor() {
        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

//        // 自定义颜色
        if (this instanceof SplashActivity) {
            tintManager.setTintColor(getResources().getColor(R.color.transparent_bar));
        } else {
            tintManager.setTintResource(R.color.home);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
