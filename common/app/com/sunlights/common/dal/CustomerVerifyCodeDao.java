package com.sunlights.common.dal;

import models.CustomerVerifyCode;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerVerifyCodeDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface CustomerVerifyCodeDao {
  public CustomerVerifyCode saveCustomerVerifyCode(CustomerVerifyCode customerVerifyCode);

  public CustomerVerifyCode updateCustomerVerifyCode(CustomerVerifyCode customerVerifyCode);

  public CustomerVerifyCode findVerifyCodeByType(String mobilePhoneNo, String verifyType);
}
