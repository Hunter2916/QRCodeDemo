package com.maijia.QR.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.maijia.QR.R;
import com.maijia.QR.view.MyAnimation;
import com.maijia.QR.view.MyYAnimation1;

/**
 * 自定义的补间动画
 * 使用animation作为补间动画的基类，并且提供四个实现类
 * alphaanimation   rotateanimation  scaleanimation  translateanimation
 * 透明度            选择             缩放             位移
 */
public class CustomTweenAnimation extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tween_animation);
        imageView = (ImageView) findViewById(R.id.iv_tween);
        initView();
//        initView1();
    }

    /**
     * 沿着Y轴 旋转
     */
    private void initView1() {
        MyYAnimation1 myYAnimation = new MyYAnimation1();
        myYAnimation.setRepeatCount(Animation.INFINITE); //旋转的次数（无数次）
        imageView.startAnimation(myYAnimation);
    }

    /**
     * 沿着想X Y轴翻转
     */
    private void initView() {

        WindowManager windowManager = (WindowManager)
                getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrice = new DisplayMetrics();
        // 获取屏幕的宽和高
        display.getMetrics(metrice);
//        MyAnimation myAnimation = new MyAnimation(metrice.xdpi / 2
//                , metrice.ydpi / 2, 5000);
        MyAnimation myAnimation = new MyAnimation(0
                , 0, 5000);
        myAnimation.setRepeatCount(Animation.INFINITE);
        imageView.setAnimation(myAnimation);
    }
}
