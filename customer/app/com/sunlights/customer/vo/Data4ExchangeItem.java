package com.sunlights.customer.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/17.
 */
public class Data4ExchangeItem implements Serializable {

    private String title;

    private String detail;

    private String logo;

    private String createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
