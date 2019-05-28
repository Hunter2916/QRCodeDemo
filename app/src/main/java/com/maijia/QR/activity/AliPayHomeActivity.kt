package com.maijia.QR.activity

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.maijia.QR.R
import kotlinx.android.synthetic.main.activity_ali_pay_home.*
import kotlinx.android.synthetic.main.include_default_layout.*
import kotlinx.android.synthetic.main.include_toolbar_close.*
import kotlinx.android.synthetic.main.include_toolbar_open.*

/**
 * 支付宝首页
 */
class AliPayHomeActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ali_pay_home)
        app_bar_layout.addOnOffsetChangedListener(this)
        initView()
    }

    private fun initView() {


    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {

        //垂直方向偏移量
        val offset = Math.abs(verticalOffset)
        //最大偏移距离
        val scrollRange = appBarLayout!!.totalScrollRange
        if (offset <= scrollRange / 2) {//当滑动没超过一半，展开状态下toolbar显示内容，根据收缩位置，改变透明值
            include_toolbar_open.setVisibility(View.VISIBLE)
            include_toolbar_close.setVisibility(View.GONE)
            //根据偏移百分比 计算透明值
            val scale2 = offset.toFloat() / (scrollRange / 2)
            val alpha2 = (255 * scale2).toInt()
            toolbar_open_bg_view.setBackgroundColor(Color.argb(alpha2, 25, 131, 209))
        } else {//当滑动超过一半，收缩状态下toolbar显示内容，根据收缩位置，改变透明值
            include_toolbar_open.setVisibility(View.GONE)
            include_toolbar_close.setVisibility(View.VISIBLE)
            val scale3 = (scrollRange - offset).toFloat() / (scrollRange / 2)
            val alpha3 = (255 * scale3).toInt()
            bg_toolbar_close.setBackgroundColor(Color.argb(alpha3, 25, 131, 209))
        }
        //根据偏移百分比计算扫一扫布局的透明度值
        val scale = offset.toFloat() / scrollRange
        val alpha = (255 * scale).toInt()
        content_bg_view.setBackgroundColor(Color.argb(alpha, 25, 131, 209))

    }

    override fun onDestroy() {
        super.onDestroy()
        app_bar_layout.removeOnOffsetChangedListener(this)
    }

}
