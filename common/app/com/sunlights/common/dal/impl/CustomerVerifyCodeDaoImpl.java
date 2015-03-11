package com.sunlights.common.dal.impl;

import com.sunlights.common.dal.CustomerVerifyCodeDao;
import com.sunlights.common.dal.EntityBaseDao;
import models.CustomerVerifyCode;

import javax.persistence.Query;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerVerifyCodeDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class CustomerVerifyCodeDaoImpl extends EntityBaseDao implements CustomerVerifyCodeDao {

    @Override
    public CustomerVerifyCode saveCustomerVerifyCode(CustomerVerifyCode customerVerifyCode) {
        return create(customerVerifyCode);
    }

    @Override
    public CustomerVerifyCode updateCustomerVerifyCode(CustomerVerifyCode customerVerifyCode) {
        return update(customerVerifyCode);
    }

    public CustomerVerifyCode findVerifyCodeByType(String mobilePhoneNo, String verifyType) {
        Query query = createNameQuery("findVerifyCodeByType", mobilePhoneNo, verifyType);
        List<CustomerVerifyCode> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
