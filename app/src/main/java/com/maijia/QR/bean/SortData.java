package com.maijia.QR.bean;

import java.io.Serializable;

public class SortData implements Serializable {
    private int pic;
    private String content;

    public SortData(int pic) {
        this.pic = pic;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
