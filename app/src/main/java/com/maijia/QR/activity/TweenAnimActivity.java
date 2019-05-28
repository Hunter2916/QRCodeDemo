package com.maijia.QR.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.maijia.QR.R;

import java.util.Timer;
import java.util.TimerTask;

public class TweenAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_anim);
        initView();


    }

    @SuppressLint("ResourceType")
    private void initView() {
        Button button= (Button) findViewById(R.id.bt_anim);
        final ImageView flower = (ImageView) findViewById(R.id.flower);
        //加载第一份动画资源
        final Animation anim = AnimationUtils.loadAnimation(this, R.animator.anim);
        //设置动画结束后保留结束状态
        anim.setFillAfter(true);
        //加载第二份动画资源
        final Animation reverse = AnimationUtils.loadAnimation(this
                , R.animator.reverse);
        //设置动画结束后保留结束状态
        reverse.setFillAfter(true);
        Button bn = (Button) findViewById(R.id.bn);
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    flower.startAnimation(reverse);
                }
            }
        };
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flower.startAnimation(anim);
                //设置3.5秒启动第二动画
                new Timer().schedule(new TimerTask(){
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0x123);
                    }
                },3000);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TweenAnimActivity.this,CustomTweenAnimation.class));
            }
        });
    }
}
