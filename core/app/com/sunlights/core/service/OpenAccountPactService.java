package com.sunlights.core.service;

import com.sunlights.core.vo.AgreementVo;
import com.sunlights.customer.vo.BankCardVo;

/**
 * <p>Project: fsp</p>
 * <p>Title: OpenAccountPactService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface OpenAccountPactService {
    public AgreementVo findAgreementVoByAgreementNo(String agreementNo);

    public void createFundOpenAccount(String customerId, BankCardVo bankCardVo);
}
