package com.maijia.QR.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.maijia.QR.R
import com.maijia.QR.ScaleTransitionPagerTitleView
import com.maijia.QR.adapter.ExamplePagerAdapter
import kotlinx.android.synthetic.main.activity_top_sliding_change_big.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import java.util.*

class TopSlidingChangeBigActivity : AppCompatActivity() {
    private val CHANNELS = arrayOf("CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN", "KITKAT", "LOLLIPOP", "M", "NOUGAT")
    private val mDataList = Arrays.asList(*CHANNELS)
    val fragments = ArrayList<Fragment>()
//        private val mExamplePagerAdapter = ExamplePagerAdapter(mDataList)
    private val mExamplePagerAdapter = ExamplePagerAdapter(Arrays.asList(*arrayOf("大头", "二花")))
//    private val pagerAdapter=MainPagerAdapter(supportFragmentManager,fragments)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_sliding_change_big)
        initView()
    }

    private fun initView() {
        view_pager!!.setAdapter(mExamplePagerAdapter)
        initMagicIndicator5()

    }

    private fun initMagicIndicator5() {

        magic_indicator5.setBackgroundColor(Color.WHITE)
        val commonNavigator = CommonNavigator(this)
        commonNavigator.scrollPivotX = 0.8f
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return mDataList?.size ?: 0
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView = ScaleTransitionPagerTitleView(context)
                simplePagerTitleView.setText(mDataList[index])
                simplePagerTitleView.setTextSize(18f)
                simplePagerTitleView.setNormalColor(Color.parseColor("#616161"))
                simplePagerTitleView.setSelectedColor(Color.parseColor("#f57c00"))
                simplePagerTitleView.setOnClickListener(View.OnClickListener { view_pager.setCurrentItem(index) })
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.startInterpolator = AccelerateInterpolator()
                indicator.endInterpolator = DecelerateInterpolator(1.6f)
                indicator.yOffset = UIUtil.dip2px(context, 39.0).toFloat()
                indicator.lineHeight = UIUtil.dip2px(context, 1.0).toFloat()
                indicator.setColors(Color.parseColor("#f57c00"))
                return indicator
            }
        }
        magic_indicator5.navigator = commonNavigator
        ViewPagerHelper.bind(magic_indicator5, view_pager)
    }

}
