package com.sunlights.customer.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/3.
 */
public class ShareVo implements Serializable {

    private String description;

    private String shorturl;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }
}
