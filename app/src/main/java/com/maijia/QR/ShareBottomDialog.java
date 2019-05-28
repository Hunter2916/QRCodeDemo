package com.maijia.QR;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import me.shaohui.bottomdialog.BaseBottomDialog;

public class ShareBottomDialog extends BaseBottomDialog implements View.OnClickListener {

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_layout;
    }

    @Override
    public void bindView(View view) {
        TextView a = (TextView) view.findViewById(R.id.A);
        TextView b = (TextView) view.findViewById(R.id.B);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        b.setText("哈哈");
    }

    @Override
    public float getDimAmount() {
        return 0.0f;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.A:
                Toast.makeText(getActivity(), "我是A", Toast.LENGTH_SHORT).show();
                break;
            case R.id.B:
                Toast.makeText(getActivity(), "我是B", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
