package com.maijia.QR.fragment


import android.animation.AnimatorInflater
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maijia.QR.R
import com.maijia.QR.activity.*
import kotlinx.android.synthetic.main.fragment_my.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class MyFragment : Fragment() {

    companion object {
        fun newInstance(): MyFragment {
            return MyFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        //添加myAnimationView组件
//        ll_container.addView(MyAnimationView())
        iv_bt.setOnClickListener {
            // 返回Java所支持的全部国家和语言的数组
            val localeList = Locale.getAvailableLocales()
            // 遍历数组的每个元素，依次获取所支持的国家和语言
            for (i in localeList.indices) {
                // 打印出所支持的国家和语言
                println(localeList[i].getDisplayCountry()
                        + "=" + localeList[i].getCountry()
                        + " " + localeList[i].getDisplayLanguage()
                        + "=" + localeList[i].getLanguage())
            }
        }
        bt_call.setOnClickListener {
            callPhone("12145684")
        }
        recycler.setOnClickListener {
            //悬浮吸顶效果
            startActivity(Intent(activity, HoverItemActivity::class.java))
//            startActivity(Intent(activity, RecyclerViewUnfoldActivity::class.java))
        }
        nestedScrollViewrecycler.setOnClickListener {
            startActivity(Intent(activity, NestedScrollviewActivity::class.java))

        }
        alipayScrollViewrecycler.setOnClickListener {//仿支付宝头部
            startActivity(Intent(activity, AliPayHomeActivity::class.java))
        }
        gsonArrayJson.setOnClickListener {
            //            startActivity(Intent(activity, GsonArrayJsonActivity::class.java))
            startActivity(Intent(activity, JsonGsonByArrayAndArray::class.java))
        }
//        TopSlidingChangeBig.setOnClickListener {
//            startActivity(Intent(activity, TopSlidingChangeBigActivity::class.java))
//        }
        click_show_tip.setOnClickListener {
            startActivity(Intent(activity, ExpandableListViewActivity::class.java))
        }
        time_recycle.setOnClickListener {
            startActivity(Intent(activity, PojectTimeAndRecyclerView::class.java))
//            startActivity(Intent(activity, WebViewBridgeActivity::class.java))
        }

    }

    inner class MyAnimationView : View(activity) {
        init {
            // 加载动画资源
            val colorAnim = AnimatorInflater
                    .loadAnimator(activity, R.animator.color_anim) as ObjectAnimator
            colorAnim.setEvaluator(ArgbEvaluator())
            // 对该View本身应用属性动画
            colorAnim.setTarget(this)
            // 开始指定动画
            colorAnim.start()
        }
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    fun callPhone(phoneNum: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        val data = Uri.parse("tel:$phoneNum")
        intent.data = data
        startActivity(intent)
    }
}