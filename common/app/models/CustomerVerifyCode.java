package models;

import com.sunlights.common.AppConst;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2014/9/4.
 */
@Entity
@Table(name = "C_CUSTOMER_VERIFY_CODE")
public class CustomerVerifyCode extends IdEntity {
  @Column(length = 40, name = "VERIFY_CODE")
  private String verifyCode; // 验证码
  @Column(length = 11, name = "MOBILE")
  private String mobile;//电话
  @Column(length = 40, name = "DEVICE_NO")
  private String deviceNo;//设备号
  @Column(length = 20, name = "VERIFY_TYPE")
  private String verifyType;//验证码类型
  @Column(name = "UPDATED_DATETIME")
  private Timestamp updatedDatetime;
  @Column(name = "CREATED_DATETIME")
  private Timestamp createdDatetime;
  @Column(name = "STATUS", length = 1)
  private String status = AppConst.VERIFY_CODE_STATUS_INVALID;

  public CustomerVerifyCode() {
  }

  public Timestamp getUpdatedDatetime() {
    return updatedDatetime;
  }

  public void setUpdatedDatetime(Timestamp updatedDatetime) {
    this.updatedDatetime = updatedDatetime;
  }

  public Timestamp getCreatedDatetime() {
    return createdDatetime;
  }

  public void setCreatedDatetime(Timestamp createdDatetime) {
    this.createdDatetime = createdDatetime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public String getVerifyType() {
    return verifyType;
  }

  public void setVerifyType(String verifyType) {
    this.verifyType = verifyType;
  }
}
