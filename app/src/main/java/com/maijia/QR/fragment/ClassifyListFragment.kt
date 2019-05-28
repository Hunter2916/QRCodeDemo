package com.maijia.QR.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maijia.QR.R
import kotlinx.android.synthetic.main.fragment_classify_list.*

/**
 * 底部分类数据
 */
class ClassifyListFragment : BaseFragment() {
    var currentIndex: Int = 0//当前是哪个

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun init(view: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_classify_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
//        getData()
    }

    private fun initView() {
        var id = arguments!!.getInt("state")
        tv.text = id.toString()

    }

    fun onReShow(currentIndex: Int) {
        this.currentIndex = currentIndex

    }

    companion object {
        fun newInstance(state: Int): ClassifyListFragment {
            val frag = ClassifyListFragment()
            val bundle = Bundle()
            bundle.putInt("id", state)//对应的编号
            frag.arguments = bundle
            return frag
        }
    }

}
