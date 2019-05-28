package com.maijia.QR.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.maijia.QR.R
import kotlinx.android.synthetic.main.activity_chains_constraint_layout.*

/**
 * 约束布局的链chains就是相互牵引
 */
class ChainsConstraintLayoutActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chains_constraint_layout)
        initListener()

    }

    private fun initListener() {
        val str = "你好啊"
        et_a.setOnClickListener(this)
        et_a.setText(str)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.et_a -> {
                //如果前个页面设置了值则光标就在该长度后面
                et_a.setSelection(3)
            }
        }
    }

}
