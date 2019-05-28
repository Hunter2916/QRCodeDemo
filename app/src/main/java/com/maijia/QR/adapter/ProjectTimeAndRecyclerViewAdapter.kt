package com.maijia.QR.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.maijia.QR.R
import com.maijia.QR.bean.RecyclerViewProjectBean
import com.maijia.QR.utils.StringUtils

/**
 *@author：created by zhaoliang
 *@date 2019/3/31 10:10
 *@desc：
 *
 */
class ProjectTimeAndRecyclerViewAdapter(data: List<RecyclerViewProjectBean>) : BaseQuickAdapter<RecyclerViewProjectBean, BaseViewHolder>(R.layout.item_project_recycler_view, data) {
    override fun convert(helper: BaseViewHolder?, item: RecyclerViewProjectBean?) {
        helper!!.setText(R.id.title, item!!.title)
        var a: Int = 2//第二个之后显示其他图片
//        if (data.get(a)) {
//            helper.setImageResource(R.id.photo, item.photo)
//        } else {
//            helper.setImageResource(R.id.photo, R.mipmap.ic_me)
//        }
        Glide.with(mContext)
                .load(item.photo)
                .crossFade()
//                .placeholder(R.mipmap.maker)
                .error(R.mipmap.ic_me)
                .into(helper.getView(R.id.photo))




    }


}