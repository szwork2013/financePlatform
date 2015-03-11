package com.sunlights.customer.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.vo.BankVo;
import models.Bank;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface BankService {
    public List<BankVo> findBanksBy(PageVo pageVo);

    public Bank findBankByBankCode(String bankCode);

    public Bank findBankByBankName(String bankName);

}
