package com.sunlights.core.dal;

import models.Bank;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface BankDao {
  public Bank findBankByBankCode(String bankCode);
}
