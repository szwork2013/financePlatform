package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.OpenAccountPactDao;
import models.FundOpenAccount;
import models.OpenAccountPact;

import javax.persistence.Query;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: OpenAccountPactDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class OpenAccountPactDaoImpl extends EntityBaseDao implements OpenAccountPactDao {
  @Override
  public OpenAccountPact findOpenAccountPactByAgreementNo(String agreementNo) {
    StringBuffer jpql = new StringBuffer();
    jpql.append(" select o from OpenAccountPact o");
    jpql.append(" where o.status = 'Y'");
    jpql.append(" and o.agreementNo = '" + agreementNo + "'");
    List<OpenAccountPact> openAccountPacts = super.find(jpql.toString());
    if (!openAccountPacts.isEmpty()) {
      return openAccountPacts.get(0);
    }
    return null;
  }

  @Override
  public FundOpenAccount findFundOpenAccount(String customerId, String bankCardNo) {
    String sql = "select oa from FundOpenAccount oa where oa.customer_id = ?0 and oa.bankCardNo = ?1";
    Query query = em.createQuery(sql, FundOpenAccount.class);
    query.setParameter(0, customerId);
    query.setParameter(1, bankCardNo);
    List<FundOpenAccount> list = query.getResultList();
    if (list == null || list.isEmpty()) {
      return null;
    }

    return list.get(0);
  }

  public FundOpenAccount saveFundOpenAccount(FundOpenAccount fundOpenAccount) {
    return create(fundOpenAccount);
  }


}
