package com.sunlights.account.biz.impl;

import java.math.BigDecimal;

import com.sunlights.account.biz.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunlights.account.facade.CustAccountFacade;


@Component("custAccountFacade")
public class CustAccountFacadeImpl implements CustAccountFacade {
	@Autowired
	private AccountService accountService;

	@Override
	public boolean registerBaseAccount(String custId, String tradePassword) {
		return accountService.registerBaseAccount(custId, tradePassword);
	}

	@Override
	public boolean registerSubAccount(String custId, String baseAccount,
			String prdCode) {
		return false;
	}

	@Override
	public void dealAccount(String custId, String prdCode, String tradeNo,
			String tradeType, BigDecimal amount) {
		// TODO Auto-generated method stub

	}

}
