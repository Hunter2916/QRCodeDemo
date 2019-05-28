package com.maijia.QR.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lxj.xpopup.XPopup
import com.maijia.QR.PageInfo
import com.maijia.QR.R
import kotlinx.android.synthetic.main.fragment_news.*


/**
 * A simple [Fragment] subclass.
 *
 */
class NewsFragment : Fragment() {
    var pageInfos: Array<PageInfo>? = null
    var titles: ArrayList<String>? = null
    var fragments: ArrayList<Fragment>? = null

    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    /**
     * kotlin必须使用onVieCreated()回调方法
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initListener()
        initView()
    }

    /**
     * 改变选中字体的大小
     */
    private fun initListener() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {//未选中
                val view = tab!!.customView
                if (view != null && view is TextView) {
                    view.setTextSize(16f)
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {//选中
                viewPager.setCurrentItem(tab!!.position)
                val view = tab.customView
                if (view != null && view is TextView) {
                    view.setTextSize(18f)
//                    tab.setCustomView(R.layout.custom_tab_layout_text)
                }
            }

        })
    }

    private fun initView() {
        titles = ArrayList()
        fragments = ArrayList()
        titles!!.add("快速开始")
        titles!!.add("快速你好")
        titles!!.add("快速太好")
        titles!!.add("快速介绍")


        fragments!!.add(AnimationFragment())
        fragments!!.add(HomeFragment())
        fragments!!.add(MyFragment())
        fragments!!.add(SortFragment())

        pageInfos = arrayOf<PageInfo>(PageInfo("快速开始", AnimationFragment()),
                PageInfo("局部阴影", HomeFragment()),
                PageInfo("局部阴影", HomeFragment()),
                PageInfo("图片浏览", MyFragment()),
                PageInfo("图片浏览", MyFragment()),
                PageInfo("图片浏览", MyFragment()),
                PageInfo("图片浏览", MyFragment()),
                PageInfo("尝试不同动画", SortFragment()),
                PageInfo("尝试不同动画", SortFragment()))
//        pageInfos = arrayOf<PageInfo>(PageInfo(titles, fragments), PageInfo(titles, fragments))
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {
//                callFragmentInit(i)
            }

            override fun onPageScrollStateChanged(i: Int) {

            }
        });
        viewPager.adapter = MainAdapter(activity!!.supportFragmentManager)
        viewPager.currentItem = 0

        viewPager.offscreenPageLimit = 0//数据加载几个页面
        tabLayout.setupWithViewPager(viewPager)

        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            if (tab != null) {
                tab.customView = getTabView(i)

            }
//            if (i == 0) {
//                tabLayout.addTab(TabLayout(activity).newTab().setCustomView(view), 0, true)
//            } else {
//                tabLayout.addTab(TabLayout(activity).newTab().setCustomView(view), i, false)
//            }

        }

        initListener()
//        viewPager.postDelayed({ callFragmentInit(0) }, 300)
        XPopup.setPrimaryColor(resources.getColor(R.color.colorPrimary))
    }

    /**
     * 自定义Tab的View
     * @param currentPosition
     * @return
     */
    private fun getTabView(i: Int): View {
        val view = LayoutInflater.from(activity).inflate(R.layout.custom_tab_layout_text, null)
        val text = view.findViewById<TextView>(R.id.text1)
//        text.text = pageInfos!!.get(i).title.get(i)
        text.text = pageInfos!!.get(i).title

            return view

    }


    //    private fun callFragmentInit(i: Int) {
//        val fragment = (viewPager.adapter as FragmentPagerAdapter).getItem(i) as BaseFragment
//        fragment.init(fragment.getView())
//    }
    internal inner class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(i: Int): Fragment {
            return pageInfos!![i].fragment
//            return pageInfos!!.get(i).fragments.get(i)
        }

        override fun getCount(): Int {
            return pageInfos!!.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return pageInfos!![position].title
//            return pageInfos!!.get(position).title.get(position)
        }
    }
}
