package com.maijia.QR.bean;

import java.io.Serializable;

/**
 * @author：created by zhaoliang
 * @date 2019/3/9 16:09
 * @desc：
 */
public class GsonArrayBean implements Serializable {

    /**
     * id : 1
     * title : 直属销售额
     * value : 0
     */

    private int id;
    private String title;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
