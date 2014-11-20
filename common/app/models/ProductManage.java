package models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: ProductManage.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
@javax.persistence.Table(name = "p_product_manage", schema = "public")
public class ProductManage extends IdEntity {

    @javax.persistence.Column(name = "product_code")
    private String productCode;

    @javax.persistence.Column(name = "product_name")
    private String productName;

    @javax.persistence.Column(name = "product_type")
    private String productType;

    @javax.persistence.Column(name = "product_status")
    private String productStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "begin_date")
    private Date beginDate;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "end_date")
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "temp_stop_date")
    private Date tempStopDate;

    @javax.persistence.Column(name = "priority_level")
    private Integer priorityLevel;

    @javax.persistence.Column(name = "recommend_type")
    private String recommendType;

    @javax.persistence.Column(name = "recommend_flag")
    private String recommendFlag;

    @javax.persistence.Column(name = "recommend_desc")
    private String recommendDesc;

    @javax.persistence.Column(name = "supplier_code")
    private String supplierCode;

    @javax.persistence.Column(name = "url")
    private String url;

    @javax.persistence.Column(name = "is_grab")
    private String isGrab;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "up_begin_time")
    private Date upBeginTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "down_end_time")
    private Date downEndTime;

    @javax.persistence.Column(name = "product_desc")
    private String productDesc;

    @javax.persistence.Column(name = "init_buyed_count")
    private Integer initBuyedCount;

    @javax.persistence.Column(name = "one_month_buyed_count")
    private Integer oneMonthBuyedCount;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "update_time")
    private Date updateTime;

    @javax.persistence.Column(name = "create_by")
    private String createBy;

    @javax.persistence.Column(name = "update_by")
    private String updateBy;

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

    public Date getDownEndTime() {
        return downEndTime;
    }

    public void setDownEndTime(Date downEndTime) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductManage that = (ProductManage) o;

        if (getId() != that.getId()) return false;
        if (beginDate != null ? !beginDate.equals(that.beginDate) : that.beginDate != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (downEndTime != null ? !downEndTime.equals(that.downEndTime) : that.downEndTime != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (initBuyedCount != null ? !initBuyedCount.equals(that.initBuyedCount) : that.initBuyedCount != null)
            return false;
        if (isGrab != null ? !isGrab.equals(that.isGrab) : that.isGrab != null) return false;
        if (oneMonthBuyedCount != null ? !oneMonthBuyedCount.equals(that.oneMonthBuyedCount) : that.oneMonthBuyedCount != null)
            return false;
        if (priorityLevel != null ? !priorityLevel.equals(that.priorityLevel) : that.priorityLevel != null)
            return false;
        if (productCode != null ? !productCode.equals(that.productCode) : that.productCode != null) return false;
        if (productDesc != null ? !productDesc.equals(that.productDesc) : that.productDesc != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productStatus != null ? !productStatus.equals(that.productStatus) : that.productStatus != null)
            return false;
        if (productType != null ? !productType.equals(that.productType) : that.productType != null) return false;
        if (recommendDesc != null ? !recommendDesc.equals(that.recommendDesc) : that.recommendDesc != null)
            return false;
        if (recommendFlag != null ? !recommendFlag.equals(that.recommendFlag) : that.recommendFlag != null)
            return false;
        if (recommendType != null ? !recommendType.equals(that.recommendType) : that.recommendType != null)
            return false;
        if (supplierCode != null ? !supplierCode.equals(that.supplierCode) : that.supplierCode != null) return false;
        if (tempStopDate != null ? !tempStopDate.equals(that.tempStopDate) : that.tempStopDate != null) return false;
        if (upBeginTime != null ? !upBeginTime.equals(that.upBeginTime) : that.upBeginTime != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() != null ? getId() ^ (getId() >>> 32) : 0);
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (productStatus != null ? productStatus.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (tempStopDate != null ? tempStopDate.hashCode() : 0);
        result = 31 * result + (priorityLevel != null ? priorityLevel.hashCode() : 0);
        result = 31 * result + (recommendType != null ? recommendType.hashCode() : 0);
        result = 31 * result + (recommendFlag != null ? recommendFlag.hashCode() : 0);
        result = 31 * result + (recommendDesc != null ? recommendDesc.hashCode() : 0);
        result = 31 * result + (supplierCode != null ? supplierCode.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (isGrab != null ? isGrab.hashCode() : 0);
        result = 31 * result + (upBeginTime != null ? upBeginTime.hashCode() : 0);
        result = 31 * result + (downEndTime != null ? downEndTime.hashCode() : 0);
        result = 31 * result + (productDesc != null ? productDesc.hashCode() : 0);
        result = 31 * result + (initBuyedCount != null ? initBuyedCount.hashCode() : 0);
        result = 31 * result + (oneMonthBuyedCount != null ? oneMonthBuyedCount.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }
}
