package com.maijia.QR.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.maijia.QR.R;
import com.maijia.QR.databinding.ActivityThreeBinding;

public class ThreeActivity extends BaseActivity {
    ActivityThreeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_three);
        initClick();
    }

    private void initClick() {

    }

    @Override
    public void onClick(View v) {

    }
}
