package com.maijia.QR.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.heima.tabview.library.TabView
import com.heima.tabview.library.TabViewChild
import com.maijia.QR.R
import com.maijia.QR.fragment.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

class Main2Activity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        destroyFragment()
        initVeiw()
//        initListener()
    }

//    private fun initListener() {
//        indexView.setOnClickListener(this)
//        goodsView.setOnClickListener(this)
//        shopcarView.setOnClickListener(this)
//        meView.setOnClickListener(this)
//
//    }

    private fun initVeiw() {

        val tabViewChildList = ArrayList<TabViewChild>()
        val tabViewChild01 = TabViewChild(R.drawable.tab01_sel, R.drawable.tab01_unsel, "首页", HomeFragment.newInstance("首页"))
        val tabViewChild02 = TabViewChild(R.drawable.tab02_sel, R.drawable.tab02_unsel, "分类", AnimationFragment.newInstance())
        val tabViewChild03 = TabViewChild(R.drawable.tab03_sel, R.drawable.tab03_unsel, "资讯", NewsFragment.newInstance())
        val tabViewChild04 = TabViewChild(R.drawable.tab04_sel, R.drawable.tab04_unsel, "购物车", SortFragment.newInstance())
        val tabViewChild05 = TabViewChild(R.drawable.tab05_sel, R.drawable.tab05_unsel, "我的", MyFragment.newInstance())
        tabViewChildList.add(tabViewChild01)
        tabViewChildList.add(tabViewChild02)
        tabViewChildList.add(tabViewChild03)
        tabViewChildList.add(tabViewChild04)
        tabViewChildList.add(tabViewChild05)
        //end add data
        tabView.setTabViewDefaultPosition(2)
        tabView.setTabViewChild(tabViewChildList, supportFragmentManager)
        tabView.setOnTabChildClickListener(TabView.OnTabChildClickListener { position, currentImageIcon, currentTextView ->
            // Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();
        })



    }

    override fun onClick(v: View?) {
        when (v!!.id) {

        }
    }

    private fun destroyFragment() {
        @SuppressLint("RestrictedApi")
        val fragmentList = supportFragmentManager.fragments
        if (fragmentList != null) {
            for (i in fragmentList.indices) {
                supportFragmentManager.beginTransaction().remove(fragmentList[i]).commitAllowingStateLoss()
            }
        }

    }
}
