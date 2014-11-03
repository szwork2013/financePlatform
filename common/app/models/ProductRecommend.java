package models;

import models.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <p>Project: fsp</p>
 * <p>Title: productRecommend.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "P_PRODUCT_RECOMMEND")
public class ProductRecommend extends BaseEntity {

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "RECOMMEND_TYPE")
    private String recommendType;//推荐类型
    @Column(name = "PRODUCT_CODE")
    private String productCode;//产品代码
    @Column(name = "BEGIN_DATE")
    private Timestamp beginDate;//推荐起始日期
    @Column(name = "END_DATE")
    private Timestamp endDate;//推荐结束日期
    @Column(name = "TEMP_STOP_DATE")
    private Timestamp tempStopDate;//临时终止日期
    @Column(name = "PRIORITY_LEVEL")
    private int priorityLevel;//推荐优先级
    @Column(name = "RECOMMEND_FLAG")
    private int recommendFlag;//推荐标志
    @Column(name = "RECOMMEND_DESC")
    private String recommendDesc;//推荐说明

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getTempStopDate() {
        return tempStopDate;
    }

    public void setTempStopDate(Timestamp tempStopDate) {
        this.tempStopDate = tempStopDate;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(int recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public String getRecommendDesc() {
        return recommendDesc;
    }

    public void setRecommendDesc(String recommendDesc) {
        this.recommendDesc = recommendDesc;
    }
}
