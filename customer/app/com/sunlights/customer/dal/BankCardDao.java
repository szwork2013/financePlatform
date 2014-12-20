package com.sunlights.customer.dal;

import models.ShuMiAccount;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/19.
 */
public interface BankCardDao {

    public List<ShuMiAccount> getByCustId(String custId);

}
