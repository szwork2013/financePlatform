package com.sunlights.customer.dal;

import models.Customer;
import models.CustomerGesture;
import models.CustomerSession;
import com.sunlights.customer.vo.CustomerInfoVo;

import java.sql.Timestamp;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerManageDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface CustomerDao {
    public String getCustomerIdSeq();

    public Customer getCustomerByMobile(String mobile);
    public Customer getCustomerByCustomerId(String customerId);
    public Customer saveCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);

    public CustomerInfoVo getCustomerInfoVoByPhoneNo(String mobilePhoneNo, String deviceNo);
    public CustomerInfoVo getCustomerInfoVoByIdCardNo(String idCardNo, String userName);

    public CustomerSession findCustomerSessionByToken(String token, Timestamp nMin);
    public CustomerSession findCustomerSessionByCustomer(String customerId, String deviceNo);
    public CustomerSession saveCustomerSession(CustomerSession customerSession);
    public CustomerSession updateCustomerSession(CustomerSession customerSession);

    public CustomerGesture saveCustomerGesture(CustomerGesture customerGesture);
    public CustomerGesture updateCustomerGesture(CustomerGesture customerGesture);
    public CustomerGesture findCustomerGestureByDeviceNo(String customerId, String deviceNo);




}
