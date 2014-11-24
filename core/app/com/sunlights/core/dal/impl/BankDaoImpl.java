package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.BankDao;
import models.Bank;

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
}
