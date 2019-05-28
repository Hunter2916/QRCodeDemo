package com.maijia.QR.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.maijia.QR.R
import com.maijia.QR.adapter.MsgAdapter
import com.maijia.QR.bean.MsgBean
import kotlinx.android.synthetic.main.activity_test_xml.*

/**
 * 适合一次性请求数据
 */
class RecyclerViewUnfoldActivity : AppCompatActivity() {
    private var layoutManager: LinearLayoutManager? = null
    private var lists: List<MsgBean>? = null
    private var adapter: MsgAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_xml)
        initView()
    }

    private fun initView() {

        lists = arrayListOf(MsgBean("152648525", "这是一条信息", "来到公司的第一个任务就是: 一个列表页面")
                , MsgBean("152648525", "这是一条信息", "来到公司的第一个任务就是: 一个列表页面")
                , MsgBean("152648525", "这是一条信息", "来到公司的第一个任务就是: 一个列表页面")
                , MsgBean("152648525", "这是一条信息", "来到公司的第一个任务就是: 一个列表页面")
                , MsgBean("152648525", "这是一条信息", "来到公司的第一个任务就是: 一个列表页面")
                , MsgBean("152648525", "这是一条信息", "来到公司的第一个任务就是: 一个列表页面")) as List<MsgBean>?

        adapter = MsgAdapter(this)
        adapter!!.setLists(lists)
        layoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.adapter = adapter
    }
}
