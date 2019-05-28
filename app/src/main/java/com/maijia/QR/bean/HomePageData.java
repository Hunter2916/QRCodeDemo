package com.maijia.QR.bean;

import java.io.Serializable;

/**
 * Created by zhaoliang on 2018/7/9 0009
 */

public class HomePageData implements Serializable {
    //图片
    private int imageId;
    //标题
    private String title;
    //内容
    private String content;
    //时间
    private String time;
    //发布者
    private String publisher;

    public HomePageData(int imageId, String title, String content, String time, String publisher) {
        this.imageId = imageId;
        this.title = title;
        this.content = content;
        this.time = time;
        this.publisher = publisher;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
