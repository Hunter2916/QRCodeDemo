package com.maijia.QR.activity

import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.maijia.QR.R
import com.maijia.QR.bean.GsonArrayBean1


class GsonArrayJsonActivity : BaseActivity() {
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var str: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson_array_json)
        initListener()
    }

    private fun initListener() {
        str = "{\n" +
                "    \"result\": [\n" +
                "        [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"title\": \"直属销售额\",\n" +
                "                \"value\": \"0\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 2,\n" +
                "                \"title\": \"直属销售奖金\",\n" +
                "                \"value\": \"0\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 3,\n" +
                "                \"title\": \"直属见习销售额\",\n" +
                "                \"value\": \"0\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 4,\n" +
                "                \"title\": \"直属见习销售奖金\",\n" +
                "                \"value\": \"0\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 5,\n" +
                "                \"title\": \"直接推荐会员\",\n" +
                "                \"value\": \"0\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 14,\n" +
                "                \"title\": \"直属见习销售排名\",\n" +
                "                \"value\": 0\n" +
                "            }\n" +
                "        ],\n" +
                "        [\n" +
                "            {\n" +
                "                \"id\": 9,\n" +
                "                \"title\": \"收货地址销售额\",\n" +
                "                \"value\": \"0\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 10,\n" +
                "                \"title\": \"收货地址奖金\",\n" +
                "                \"value\": \"0\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 11,\n" +
                "                \"title\": \"注册地见习销售额\",\n" +
                "                \"value\": \"0\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 12,\n" +
                "                \"title\": \"注册地见习销售奖金\",\n" +
                "                \"value\": \"0\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 13,\n" +
                "                \"title\": \"区域内注册会员数量\",\n" +
                "                \"value\": \"0\"\n" +
                "            }\n" +
                "        ]\n" +
                "    ]\n" +
                "}"
        //先转JsonObject
        val jsonObject = JsonParser().parse(str).asJsonObject
//        //再转JsonArray 加上数据头
        val jsonArray = jsonObject.getAsJsonArray("result")
        val list: ArrayList<GsonArrayBean1.ResultBean> = ArrayList()
        val list1: ArrayList<ArrayList<GsonArrayBean1.ResultBean>> = ArrayList()
        var bean1: GsonArrayBean1? = null
//        //循环遍历
//        var gson:String?=null

        bean1 = Gson().fromJson(jsonObject, GsonArrayBean1::class.java)


        for (index in 1..bean1.result.size) {
            when (index) {
                1 -> {
                    for (i in bean1.result[0]) {
                        list.add(i)
                    }
                }
                2 -> {
                    for (i in bean1.result[1]) {
                        list.add(i)
                    }
                }
                3 -> {
                    for (i in bean1.result[2]) {
                        list.add(i)
                    }
                }
            }
        }

        showToastShort("数据" + Gson().toJson(list))
    }


}

