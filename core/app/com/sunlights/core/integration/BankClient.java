package com.sunlights.core.integration;

import com.sunlights.core.vo.BankVo;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankClient.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface BankClient {
    public BankVo findBankByBankCardNo(String bankCardNo);
    public boolean validateBankCard(String idCardNo, String bankCardNo);
}
