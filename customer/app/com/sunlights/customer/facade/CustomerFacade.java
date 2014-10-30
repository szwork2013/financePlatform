package com.sunlights.customer.facade;

import com.sunlights.customer.vo.CustomerInfoVo;
import com.sunlights.customer.models.Customer;
import com.sunlights.customer.models.CustomerSession;
import play.mvc.Http;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerFacade.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface CustomerFacade {
    public Customer getCustomerByToken(String token);
    public Customer getCustomerByCustomerId(String customerId);
    public Customer getCustomerByMobile(String mobile);
    public Customer saveCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);

    public CustomerInfoVo getCustomerInfoVoByPhoneNo(String mobilePhoneNo, String deviceNo);
    public CustomerInfoVo getCustomerInfoVoByIdCardNo(String idCardNo, String userName);

    public void validateCustomerSession(Http.Request request, Http.Session session, Http.Response response);
    public void sessionLoginSessionId(Http.Session session, Http.Response response, CustomerSession customerSession);
    public void removeCache(String token);

    public CustomerSession createCustomerSession(Customer customer, String clientAddress);
    public CustomerSession getCustomerSession(String token);
}
