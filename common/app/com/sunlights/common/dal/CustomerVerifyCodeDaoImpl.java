package com.sunlights.common.dal;

import org.springframework.stereotype.Service;

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
@Service
public class CustomerVerifyCodeDaoImpl extends EntityBaseDao implements CustomerVerifyCodeDao{

    //CustomerVerifyCode
    @Override
    public CustomerVerifyCode saveCustomerVerifyCode(CustomerVerifyCode customerVerifyCode) {
        return create(customerVerifyCode);
    }

    @Override
    public CustomerVerifyCode updateCustomerVerifyCode(CustomerVerifyCode customerVerifyCode) {
        return update(customerVerifyCode);
    }

    public CustomerVerifyCode findVerifyCodeByType(String mobilePhoneNo, String verifyType){
        Query query = entityManager.createNativeQuery("select c.* FROM c_customer_verify_code c where c.mobile = ?0 and c.verify_type = ?1 and c.status = 'N' order by created_datetime desc", CustomerVerifyCode.class);
        query.setParameter(0, mobilePhoneNo);
        query.setParameter(1, verifyType);
        List<CustomerVerifyCode> list = query.getResultList();
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

}
