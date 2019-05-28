package com.maijia.QR.listener;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by zhaoliang on 2018/6/10 0010
 */

public class MyOrientationListener implements SensorEventListener {
    private SensorManager sensorManager;
    private Context context;
    private Sensor sensor;
    private float lastX;
    private OnOrientationListener mOnOrientationListener;

    public MyOrientationListener(Context context) {
        this.context = context;
    }

    /**
     * 开始监听
     */
    public void start() {
        sensorManager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            //得到方向传感器
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
        if (sensor != null) {
            assert sensorManager != null;
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    /**
     * 结束监听
     *
     * @param
     */
    public void stop() {
        //移除鉴定
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//方向发生变化
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float x = event.values[SensorManager.DATA_X];
            //应该是度数发生改变
            if (Math.abs(x - lastX) > 1.0) {
                if (mOnOrientationListener != null) {
                    mOnOrientationListener.onOrientationChanged(x);
                }
            }
        }
    }

    /**
     * 经度发生改变
     *
     * @param sensor
     * @param accuracy
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void setOnOrientationListener(
            OnOrientationListener mOnOrientationListener) {
        this.mOnOrientationListener = mOnOrientationListener;
    }

    /**
     * 定义接口供UI更新调用
     */
    public interface OnOrientationListener {
        void onOrientationChanged(float x);
    }
}
