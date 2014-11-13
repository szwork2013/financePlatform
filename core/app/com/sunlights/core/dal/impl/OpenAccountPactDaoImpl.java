package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.OpenAccountPactDao;
import models.FundOpenAccount;
import models.OpenAccountPact;

import javax.persistence.Query;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: OpenAccountPactDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class OpenAccountPactDaoImpl extends EntityBaseDao implements OpenAccountPactDao {
    @Override
    public OpenAccountPact findOpenAccountPactByAgreementNo(String agreementNo) {
        Query query = createNameQuery("findOpenAccountPactByAgreementNo", agreementNo);
        List<OpenAccountPact> openAccountPacts = query.getResultList();
        if (openAccountPacts.isEmpty()) {
            return null;
        }
        return openAccountPacts.get(0);
    }

    @Override
    public FundOpenAccount findFundOpenAccount(String customerId, String bankCardNo) {
        Query query = createNameQuery("findFundOpenAccount", customerId, bankCardNo);
        List<FundOpenAccount> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public FundOpenAccount saveFundOpenAccount(FundOpenAccount fundOpenAccount) {
        return create(fundOpenAccount);
    }


}
