package com.sunlights.customer.service;

import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.models.Customer;
import com.sunlights.customer.models.CustomerSession;
import com.sunlights.customer.vo.CustomerFormVo;
import com.sunlights.customer.vo.CustomerVo;

/**
 * <p>Project: fsp</p>
 * <p>Title: LoginService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface LoginService {
    
    public Customer register(CustomerFormVo customerFormVo);

    public CustomerSession login(CustomerFormVo vo, String token, String clientAddress);

    public CustomerSession loginByGesture(CustomerFormVo vo, String token, String clientAddress);

    public void logout(String mobilePhoneNo, String deviceNo, String token);

    /**
     * 忘记密码验证码校对
     * @return
     */
    public boolean resetpwdCertify(CustomerFormVo vo);

    /**
     * 重置密码
     * @return
     */
    public Customer resetpwd(String mobilePhoneNo, String passWord, String deviceNo);

    /**
     * 保存手势密码
     * @return
     */
    public CustomerVo saveGesturePwd(CustomerFormVo vo);

    public void confirmPwd(String mobilePhoneNo, String password);

    public void saveLoginHistory(Customer customer, String deviceNo);
}
