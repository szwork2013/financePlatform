package com.sunlights.common.vo;

import java.io.Serializable;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerVerifyCodeVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class CustomerVerifyCodeVo implements Serializable {
  private String mobile;
  private String verifyType;
  private String verifyCode;
  private String deviceNo;

  public CustomerVerifyCodeVo() {
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getVerifyType() {
    return verifyType;
  }

  public void setVerifyType(String verifyType) {
    this.verifyType = verifyType;
  }

  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }
}
