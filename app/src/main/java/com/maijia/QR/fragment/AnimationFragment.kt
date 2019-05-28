package com.maijia.QR.fragment


import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maijia.QR.R
import kotlinx.android.synthetic.main.fragment_animation.*



/**
 * A simple [Fragment] subclass.
 *
 */
class AnimationFragment : Fragment() {
    //伴生单例对象
    companion object {
        fun newInstance(): AnimationFragment {
            return AnimationFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
//获取AnimationDrawable动画对象
        val anim = iv_anim.background as AnimationDrawable
        play.setOnClickListener {
            anim.start()
        }
        stop.setOnClickListener {
            anim.stop()
        }

    }
}
