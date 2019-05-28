package com.maijia.QR.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.maijia.QR.R
import com.maijia.QR.adapter.NestedScrollViewAdapter
import com.maijia.QR.bean.HomePageData
import com.maijia.QR.listener.OnBack1Listener
import kotlinx.android.synthetic.main.activity_nested_scrollview.*

class NestedScrollviewActivity : AppCompatActivity() {
    var adapter: NestedScrollViewAdapter? = null
    var homePageDataList: MutableList<HomePageData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scrollview)
        initView()
        initListener()
    }

    private fun initListener() {

        adapter!!.setOnBack1Listener(OnBack1Listener {
            Toast.makeText(this, "你点击的是：${it.toString()}", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this);
        homePageDataList = arrayListOf()
        homePageDataList!!.add(HomePageData(R.mipmap.ic_zx_1, "行业内部资讯", "", "2018-06-05 12:30:00", "管理员"))
        homePageDataList!!.add(HomePageData(R.mipmap.ic_zx_2, "行业内部资讯", "", "2018-06-09 12:30:00", "管理员"))
        homePageDataList!!.add(HomePageData(R.mipmap.ic_zx_3, "行业内部资讯", "", "2018-06-10 12:30:00", "管理员"))
        homePageDataList!!.add(HomePageData(R.mipmap.ic_zx_3, "行业内部资讯", "", "2018-06-11 12:30:00", "管理员"))
        homePageDataList!!.add(HomePageData(R.mipmap.ic_zx_1, "行业内部资讯", "", "2018-06-15 12:30:00", "管理员"))
        homePageDataList!!.add(HomePageData(R.mipmap.ic_zx_3, "行业内部资讯", "", "2018-06-17 12:30:00", "管理员"))
        homePageDataList!!.add(HomePageData(R.mipmap.ic_zx_2, "行业内部资讯", "", "2018-06-18 12:30:00", "管理员"))
        adapter = NestedScrollViewAdapter(homePageDataList, this)
        recyclerView.adapter = adapter


    }
}
