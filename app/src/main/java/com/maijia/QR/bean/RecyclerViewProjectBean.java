package com.maijia.QR.bean;

/**
 * @author：created by zhaoliang
 * @date 2019/3/5 09:41
 * @desc：
 */
public class RecyclerViewProjectBean {
    public int photo;//时间
    public String title;//内容

    public RecyclerViewProjectBean(int photo, String title) {
        this.photo = photo;
        this.title = title;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
