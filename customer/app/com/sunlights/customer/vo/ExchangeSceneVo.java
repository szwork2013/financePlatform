package com.sunlights.customer.vo;

import java.io.Serializable;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public class ExchangeSceneVo implements Serializable {

    private String id;

    private String title;

    private String detail;

    private String logo;

    private String exchangeType;

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
