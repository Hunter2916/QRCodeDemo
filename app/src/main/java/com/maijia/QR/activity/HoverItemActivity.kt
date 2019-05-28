package com.maijia.QR.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.maijia.QR.R
import com.maijia.QR.adapter.HoverAdapter
import com.maijia.QR.bean.UserBean
import com.maijia.QR.utils.CharacterParser
import com.maijia.QR.utils.HoverItemDecoration
import com.maijia.QR.utils.PinyinComparator
import com.maijia.QR.view.IndexView
import kotlinx.android.synthetic.main.activity_hover_item.*
import java.util.*

class HoverItemActivity : BaseActivity() {
    override fun onClick(v: View?) {

    }

    var adapter: HoverAdapter? = null
    var userBeans: List<UserBean>? = null
    val names = arrayOf("阿妹", "毕哥", "长生", "大黑牛", "俄罗斯", "伏天", "哥哥", "何方", "姬无命", "柯南", "毛天花", "李四", "王五", "田鸡", "孙五", "张三")
    /**
     * 汉字转换成拼音的类
     */
    private var characterParser: CharacterParser? = null
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private var pinyinComparator: PinyinComparator? = null

    private var layoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hover_item)
        initView()
    }

    private fun initView() {
        characterParser = CharacterParser.getInstance()
        pinyinComparator = PinyinComparator()


        userBeans = filledData(getData())
        layoutManager = LinearLayoutManager(this)
        recycler_view.setLayoutManager(layoutManager)
        //一行代码实现吸顶悬浮的效果
        recycler_view.addItemDecoration(HoverItemDecoration(this, object : HoverItemDecoration.BindItemTextCallback {
            override fun getItemText(position: Int): String {
                //悬浮的信息
                return userBeans!![position].sortLetters
            }
        }))

        adapter = HoverAdapter(userBeans)

        recycler_view.setAdapter(adapter)

        initIndexView()
    }

    private fun initIndexView() {
        index_view.setShowTextDialog(show_text_dialog)
        index_view.setOnTouchingLetterChangedListener(object : IndexView.OnTouchingLetterChangedListener {
            override fun onTouchingLetterChanged(letter: String) {
                // 该字母首次出现的位置
                val position = getPositionForSection(letter)
                if (position != -1) {
                    layoutManager!!.scrollToPositionWithOffset(position, 0)
                    layoutManager!!.setStackFromEnd(false)
                }
            }
        })

    }

    /**
     * 获取字母首次出现的位置
     *
     * @param section
     * @return
     */
    fun getPositionForSection(section: String): Int {
        for (i in userBeans!!.indices) {
            val sortStr = userBeans!!.get(i).sortLetters
            if (sortStr == section) {
                return i
            }
        }
        return -1
    }

    private fun getData(): List<UserBean> {
        val userBeans = ArrayList<UserBean>()
        for (i in 0..100) {
            val userBean = UserBean()
            userBean.userName = names[i % 16]
            userBeans.add(userBean)
        }

        return userBeans
    }

    private fun filledData(sortList: List<UserBean>): List<UserBean> {

        for (i in sortList.indices) {

            if ("" == sortList[i].userName) {
                sortList[i].sortLetters = "#"
            } else {
                // 汉字转换成拼音
                val pinyin = characterParser!!.getSelling(sortList[i].userName)
                val sortString = pinyin.substring(0, 1).toUpperCase()

                // 正则表达式，判断首字母是否是英文字母
                if (sortString.matches("[A-Z]".toRegex())) {
                    sortList[i].sortLetters = sortString.toUpperCase()
                } else {
                    sortList[i].sortLetters = "#"
                }
            }

        }

        // 根据a-z进行排序源数据
        Collections.sort(sortList, pinyinComparator)

        return sortList
    }
}
