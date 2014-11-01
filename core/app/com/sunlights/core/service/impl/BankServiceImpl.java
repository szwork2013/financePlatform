package com.sunlights.core.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.Message;
import com.sunlights.core.dal.BankDao;
import com.sunlights.core.integration.BankClient;
import com.sunlights.core.models.Bank;
import com.sunlights.core.service.BankService;
import com.sunlights.core.vo.BankCardVo;
import com.sunlights.core.vo.BankVo;
import com.sunlights.customer.service.impl.CustomerService;
import org.apache.commons.lang3.StringUtils;

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

    private BankDao bankDao;


    private PageService pageService;


    private BankClient bankClient;


    private CustomerService customerService;

    @Override
    public List<BankVo> findBanksBy(PageVo pager) {
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.bsp.models.vo.BankVo(b)");
        xsql.append(" from Bank b");
        xsql.append(" where 1=1");
        xsql.append(" /~ and b.bankName like {bankName} ~/ ");
        xsql.append(" /~ and b.bankCode like {bankCode} ~/ ");
        xsql.append(" /~ and b.status = {status} ~/ ");
        List<BankVo> bankVos = pageService.findXsqlBy(xsql.toString(), pager);
        pager.getList().addAll(bankVos);
        return bankVos;
    }

    @Override
    public BankVo findBankByBankCardNo(String bankCardNo) {
        return bankClient.findBankByBankCardNo(bankCardNo);
    }

    @Override
    public Bank findBankByBankCode(String bankCode) {
        return bankDao.findBankByBankCode(bankCode);
    }

    @Override
    public boolean validateBankCard(String token, BankCardVo bankCardVo) {
        if (StringUtils.isNotEmpty(token)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return false;
        }
        String idCardNo = customerService.getCustomerByToken(token).getIdentityNumber();
        if (StringUtils.isNotEmpty(idCardNo)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.BANK_NAME_CERTIFY_FAIL));
            return false;
        }
        return bankClient.validateBankCard(idCardNo, bankCardVo.getNo());
    }
}
