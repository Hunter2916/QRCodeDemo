package com.maijia.QR.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * @author：created by zhaoliang
 * @date 2019/4/9 20:34
 * @desc：
 */
public class VerticalImageSpan extends ImageSpan {
    public VerticalImageSpan(Context context, Bitmap b, int verticalAlignment) {
        super(context, b, verticalAlignment);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text,int start, int end, float x,int top, int y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        canvas.save();


        int transY = bottom - b.getBounds().bottom;

        if (mVerticalAlignment == ALIGN_BOTTOM) {

        }if (mVerticalAlignment == ALIGN_BASELINE) {
            transY -= paint.getFontMetricsInt().descent;
        }else {
            Paint.FontMetricsInt fm = paint.getFontMetricsInt();
            transY = (y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2;
        }

        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}
