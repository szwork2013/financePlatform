package com.sunlights.core.vo;

/**
 * <p>Project: fsp</p>
 * <p>Title: ProductParamter.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ProductParameter {
    private int index;
    private int pageSize;
    private String category;
    private String type;
    private String code;
    private String chartType;
    private String prdCode;
    private int interval;
    private int days;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public String getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getDays() {
        return days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductParameter)) return false;

        ProductParameter that = (ProductParameter) o;

        if (days != that.days) return false;
        if (index != that.index) return false;
        if (interval != that.interval) return false;
        if (pageSize != that.pageSize) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (chartType != null ? !chartType.equals(that.chartType) : that.chartType != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (prdCode != null ? !prdCode.equals(that.prdCode) : that.prdCode != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + pageSize;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (chartType != null ? chartType.hashCode() : 0);
        result = 31 * result + (prdCode != null ? prdCode.hashCode() : 0);
        result = 31 * result + interval;
        result = 31 * result + days;
        return result;
    }

    public void setDays(int days) {
        this.days = days;
    }

}
