package com.sunlights.core.dal;

import models.FundOpenAccount;
import models.OpenAccountPact;

/**
 * <p>Project: fsp</p>
 * <p>Title: OpenAccountPactDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface OpenAccountPactDao {
  public OpenAccountPact findOpenAccountPactByAgreementNo(String agreementNo);


  public FundOpenAccount findFundOpenAccount(String customerId, String bankCardNo);

  public FundOpenAccount saveFundOpenAccount(FundOpenAccount fundOpenAccount);
}
