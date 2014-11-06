package models;


import javax.persistence.*;
import java.util.Date;

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
public class ProductRecommend extends IdEntity {
  @Column(name = "RECOMMEND_TYPE")
  private String recommendType;//推荐类型
  @Column(name = "PRODUCT_CODE")
  private String productCode;//产品代码

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "BEGIN_DATE")
  private Date beginDate;//推荐起始日期

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "END_DATE")
  private Date endDate;//推荐结束日期

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "TEMP_STOP_DATE")
  private Date tempStopDate;//临时终止日期

  @Column(name = "PRIORITY_LEVEL")
  private int priorityLevel;//推荐优先级
  @Column(name = "RECOMMEND_FLAG")
  private String recommendFlag;//推荐标志
  @Column(name = "RECOMMEND_DESC")
  private String recommendDesc;//推荐说明


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
