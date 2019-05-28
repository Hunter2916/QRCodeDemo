package com.maijia.QR.fragment;


import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.maijia.QR.R;
import com.maijia.QR.activity.MainActivity;
import com.maijia.QR.databinding.FragmentEntryBinding;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntryFragment extends Fragment {
    FragmentEntryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setStatusBarColor();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_entry, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        binding.btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();//销毁自己
            }
        });
    }
    /**
     * 设置沉浸式状态栏
     */
    public void setStatusBarColor() {
        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

//        // 自定义颜色
        if (this instanceof EntryFragment) {
            tintManager.setTintColor(getResources().getColor(R.color.transparent_bar));
        } else {
            tintManager.setTintResource(R.color.home);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getActivity().getWindow();
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
