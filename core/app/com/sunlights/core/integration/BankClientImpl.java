package com.sunlights.core.integration;

import com.sunlights.core.vo.BankVo;
import org.springframework.stereotype.Component;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankCilentImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class BankClientImpl implements BankClient {
  @Override
  public BankVo findBankByBankCardNo(String bankCardNo) {
    BankVo bankVo = new BankVo();
    if ("6225885105574736".equals(bankCardNo)) {
      bankVo.bankCode = "CCB";
    }
    return bankVo;
  }

  @Override
  public boolean validateBankCard(String idCardNo, String bankCardNo) {
    return true;
  }
}
