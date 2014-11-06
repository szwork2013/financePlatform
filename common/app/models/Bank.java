package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: fsp</p>
 * <p>Title: Bank.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "C_BANK")
public class Bank extends IdEntity {
  @Column(length = 40, name = "BANK_CODE")
  private String bankCode;
  @Column(length = 50, name = "BANK_NAME")
  private String bankName;
  @Column(length = 50, name = "EN_NAME")
  private String enName;
  @Column(length = 1, name = "STATUS")
  private String status;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATETIME")
  private Date createdDatetime;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UPDATE_DATETIME")
  private Date updateDatetime;

  public Bank() {
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getCreatedDatetime() {
    return createdDatetime;
  }

  public void setCreatedDatetime(Date createdDatetime) {
    this.createdDatetime = createdDatetime;
  }

  public Date getUpdateDatetime() {
    return updateDatetime;
  }

  public void setUpdateDatetime(Date updateDatetime) {
    this.updateDatetime = updateDatetime;
  }
}
