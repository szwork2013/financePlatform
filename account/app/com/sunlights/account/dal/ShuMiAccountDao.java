package com.sunlights.account.dal;

import models.ShuMiAccount;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShuMiAccountDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface ShuMiAccountDao {
    public ShuMiAccount saveShuMiAccount(ShuMiAccount shuMiAccount);

    public ShuMiAccount findShuMiAccountByCustomerId(String customerId);

    public ShuMiAccount updateShuMiAccount(ShuMiAccount shuMiAccount);


}
