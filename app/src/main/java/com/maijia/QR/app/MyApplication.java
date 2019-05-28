package com.maijia.QR.app;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Environment;
import android.os.Vibrator;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.SDKInitializer;
import com.maijia.QR.service.LocationService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatformConfig;

/**
 * Created by zhaoliang on 2018/6/8 0008
 */

public class MyApplication extends Application {
    public LocationService locationService;
    public Vibrator mVibrator;

    public static String ImagePath;
    public static String VideoPath;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        /**
         * 极光分享
         */
        JShareInterface.setDebugMode(true);
        PlatformConfig platformConfig = new PlatformConfig()
                .setWechat("wx89d573a72ddc93a2", "197428c3f8b40244b540430d41b8057a")
                .setQQ("1106011004", "YIbPvONmBQBZUGaN");
//                .setSinaWeibo("374535501", "baccd12c166f1df96736b51ffbf600a2", "https://www.jiguang.cn");
//                .setFacebook("1847959632183996", "JShareDemo")
//                .setTwitter("eRJyErWUhRZVqBzADAbUnNWx5", "Oo7DJMiBwBHGFWglFrML1ULZCUDlH990RlJlQDdfepm3lToiMC");
        JShareInterface.init(this, platformConfig);
        JShareInterface.init(this);
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        //初始化SDK各组件
        SDKInitializer.initialize(getApplicationContext());

//        new Thread() {
//            @Override
//            public void run() {
//                File imageFile = copyResurces("jiguang_test_img.png", "test_img.png", 0);
//                File videoFile = copyResurces("jiguang_test.mp4", "jiguang_test.mp4", 0);
//                if (imageFile != null) {
//                    ImagePath = imageFile.getAbsolutePath();
//                }
//
//                if (videoFile != null) {
//                    VideoPath = videoFile.getAbsolutePath();
//                }
//
//                super.run();
//            }
//        }.start();
    }

    /**
     * 如果使用了MultiDex，建议通过Gradle的“multiDexKeepFile”配置等方式把Bugly的类放到主Dex，
     * 另外建议在Application类的"attachBaseContext"方法中主动加载非主dex
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    //已经是单例模式返回
    public static Context getContext() {
        return mContext;
    }
    private File copyResurces(String src, String dest, int flag) {
        File filesDir = null;
        try {
            if (flag == 0) {//copy to sdcard
                filesDir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/jiguang/" + dest);
                File parentDir = filesDir.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
            } else {//copy to data
                filesDir = new File(getFilesDir(), dest);
            }
            if (!filesDir.exists()) {
                filesDir.createNewFile();
                InputStream open = getAssets().open(src);
                FileOutputStream fileOutputStream = new FileOutputStream(filesDir);
                byte[] buffer = new byte[4 * 1024];
                int len = 0;
                while ((len = open.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                }
                open.close();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (flag == 0) {
                filesDir = copyResurces(src, dest, 1);
            }
        }
        return filesDir;
    }
}
