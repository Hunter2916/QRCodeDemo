package com.maijia.QR.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.maijia.QR.R
import com.maijia.QR.adapter.SortAdapter
import com.maijia.QR.bean.SortData
import kotlinx.android.synthetic.main.fragment_sort.*
import kotlinx.android.synthetic.main.index_zone_layout.*


/**
 * 动态添加布局
 *
 */
class SortFragment : Fragment() {
    var textView: TextView? = null
    var list: List<SortData>? = null
    var list1: List<SortData>? = null
    var sortAdapter: SortAdapter? = null

    companion object {
        fun newInstance(): SortFragment {
            return SortFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_sort, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
//        recyclerView1.setHasFixedSize(true)
//        recyclerView1.layoutManager = GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?
//        list = listOf(SortData(R.mipmap.b1), SortData(R.mipmap.b2), SortData(R.mipmap.b3))
        list = listOf(SortData(R.mipmap.b1), SortData(R.mipmap.b2), SortData(R.mipmap.b3), SortData(R.mipmap.b1))
        if (list!!.size == 3) {
            recyclerView1.visibility=View.VISIBLE
            ll_zone.visibility=View.GONE
            sortAdapter = SortAdapter(activity, list)
            recyclerView1.adapter = sortAdapter

        }else{
            recyclerView1.visibility=View.GONE
            ll_zone.visibility=View.VISIBLE
            sortAdapter = SortAdapter(activity, ArrayList())
            recyclerView1.adapter = sortAdapter
        }


    }

}
