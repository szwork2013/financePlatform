package com.sunlights.core.dal;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.models.Bank;
import org.springframework.stereotype.Repository;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Repository
public class BankDaoImpl extends EntityBaseDao implements BankDao {

    @Override
    public Bank findBankByBankCode(String bankCode) {
        return super.findUniqueBy(Bank.class, "bankCode", bankCode);
    }
}
