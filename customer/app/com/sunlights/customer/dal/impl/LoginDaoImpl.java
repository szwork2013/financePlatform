package com.sunlights.customer.dal.impl;

import com.sunlights.customer.dal.LoginDao;
import com.sunlights.customer.models.LoginHistory;
import com.sunlights.common.dal.EntityBaseDao;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: LoginDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Service
public class LoginDaoImpl extends EntityBaseDao implements LoginDao {
    @Override
    public LoginHistory saveLoginHistory(LoginHistory loginHistory) {
        return create(loginHistory);
    }

    @Override
    public LoginHistory updateLoginHistory(LoginHistory loginHistory) {
        return update(loginHistory);
    }

    public LoginHistory findByCustomerPwdInd(String customerId, String deviceNo){
        Query query = entityManager.createNativeQuery("select c.* FROM login_history c where c.customer_id = ?0 " +
                "and c.device_no = ?1 and c.pwd_ind = 'Y'" +
                "order by created_datetime desc", LoginHistory.class);
        query.setParameter(0, customerId);
        query.setParameter(1, deviceNo);
        List<LoginHistory> list = query.getResultList();
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }
    public LoginHistory findByCustomerQusPwdInd(String customerId, String deviceNo){
        Query query = entityManager.createNativeQuery("select c.* FROM login_history c where c.customer_id = ?0 " +
                "and c.device_no = ?1 and c.gesture_Ind = 'Y'" +
                "order by created_datetime desc", LoginHistory.class);
        query.setParameter(0, customerId);
        query.setParameter(1, deviceNo);
        List<LoginHistory> list = query.getResultList();
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public LoginHistory findByLoginCustomer(String customerId, String deviceNo){
        Query query = entityManager.createNativeQuery("select c.* FROM login_history c where c.customer_id = ?0 " +
                "and c.device_no = ?1 and c.success_Ind = 'Y' and c.login_datetime is not null " +
                "and c.logout_datetime is null order by created_datetime desc", LoginHistory.class);
        query.setParameter(0, customerId);
        query.setParameter(1, deviceNo);
        List<LoginHistory> list = query.getResultList();
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }
}
