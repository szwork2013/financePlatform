package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.LoginDao;
import models.LoginHistory;

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

public class LoginDaoImpl extends EntityBaseDao implements LoginDao {
  @Override
  public LoginHistory saveLoginHistory(LoginHistory loginHistory) {
    return create(loginHistory);
  }

  @Override
  public LoginHistory updateLoginHistory(LoginHistory loginHistory) {
    return update(loginHistory);
  }

  public LoginHistory findByPwd(String customerId, String deviceNo) {
    Query query = em.createQuery("select c FROM LoginHistory c where c.customerId = ?0 " +
        "and c.deviceNo = ?1 and c.pwdInd = 'Y'" +
        "order by c.createTime desc", LoginHistory.class);
    query.setParameter(0, customerId);
    query.setParameter(1, deviceNo);
    List<LoginHistory> list = query.getResultList();
    if (list != null && list.size() != 0) {
      return list.get(0);
    }
    return null;
  }

  public LoginHistory findByGesturePwd(String customerId, String deviceNo) {
    Query query = em.createQuery("select c FROM LoginHistory c where c.customerId = ?0 " +
            "and c.deviceNo = ?1 and c.gestureInd = 'Y'" +
            "order by c.createTime desc", LoginHistory.class);
    query.setParameter(0, customerId);
    query.setParameter(1, deviceNo);
    List<LoginHistory> list = query.getResultList();
    if (list != null && list.size() != 0) {
      return list.get(0);
    }
    return null;
  }

  public LoginHistory findByLoginCustomer(String customerId, String deviceNo) {
    Query query = em.createQuery("select c FROM LoginHistory c where c.customerId = ?0 " +
        "and c.deviceNo = ?1 and c.successInd = 'Y' and c.loginTime is not null " +
        "and c.logoutTime is null order by c.createTime desc", LoginHistory.class);
    query.setParameter(0, customerId);
    query.setParameter(1, deviceNo);
    List<LoginHistory> list = query.getResultList();
    if (list != null && list.size() != 0) {
      return list.get(0);
    }
    return null;
  }
}
