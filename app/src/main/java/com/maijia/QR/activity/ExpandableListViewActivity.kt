package com.maijia.QR.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.maijia.QR.R
import com.maijia.QR.adapter.ExpandableListAdapter
import kotlinx.android.synthetic.main.activity_expandable_list_view.*

class ExpandableListViewActivity : AppCompatActivity() {
    var adapter: ExpandableListAdapter? = null
    private val armTypes = arrayOf("神族", "虫族", "人族")
    private val arms = arrayOf(arrayOf("狂战士", "龙骑士", "黑暗圣堂"), arrayOf("小狗", "飞龙", "自爆妃子"), arrayOf("步兵", "伞兵", "护士mm"))
    var isGroupWhite = false
    var selPos = -1
    var expandPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable_list_view)
        adapter = ExpandableListAdapter(this, armTypes, arms)
        ecpandable.setGroupIndicator(null)
        ecpandable.setAdapter(adapter)
        initView()
    }

    private fun initView() {

        ecpandable.setOnGroupClickListener { _, _, groupPosition, _ ->
            //            mPresenter!!.getAgentAnalysis(groupList[groupPosition].id)

            isGroupWhite = ecpandable.isGroupExpanded(groupPosition)
            selPos = groupPosition
            false
        }
//打开点击的子布局 其他都关闭
        ecpandable.setOnGroupExpandListener {
            expandPosition = it
//            expand.isGroupExpanded(it)
            for (i in 0..armTypes.size) {
                if (i != it) {
                    ecpandable.collapseGroup(i)
                }
            }
        }
        adapter!!.setSelPos(expandPosition, isGroupWhite)
        adapter!!.notifyDataSetInvalidated()
    }

}

