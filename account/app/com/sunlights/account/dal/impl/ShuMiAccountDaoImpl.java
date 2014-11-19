package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.ShuMiAccountDao;
import com.sunlights.common.dal.EntityBaseDao;
import models.ShuMiAccount;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShuMiAccountDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ShuMiAccountDaoImpl extends EntityBaseDao implements ShuMiAccountDao{
    @Override
    public ShuMiAccount saveShuMiAccount(ShuMiAccount shuMiAccount) {
        return create(shuMiAccount);
    }
}
