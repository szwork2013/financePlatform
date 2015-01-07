package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.BankDao;
import models.Bank;

import javax.persistence.Query;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class BankDaoImpl extends EntityBaseDao implements BankDao {

  @Override
  public Bank findBankByBankCode(String bankCode) {
    return super.findUniqueBy(Bank.class, "bankCode", bankCode);
  }

    @Override
    public Bank findBankByBankName(String bankName) {
        String sql = "select b from Bank b where b.bankName like '%" + bankName + "%'";
        Query query = em.createQuery(sql, Bank.class);

        List<Bank> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
