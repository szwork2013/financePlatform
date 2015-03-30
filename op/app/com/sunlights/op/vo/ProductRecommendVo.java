package com.sunlights.op.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import models.ProductRecommend;

import java.util.Date;

/**
 * <p>Project: op</p>
 * <p>Title: ProductRecommendVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:xiaobin.sheng@sunlights.cc">shenxiaobin</a>
 */
public class ProductRecommendVo {

    private Long id;
    private String recommendType;//推荐类型
    private String productName;//产品名称
    private String productCode;//产品代码
    private Date beginDate;//推荐起始日期
    private Date endDate;//推荐结束日期
    private Date tempStopDate;//临时终止日期
    private int priorityLevel;//推荐优先级
    private String recommendFlag;//推荐标志
    private String recommendDesc;//推荐说明

    public ProductRecommendVo() {
        super();
    }

    public ProductRecommendVo(ProductRecommend productRecommend) {
        inProductRecommendVo(productRecommend);
    }

    public void inProductRecommendVo(ProductRecommend productRecommend) {
        this.id = productRecommend.getId();
        this.recommendType = productRecommend.getRecommendType();
        this.productName = productRecommend.getProductName();
        this.productCode = productRecommend.getProductCode();
        this.beginDate = productRecommend.getBeginDate();
        this.endDate = productRecommend.getEndDate();
        this.tempStopDate = productRecommend.getTempStopDate();
        this.priorityLevel = productRecommend.getPriorityLevel();
        this.recommendFlag = productRecommend.getRecommendFlag();
        this.recommendDesc = productRecommend.getRecommendDesc();
        //this.url = productRecommend.getUrl();
    }

    @JsonIgnore
    public ProductRecommend getProductRecommend() {
        ProductRecommend productRecommend = new ProductRecommend();
        productRecommend.setId(this.id);
        productRecommend.setRecommendType(this.recommendType);
        productRecommend.setProductName(this.productName);
        productRecommend.setProductCode(this.productCode);
        productRecommend.setBeginDate(this.beginDate);
        productRecommend.setEndDate(this.endDate);
        productRecommend.setTempStopDate(this.tempStopDate);
        productRecommend.setPriorityLevel(this.priorityLevel);
        productRecommend.setRecommendFlag(this.recommendFlag);
        productRecommend.setRecommendDesc(this.recommendDesc);
        //productRecommend.setUrl(this.url);
        return productRecommend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getTempStopDate() {
        return tempStopDate;
    }

    public void setTempStopDate(Date tempStopDate) {
        this.tempStopDate = tempStopDate;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(String recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public String getRecommendDesc() {
        return recommendDesc;
    }

    public void setRecommendDesc(String recommendDesc) {
        this.recommendDesc = recommendDesc;
    }

}
