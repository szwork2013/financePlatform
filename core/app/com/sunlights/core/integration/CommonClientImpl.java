package com.sunlights.core.integration;

import com.sunlights.customer.facade.CustomerFacade;
import com.sunlights.customer.models.CustomerSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Project: fsp</p>
 * <p>Title: LoginClientImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Component
public class CommonClientImpl implements CommonClient {
    @Autowired
    private CustomerFacade customerFacade;

    @Override
    public String findCustomerIdByToken(String token) {
        CustomerSession customerSession = customerFacade.getCustomerSession(token);
        if (customerSession != null) {
            return customerSession.getCustomerId();
        }
        return null;
    }

    @Override
    public String findIdCardNoByToken(String token) {
        CustomerSession customerSession = customerFacade.getCustomerSession(token);
        if (customerSession != null) {
            return customerSession.getCustomerId();
        }
        return null;
    }
}
