package com.sunlights.customer.vo;

import models.Activity;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/3.
 */
public class ShareVo implements Serializable {

    private String template;

    private String shorturl;

    private String staticurl;

    public String getStaticurl() {
        return staticurl;
    }

    public void setStaticurl(String staticurl) {
        this.staticurl = staticurl;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }
}
