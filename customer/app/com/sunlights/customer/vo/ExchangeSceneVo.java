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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExchangeSceneVo)) return false;

        ExchangeSceneVo that = (ExchangeSceneVo) o;

        if (detail != null ? !detail.equals(that.detail) : that.detail != null) return false;
        if (exchangeType != null ? !exchangeType.equals(that.exchangeType) : that.exchangeType != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (exchangeType != null ? exchangeType.hashCode() : 0);
        return result;
    }
}
