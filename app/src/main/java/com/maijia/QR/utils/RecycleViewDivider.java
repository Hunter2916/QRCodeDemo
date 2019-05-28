package com.maijia.QR.utils;

import android.content.Context;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

public class RecycleViewDivider extends Y_DividerItemDecoration {
    Y_Divider divider = null;
    public RecycleViewDivider(Context context) {
        super(context);
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        divider = new Y_DividerBuilder()
                .setBottomSideLine(true, 0xfff4f4f4, 1, 16, 0)
                .create();
        return divider;
    }
}
