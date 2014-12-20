package com.sunlights.customer.service.impl;

import com.sunlights.customer.dal.BankCardDao;
import com.sunlights.customer.dal.impl.BankCardDaoImpl;
import com.sunlights.customer.service.BankCardService;
import models.ShuMiAccount;

import java.util.List;

/**
 * Created by Administrator on 2014/12/19.
 */
public class BankCardServiceImpl implements BankCardService {

    private BankCardDao bankCardDao = new BankCardDaoImpl();

    @Override
    public String getBankCardByCunstId(String custId) {
        List<ShuMiAccount> accounts = bankCardDao.getByCustId(custId);
        if(accounts == null || accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0).getShumi_bankCardNo();
    }

}
