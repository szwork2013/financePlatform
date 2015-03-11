package com.sunlights.customer.service.impl;

import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.dal.BankDao;
import com.sunlights.customer.dal.impl.BankDaoImpl;
import com.sunlights.customer.service.BankService;
import com.sunlights.customer.vo.BankVo;
import models.Bank;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class BankServiceImpl implements BankService {
    private BankDao bankDao = new BankDaoImpl();

    private PageService pageService = new PageService();

    private CustomerService customerService = new CustomerService();

    @Override
    public List<BankVo> findBanksBy(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.customer.vo.BankVo(b)");
        xsql.append(" from Bank b");
        xsql.append(" where 1=1");
        xsql.append(" /~ and b.bankName like {bankName} ~/ ");
        xsql.append(" /~ and b.bankCode like {bankCode} ~/ ");
        xsql.append(" /~ and b.status = {status} ~/ ");
        List<BankVo> bankVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        pageVo.getList().addAll(bankVos);
        return bankVos;
    }


    @Override
    public Bank findBankByBankCode(String bankCode) {
        return bankDao.findBankByBankCode(bankCode);
    }

    public Bank findBankByBankName(String bankName) {
        return bankDao.findBankByBankName(bankName);
    }

}
