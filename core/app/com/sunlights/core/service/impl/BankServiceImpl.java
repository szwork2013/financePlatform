package com.sunlights.core.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.core.service.BankService;
import com.sunlights.core.dal.BankDao;
import com.sunlights.core.integration.BankClient;
import com.sunlights.core.models.Bank;
import com.sunlights.core.vo.BankCardVo;
import com.sunlights.core.vo.BankVo;
import com.sunlights.common.page.PageService;
import com.sunlights.common.page.Pager;
import com.sunlights.common.utils.StringUtils;
import com.sunlights.common.utils.msg.Message;
import com.sunlights.common.utils.msg.MessageUtil;
import com.sunlights.customer.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankDao bankDao;

    @Autowired
    private PageService pageService;

    @Autowired
    private BankClient bankClient;

    @Autowired
    private CustomerService customerService;

    @Override
    public List<BankVo> findBanksBy(Pager pager) {
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
        if (StringUtils.isBlankOrNull(token)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return false;
        }
        String idCardNo = customerService.getCustomerByToken(token).getIdentityNumber();
        if (StringUtils.isBlankOrNull(idCardNo)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.BANK_NAME_CERTIFY_FAIL));
            return false;
        }
        return bankClient.validateBankCard(idCardNo, bankCardVo.getNo());
    }
}
