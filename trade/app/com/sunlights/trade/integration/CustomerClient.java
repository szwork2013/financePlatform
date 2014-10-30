package com.sunlights.trade.integration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunlights.customer.facade.CustomerFacade;
import com.sunlights.customer.models.CustomerSession;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerClient.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Service("tpCustomerClient")
public class CustomerClient {
    @Autowired
    private CustomerFacade customerFacade;
    
    public CustomerSession getCustomerSession(String token){
        return customerFacade.getCustomerSession(token);
    }
}
