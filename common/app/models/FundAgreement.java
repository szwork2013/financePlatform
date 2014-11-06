package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: FundAgreement.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "f_fund_Agreement")
public class FundAgreement extends IdEntity {
  @Column(name = "customer_id", length = 30)
  private String customerId;
  @Column(name = "company_code", length = 20)
  private String companyCode;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_time")
  private Date createTime;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "update_time")
  private Date updateTime;

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
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
}
