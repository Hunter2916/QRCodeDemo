package com.maijia.QR.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.data.LineData;
import com.maijia.QR.R;
import com.maijia.QR.databinding.ActivitySecondBinding;
import com.maijia.QR.utils.LineChartManagerUtil;


public class SecondActivity extends BaseActivity {
    ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        initChart1();
    }

    private void initChart1() {
        //设置图表的描述
        binding.spreadLineChart.setDescription("价格趋势");
        //设置x轴的数据
        int numX = 4;
        //设置Y轴的数据
        float[] datas = { 0.005f, 0.008f, 0.015f, 0.025f, 0.045f};
        //设置折现名称
        LineChartManagerUtil.setLineName("当月值");
        LineChartManagerUtil.setLineName1("上月值");
        LineData lineData = LineChartManagerUtil.initSingleLineChart(this, binding.spreadLineChart, numX, datas);
        LineChartManagerUtil.initDataStyle(binding.spreadLineChart, lineData, this);

    }

    @Override
    public void onClick(View v) {

    }
}
