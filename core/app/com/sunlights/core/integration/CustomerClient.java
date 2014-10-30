package com.sunlights.core.integration;


import com.sunlights.customer.facade.CustomerFacade;
import com.sunlights.customer.vo.CustomerInfoVo;
import com.sunlights.customer.vo.CustomerVo;
import com.sunlights.customer.models.Customer;
import com.sunlights.customer.models.CustomerSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import play.mvc.Http;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerClient.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Service("bspCustomerClient")
public class CustomerClient {
    @Autowired
    private CustomerFacade customerFacade;

    public void validateCustomerSession(Http.Request request, Http.Session session, Http.Response response){
        customerFacade.validateCustomerSession(request,session,response);
    }
    public void sessionLoginSessionId(Http.Session session,Http.Response response, CustomerSession customerSession){
        customerFacade.sessionLoginSessionId(session,response,customerSession);
    }
    public void removeCache(String token){
        customerFacade.removeCache(token);
    }
    public CustomerSession createCustomerSession(Customer customer, String clientAddress){
        return customerFacade.createCustomerSession(customer, clientAddress);
    }
    public CustomerSession getCustomerSession(String token){
        return customerFacade.getCustomerSession(token);
    }



    public CustomerVo getCustomerVoByPhoneNo(String mobilePhoneNo, String deviceNo){
        CustomerInfoVo customerInfoVo = customerFacade.getCustomerInfoVoByPhoneNo(mobilePhoneNo, deviceNo);
        if (customerInfoVo != null) {
            return customerInfoVo.getCustomerVo();
        }
        return null;

    }

    public CustomerVo getCustomerVoByIdCardNo(String idCardNo, String userName){
        CustomerInfoVo customerInfoVo = customerFacade.getCustomerInfoVoByIdCardNo(idCardNo, userName);
        if (customerInfoVo != null) {
            return customerInfoVo.getCustomerVo();
        }
        return null;

    }
    public CustomerInfoVo getCustomerInfoVoByIdCardNo(String idCardNo, String userName){
        return customerFacade.getCustomerInfoVoByIdCardNo(idCardNo, userName);
    }




    public Customer getCustomerByToken(String token){
        return customerFacade.getCustomerByToken(token);
    }
    public Customer getCustomerByCustomerId(String customerId){
        return customerFacade.getCustomerByCustomerId(customerId);
    }
    public Customer getCustomerByMobile(String mobile){
        return customerFacade.getCustomerByMobile(mobile);
    }
    public Customer saveCustomer(Customer customer){
        return customerFacade.saveCustomer(customer);
    }
    public Customer updateCustomer(Customer customer){
        return customerFacade.updateCustomer(customer);
    }
}
