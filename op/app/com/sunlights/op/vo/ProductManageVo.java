package com.sunlights.op.vo;

import com.sunlights.common.AppConst;
import com.sunlights.common.service.CommonService;
import models.ProductManage;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: ProductManageV.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ProductManageVo {

    private Long id;
    private String productCode;
    private String productName;
    private String productType;
    private String productTypeValue;
    private String productStatus;
    private String productStatusValue;
    private Date beginDate;
    private Date endDate;
    private Date tempStopDate;
    private Integer priorityLevel;
    private String recommendType;
    private String recommendFlag;
    private String recommendDesc;
    private String supplierCode;
    private String url;
    private String isGrab;
    private String isGrabDesc;
    private Date upBeginTime;
    private Date downEndTime;
    private String productDesc;
    private Integer initBuyedCount;
    private Integer oneMonthBuyedCount;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
    private String grabStatus;
    private String grabStatusDesc;

    private Date fundCreateTime;

    public ProductManageVo(Object[] row) {
        this.id = row[0] == null ? 0L : Long.valueOf(row[0].toString());
        this.productCode = row[1] == null ? null : row[1].toString();
        this.productName = row[2] == null ? null : row[2].toString();
        this.productType = row[3] == null ? null : row[3].toString();
        this.productStatus = row[4] == null ? null : row[4].toString();
        this.beginDate = row[5] == null ? null : new Date(Timestamp.valueOf(row[5].toString()).getTime());
        this.endDate = row[6] == null ? null : new Date(Timestamp.valueOf(row[6].toString()).getTime());
        this.tempStopDate = row[7] == null ? null : new Date(Timestamp.valueOf(row[7].toString()).getTime());
        this.priorityLevel = row[8] == null ? null : Integer.valueOf(row[8].toString());
        this.recommendType = row[9] == null ? null : row[9].toString();
        this.recommendFlag = row[10] == null ? null : row[10].toString();
        this.recommendDesc = row[11] == null ? null : row[11].toString();
        this.supplierCode = row[12] == null ? null : row[12].toString();
        this.url = row[13] == null ? null : row[13].toString();
        this.isGrab = row[14] == null ? null : row[14].toString();
        this.upBeginTime = row[15] == null ? null : new Date(Timestamp.valueOf(row[15].toString()).getTime());
        this.downEndTime = row[16] == null ? null : new Date(Timestamp.valueOf(row[16].toString()).getTime());
        this.productDesc = row[17] == null ? null : row[17].toString();
        this.initBuyedCount = row[18] == null ? null : Integer.valueOf(row[18].toString());
        this.oneMonthBuyedCount = row[19] == null ? null : Integer.valueOf(row[19].toString());
        this.createTime = row[20] == null ? null : new Date(Timestamp.valueOf(row[20].toString()).getTime());
        this.updateTime = row[21] == null ? null : new Date(Timestamp.valueOf(row[21].toString()).getTime());
        this.createBy = row[22] == null ? null : row[22].toString();
        this.updateBy = row[23] == null ? null : row[23].toString();
        this.fundCreateTime = row[24] == null ? null : new Date(Timestamp.valueOf(row[24].toString()).getTime());
        this.productStatusValue = new CommonService().findValueByCatPointKey(this.productStatus);
        this.productTypeValue = new CommonService().findValueByCatPointKey(this.productType);
        this.isGrabDesc = "Y".equals(this.isGrab) ? "是" : "否";
        this.grabStatus = getGrabStatusBy(this.fundCreateTime);
    }

    public ProductManageVo() {
        super();
    }

    public ProductManageVo(ProductManage productManage) {
        inProductManage(productManage);
    }

    public void inProductManage(ProductManage productManage) {
        this.setId(productManage.getId());
        this.setProductCode(productManage.getProductCode());
        this.setProductName(productManage.getProductName());
        this.setProductType(productManage.getProductType());
        this.setProductTypeValue(new CommonService().findValueByCatPointKey(productManage.getProductType()));
        this.setProductStatus(productManage.getProductStatus());
        this.setProductStatusValue(new CommonService().findValueByCatPointKey(productManage.getProductStatus()));
        this.setBeginDate(productManage.getBeginDate());
        this.setEndDate(productManage.getEndDate());
        this.setTempStopDate(productManage.getTempStopDate());
        this.setPriorityLevel(productManage.getPriorityLevel());
        this.setRecommendType(productManage.getRecommendType());
        this.setRecommendFlag(productManage.getRecommendFlag());
        this.setRecommendDesc(productManage.getRecommendDesc());
        this.setSupplierCode(productManage.getSupplierCode());
        this.setUrl(productManage.getUrl());
        this.setIsGrab(productManage.getIsGrab());
        this.setIsGrabDesc("Y".equals(productManage.getIsGrab()) ? "是" : "否");
        this.setUpBeginTime(productManage.getUpBeginTime());
        this.setDownEndTime(productManage.getDownEndTime());
        this.setProductDesc(productManage.getProductDesc());
        this.setInitBuyedCount(productManage.getInitBuyedCount());
        this.setOneMonthBuyedCount(productManage.getOneMonthBuyedCount());
        this.setCreateTime(productManage.getCreateTime());
        this.setUpdateTime(productManage.getUpdateTime());
        this.setCreateBy(productManage.getCreateBy());
        this.setUpdateBy(productManage.getUpdateBy());
    }


    public ProductManage convertToProductManage() {
        ProductManage productManage = new ProductManage();
        productManage.setId(this.getId());
        productManage.setProductCode(this.getProductCode());
        productManage.setProductName(this.getProductName());
        productManage.setProductType(this.getProductType());
        productManage.setProductStatus(this.getProductStatus());
        productManage.setBeginDate(this.getBeginDate());
        productManage.setEndDate(this.getEndDate());
        productManage.setTempStopDate(this.getTempStopDate());
        productManage.setPriorityLevel(this.getPriorityLevel());
        productManage.setRecommendType(this.getRecommendType());
        productManage.setRecommendFlag(this.getRecommendFlag());
        productManage.setRecommendDesc(this.getRecommendDesc());
        productManage.setSupplierCode(this.getSupplierCode());
        productManage.setUrl(this.getUrl());
        productManage.setIsGrab(this.getIsGrab());
        productManage.setUpBeginTime(this.getUpBeginTime());
        productManage.setDownEndTime(this.getDownEndTime());
        productManage.setProductDesc(this.getProductDesc());
        productManage.setInitBuyedCount(this.getInitBuyedCount());
        productManage.setOneMonthBuyedCount(this.getOneMonthBuyedCount());
        productManage.setCreateTime(this.getCreateTime());
        productManage.setUpdateTime(this.getUpdateTime());
        productManage.setCreateBy(this.getCreateBy());
        productManage.setUpdateBy(this.getUpdateBy());
        return productManage;
    }

    private String getGrabStatusBy(Date fundCreateTime) {
        if (fundCreateTime == null) {
            this.grabStatusDesc = "未同步";
            return AppConst.FUND_GRAB_STATUS_NULL;
        }
        if (DateUtils.isSameDay(new Date(), fundCreateTime)) {
            this.grabStatusDesc = "已同步";
            return AppConst.FUND_GRAB_STATUS_SYNCHRONIZED;
        } else {
            this.grabStatusDesc = "过期";
            return AppConst.FUND_GRAB_STATUS_OVERDUE;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
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

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsGrab() {
        return isGrab;
    }

    public void setIsGrab(String isGrab) {
        this.isGrab = isGrab;
    }

    public Date getUpBeginTime() {
        return upBeginTime;
    }

    public void setUpBeginTime(Date upBeginTime) {
        this.upBeginTime = upBeginTime;
    }

	    public void setUpBeginTimeString(Date upBeginTime) {
        this.upBeginTime = upBeginTime;
    }

    public Date getDownEndTime() {
        return downEndTime;
    }

    public void setDownEndTime(Date downEndTime) {
        this.downEndTime = downEndTime;
    }

    public void setDownEndTimeString(Date downEndTime) {
        this.downEndTime = downEndTime;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getInitBuyedCount() {
        return initBuyedCount;
    }

    public void setInitBuyedCount(Integer initBuyedCount) {
        this.initBuyedCount = initBuyedCount;
    }

    public Integer getOneMonthBuyedCount() {
        return oneMonthBuyedCount;
    }

    public void setOneMonthBuyedCount(Integer oneMonthBuyedCount) {
        this.oneMonthBuyedCount = oneMonthBuyedCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getProductTypeValue() {
        return productTypeValue;
    }

    public void setProductTypeValue(String productTypeValue) {
        this.productTypeValue = productTypeValue;
    }

    public String getProductStatusValue() {
        return productStatusValue;
    }

    public void setProductStatusValue(String productStatusValue) {
        this.productStatusValue = productStatusValue;
    }

    public String getIsGrabDesc() {
        return isGrabDesc;
    }

    public void setIsGrabDesc(String isGrabDesc) {
        this.isGrabDesc = isGrabDesc;
    }

    public Date getFundCreateTime() {
        return fundCreateTime;
    }

    public void setFundCreateTime(Date fundCreateTime) {
        this.fundCreateTime = fundCreateTime;
    }

    public String getGrabStatus() {
        return grabStatus;
    }

    public void setGrabStatus(String grabStatus) {
        this.grabStatus = grabStatus;
    }

    public String getGrabStatusDesc() {
        return grabStatusDesc;
    }

    public void setGrabStatusDesc(String grabStatusDesc) {
        this.grabStatusDesc = grabStatusDesc;
    }
}
