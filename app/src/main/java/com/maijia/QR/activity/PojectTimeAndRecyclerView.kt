package com.maijia.QR.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.maijia.QR.R
import com.maijia.QR.adapter.ProjectTimeAndRecyclerViewAdapter
import com.maijia.QR.bean.RecyclerViewProjectBean
import com.maijia.QR.utils.StringUtils
import kotlinx.android.synthetic.main.activity_poject_time_and_recycler_view.*


class PojectTimeAndRecyclerView : BaseActivity() {
    private var list: ArrayList<RecyclerViewProjectBean>? = null
    private var adapter: ProjectTimeAndRecyclerViewAdapter? = null
    var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poject_time_and_recycler_view)

        initView()
        StringUtils.setTextShowPic(this, R.mipmap.ic_jifen_store, "  "+resources.getString(R.string.meal_detail1), tv_ce)
        demoTest(3, 5)
    }

    private fun demoTest(i: Int, i1: Int) {
        showToastShort("取余：${i % i1}取商${i / i1}")
    }

    private fun initView() {
//24小时换算成毫秒  86400000
//        time.text = "时间:${TimeUtil.formatDateTime(100000L)}"
        var dateTime: String? = ""
        timer = object : CountDownTimer(93600000, 1000) {
            override fun onTick(l: Long) {
                val day = l / (1000 * 24 * 60 * 60); //单位天
                val hour1 = (l - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60); //单位时
                val hour = l / (1000 * 60 * 60); //单位时
                val minute = (l - day * (1000 * 24 * 60 * 60) - hour1 * (1000 * 60 * 60)) / (1000 * 60); //单位分
                val second = (l - day * (1000 * 24 * 60 * 60) - hour1 * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;//单位秒
                time.text = "${day}天${hour}时${minute}分${second}秒"

            }

            override fun onFinish() {

                showToastShort("倒计时完成")
            }

        }.start()


        list = arrayListOf(RecyclerViewProjectBean(R.mipmap.ic_launcher, "呵呵"), RecyclerViewProjectBean(R.mipmap.ic_launcher, "大头"), RecyclerViewProjectBean(R.mipmap.ic_launcher, "喵喵"), RecyclerViewProjectBean(R.mipmap.ic_launcher, "呵呵"), RecyclerViewProjectBean(0, "呵呵"))


        adapter = ProjectTimeAndRecyclerViewAdapter(list!!)
//        var layoutManager = LinearLayoutManager(this)
//        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = GridLayoutManager(this,5, GridLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onClick(v: View?) {

    }

    override fun onDestroy() {
        if (timer != null) {
            timer!!.cancel()
            timer = null

        }

        super.onDestroy()
    }
}
