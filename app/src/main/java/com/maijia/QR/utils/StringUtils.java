package com.maijia.QR.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.TextView;

/**
 * @author：created by zhaoliang
 * @date 2019/4/9 20:04
 * @desc：
 */
public class StringUtils {
    public static void setTextShowPic(Context context, int pic, String str, TextView textView) {
        SpannableString spanText = new SpannableString("占" + str);
        Drawable d = context.getResources().getDrawable(pic);
        //第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
//        d.setBounds(dip2px(context,5),0,dip2px(context,45),dip2px(context,16));
//        ImageSpan imageSpan=new ImageSpan(d);
        Bitmap drawable = BitmapFactory.decodeResource(context.getResources(), pic);

//        spanText.setSpan(new ImageSpan(d, ImageSpan.ALIGN_BASELINE),0,1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText.setSpan(new VerticalImageSpan(context,drawable,-1000), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spanText);
    }

    /**
     * 根据手机分辨率从DP转成PX
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param pxValue
     * @return
     */

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
