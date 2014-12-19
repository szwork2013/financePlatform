package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.BankCardDao;
import models.ShuMiAccount;

import java.util.List;

/**
 * Created by Administrator on 2014/12/19.
 */
public class BankCardDaoImpl extends EntityBaseDao implements BankCardDao {

    @Override
    public List<ShuMiAccount> getByCustId(String custId) {
        return super.findBy(ShuMiAccount.class, "customerId", custId);
    }
}
