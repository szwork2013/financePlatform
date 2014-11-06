package com.sunlights.core.service.impl;

import com.sunlights.common.utils.DBHelper;
import com.sunlights.core.dal.OpenAccountPactDao;
import com.sunlights.core.service.BankCardService;
import models.FundOpenAccount;
import models.OpenAccountPact;
import com.sunlights.core.dal.impl.OpenAccountPactDaoImpl;
import com.sunlights.core.service.OpenAccountPactService;
import com.sunlights.core.vo.AgreementVo;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

/**
 * <p>Project: fsp</p>
 * <p>Title: OpenAccountPactServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class OpenAccountPactServiceImpl implements OpenAccountPactService {
    private OpenAccountPactDao openAccountPactDao = new OpenAccountPactDaoImpl();
    private BankCardService bankCardService = new BankCardServiceImpl();

    @Override
    public AgreementVo findAgreementVoByAgreementNo(String agreementNo) {
        if (StringUtils.isEmpty(agreementNo)) {
            return null;
        }
        OpenAccountPact openAccountPact = openAccountPactDao.findOpenAccountPactByAgreementNo(agreementNo);
        if (openAccountPact == null) {
            return null;
        }
        return new AgreementVo(openAccountPact);
    }


    @Override
    public void createFundOpenAccount(String customerId, String bankCardNo) {
        FundOpenAccount fundOpenAccount = openAccountPactDao.findFundOpenAccount(customerId,bankCardNo);
        if (fundOpenAccount == null) {

            //TODO 银行信息查询
            Timestamp currentTime = DBHelper.getCurrentTime();

            fundOpenAccount = new FundOpenAccount();
            fundOpenAccount.setCustomerId(customerId);
            fundOpenAccount.setBankCardNo(bankCardNo);
            fundOpenAccount.setCreateTime(currentTime);
            fundOpenAccount.setUpdateTime(currentTime);
            openAccountPactDao.saveFundOpenAccount(fundOpenAccount);
        }
    }
}
