package com.sunlights.customer.vo;

import models.ShareInfo;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public class ShareInfoVo extends ShareInfo {

    private String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
