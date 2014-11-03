package com.sunlights.core.models;


import com.sunlights.common.models.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <p>Project: fsp</p>
 * <p>Title: ProductManage.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
@Table(name = "P_PRODUCT_MANAGE")
public class ProductManage extends IdEntity {
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "BEGIN_TIME")
    private Timestamp beginTime;
    @Column(name = "END_DATE")
    private Timestamp endDate;
    @Column(name = "CREATE_TIME")
    private Timestamp createTime;
    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;
    @Column(name = "PRODUCT_DESC")
    private String productDesc;
    @Column(name = "PRODUCT_STATUS")
    private String productStatus;
    @Column(name = "URL")
    private String url;
    @Column(name = "PRODUCT_CODE")
    private String productCode;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
