package com.sunlights.customer.biz.impl;


import com.sunlights.customer.facade.CustomerFacade;
import com.sunlights.customer.models.Customer;
import com.sunlights.customer.models.CustomerSession;
import com.sunlights.customer.vo.CustomerInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import play.mvc.Http;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerFacadeImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Component("CustomerFacade")
public class CustomerFacadeImpl implements CustomerFacade {
    @Autowired
    private CustomerService customerService;

    @Override
    public CustomerInfoVo getCustomerInfoVoByPhoneNo(String mobilePhoneNo, String deviceNo) {
        return customerService.getCustomerInfoVoByPhoneNo(mobilePhoneNo, deviceNo);
    }

    @Override
    public CustomerInfoVo getCustomerInfoVoByIdCardNo(String idCardNo, String userName) {
        return customerService.getCustomerInfoVoByIdCardNo(idCardNo, userName);
    }


    @Override
    public void validateCustomerSession(Http.Request request, Http.Session session, Http.Response response) {
        customerService.validateCustomerSession(request,session,response);
    }

    @Override
    public void sessionLoginSessionId(Http.Session session, Http.Response response, CustomerSession customerSession) {
        customerService.sessionLoginSessionId(session,response,customerSession);
    }

    @Override
    public Customer getCustomerByCustomerId(String customerId) {
        return customerService.getCustomerByCustomerId(customerId);
    }

    @Override
    public void removeCache(String token) {
        customerService.removeCache(token);
    }

    @Override
    public CustomerSession createCustomerSession(Customer customer, String clientAddress) {
        return customerService.createCustomerSession(customer, clientAddress);
    }

    @Override
    public CustomerSession getCustomerSession(String token) {
        return customerService.getCustomerSession(token);
    }


    @Override
    public Customer getCustomerByToken(String token) {
        return customerService.getCustomerByToken(token);
    }

    public Customer getCustomerByMobile(String mobile) {
        return customerService.getCustomerByMobile(mobile);
    }

    public Customer saveCustomer(Customer customer){
        return customerService.saveCustomer(customer);
    }
    public Customer updateCustomer(Customer customer){
        return customerService.updateCustomer(customer);
    }
}
