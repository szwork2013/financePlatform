package com.sunlights.customer.dal;

import com.sunlights.customer.vo.CustomerVo;
import models.Customer;
import models.CustomerGesture;
import models.CustomerSession;

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

    /**
     * app端查询组装返回数据
     *
     * @param mobilePhoneNo
     * @param deviceNo
     * @return
     */
    public CustomerVo getCustomerVoByPhoneNo(String mobilePhoneNo, String deviceNo);

    public CustomerVo getCustomerVoByIdCardNo(String idCardNo, String realName);

    /**
     * pc端查询组装返回数据
     *
     * @param mobile
     * @return
     */
    public CustomerVo getCustomerVoByAuthenticationMobile(String mobile);

    public CustomerSession findCustomerSessionByToken(String token, Timestamp nMin);

    public CustomerSession findCustomerSessionByCustomerId(String customerId, String deviceNo);

    public CustomerSession saveCustomerSession(CustomerSession customerSession);

    public CustomerSession updateCustomerSession(CustomerSession customerSession);

    public CustomerGesture saveCustomerGesture(CustomerGesture customerGesture);

    public CustomerGesture updateCustomerGesture(CustomerGesture customerGesture);

    public CustomerGesture findCustomerGestureByDeviceNo(String customerId, String deviceNo);

    /**
     * 通过被推荐人查询推荐人信息
     *
     * @param customerId 被推荐人
     * @return
     */
    public Customer findRecommenderInfo(String customerId);

    public boolean validateHasFirstPurchase(String customerId);
}
