package com.sunlights.customer.dal;

import models.LoginHistory;

/**
 * <p>Project: fsp</p>
 * <p>Title: LoginDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface LoginDao {
    public LoginHistory saveLoginHistory(LoginHistory loginHistory);

    public LoginHistory updateLoginHistory(LoginHistory loginHistory);

    public LoginHistory findByPwd(String customerId);

    public LoginHistory findByGesturePwd(String customerId);

    public LoginHistory findByLoginCustomer(String customerId);
}
