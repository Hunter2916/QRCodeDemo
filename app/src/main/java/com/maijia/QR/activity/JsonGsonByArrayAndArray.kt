package com.maijia.QR.activity

import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.maijia.QR.R
import com.maijia.QR.bean.ThreeArrayBean

class JsonGsonByArrayAndArray : BaseActivity() {
    override fun onClick(v: View?) {

    }

    var str: String? = "{\n" +
            "    \"Data2\": [\n" +
            "        {\n" +
            "            \"Data\": [\n" +
            "                {\n" +
            "                    \"Id\": 1,\n" +
            "                    \"Title\": \"奖金池分红\",\n" +
            "                    \"Value\": 0\n" +
            "                }\n" +
            "            ],\n" +
            "            \"Title\": \"奖金池\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Data\": [\n" +
            "                {\n" +
            "                    \"Id\": 2,\n" +
            "                    \"Title\": \"销售额\",\n" +
            "                    \"Value\": 0\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Id\": 5,\n" +
            "                    \"Title\": \"推荐会员\",\n" +
            "                    \"Value\": \"0\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Id\": 3,\n" +
            "                    \"Title\": \"销售奖金\",\n" +
            "                    \"Value\": 0\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Id\": 4,\n" +
            "                    \"Title\": \"金豆奖励\",\n" +
            "                    \"Value\": 0\n" +
            "                }\n" +
            "            ],\n" +
            "            \"Title\": \"会员销售业绩\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Data\": [\n" +
            "                {\n" +
            "                    \"Id\": 6,\n" +
            "                    \"Title\": \"团队销售额\",\n" +
            "                    \"Value\": 0\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Id\": 7,\n" +
            "                    \"Title\": \"团队推荐会员\",\n" +
            "                    \"Value\": 0\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Id\": 8,\n" +
            "                    \"Title\": \"团队销售奖金\",\n" +
            "                    \"Value\": 0\n" +
            "                }\n" +
            "            ],\n" +
            "            \"Title\": \"团队销售业绩\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Data\": [\n" +
            "                {\n" +
            "                    \"Id\": 9,\n" +
            "                    \"Title\": \"落地销售额\",\n" +
            "                    \"Value\": 0\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Id\": 10,\n" +
            "                    \"Title\": \"落地奖金\",\n" +
            "                    \"Value\": 0\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Id\": 11,\n" +
            "                    \"Title\": \"市级（区县）团队推荐会员\",\n" +
            "                    \"Value\": \"0\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"Id\": 12,\n" +
            "                    \"Title\": \"商超采购销售额\",\n" +
            "                    \"Value\": 0\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_gson_by_array_and_array)
        initView()
    }

    private fun initView() {
        val jsonObject = JsonParser().parse(str).asJsonObject
        val list: ArrayList<ThreeArrayBean.Data2Bean.DataBean> = ArrayList()
        var bean1: ThreeArrayBean? = null
        bean1 = Gson().fromJson(jsonObject, ThreeArrayBean::class.java)

        for (i in 0 until bean1.data2.size) {
            for (k in 0 until bean1.data2[i].data.size) {
//                if(i==0){
//                    list.add(bean1.data2[i].data[k])
//
//                }
                when (i) {
                    0 -> {
                        list.add(bean1.data2[i].data[k])
                        bean1.data2[i].title
                    }
                    1 -> {
                        list.add(bean1.data2[i].data[k])
                    }
                    2 -> {
                        list.add(bean1.data2[i].data[k])
                    }
                    3 -> {
                        list.add(bean1.data2[i].data[k])
                    }
                }
            }
        }
        showToastShort(Gson().toJson(list))
    }
}
