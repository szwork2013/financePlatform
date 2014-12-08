package com.sunlights.customer.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/3.
 */
public class ShareVo implements Serializable {

    private String title;

    private String shorturl;

    private String content;

    private String imageurl;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }
}
