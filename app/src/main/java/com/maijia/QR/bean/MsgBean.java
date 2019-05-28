package com.maijia.QR.bean;

/**
 * @author：created by zhaoliang
 * @date 2019/3/5 09:41
 * @desc：
 */
public class MsgBean {
    public String created;//时间
    public String content;//内容
    public String contentMore;//展开更多

    public MsgBean(String created, String content, String contentMore) {
        this.created = created;
        this.content = content;
        this.contentMore = contentMore;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentMore() {
        return contentMore;
    }

    public void setContentMore(String contentMore) {
        this.contentMore = contentMore;
    }
}
