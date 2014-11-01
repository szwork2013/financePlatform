package com.sunlights.core.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.Message;
import com.sunlights.core.dal.BankCardDao;
import com.sunlights.core.models.Bank;
import com.sunlights.core.models.BankCard;
import com.sunlights.core.service.BankCardService;
import com.sunlights.core.service.BankService;
import com.sunlights.core.vo.BankCardVo;
import com.sunlights.customer.models.Customer;
import com.sunlights.customer.service.impl.CustomerService;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankCardServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */

public class BankCardServiceImpl implements BankCardService {

    private BankCardDao bankCardDao;

    private CustomerService customerService;

    private PageService pageService;

    private BankService bankService;

    @Override
    public List<BankCardVo> findBankCardsByToken(String token, PageVo pager) {
        if (StringUtils.isNotEmpty(token)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return null;
        }
        Customer customer = customerService.getCustomerByToken(token);
        String customerId = customer.getCustomerId();
        if (StringUtils.isNotEmpty(customerId)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return null;
        }
        pager.put("EQS_customerId", customerId);
        pager.put("EQS_status", "Y");

        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.bsp.models.vo.BankCardVo(c,b)");
        xsql.append(" from BankCard c , Bank b");
        xsql.append(" where c.bankId = b.id");
        xsql.append(" /~ and c.customerId = {customerId} ~/ ");
        xsql.append(" /~ and c.bankCardNo like {bankCardNo} ~/ ");
        xsql.append(" /~ and c.bankType like {bankType} ~/ ");
        xsql.append(" /~ and b.bankName like {bankName} ~/ ");
        xsql.append(" /~ and c.status = {status} ~/ ");

        List<BankCardVo> bankCardVos = pageService.findXsqlBy(xsql.toString(), pager);
        pager.getList().addAll(bankCardVos);
        MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_INFO, MsgCode.OPERATE_SUCCESS), bankCardVos);
        return bankCardVos;
    }


    @Override
    public BankCard createBankCard(String token, BankCardVo bankCardVo) {
        if (StringUtils.isNotEmpty(token)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return null;
        }
        String customerId = customerService.getCustomerByToken(token).getCustomerId();
        if (StringUtils.isNotEmpty(customerId)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return null;
        }
        BankCard bankCard = new BankCard();
        bankCard.setCustomerId(customerId);
        if (StringUtils.isNotEmpty(bankCardVo.getBankCode())) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR,  MsgCode.BIND_CARD_FAIL_EMPTY_BANK));
            return null;
        }
        if (bankCardDao.hasBankCard(bankCardVo.getNo())) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.BIND_CARD_FAIL_ALREADY_BIND));
            return null;
        }
        Bank bank = bankService.findBankByBankCode(bankCardVo.getBankCode());
        bankCard.setBankCardNo(bankCardVo.getNo());
        bankCard.setBankCode(bank.getBankCode());
        bankCard.setBankId(bank.getId());
        bankCard.setCreatedDatetime(new Timestamp(new Date().getTime()));
        bankCard.setStatus(bankCardVo.getValidateStatus());
        bankCard.setUpdateDatetime(new Timestamp(new Date().getTime()));
        bankCard.setStatus("Y");
        bankCard.setBankType("0");
        BankCard card = bankCardDao.create(bankCard);
        MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_INFO, MsgCode.OPERATE_SUCCESS));
        return card;
    }


    @Override
    public boolean deleteBankCard(String token, BankCardVo bankCardVo) {
        if (StringUtils.isNotEmpty(token)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR,  MsgCode.LOGIN_TIMEOUT));
            return false;
        }
        String customerId = customerService.getCustomerByToken(token).getCustomerId();
        if (StringUtils.isNotEmpty(customerId)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return false;
        }
        bankCardDao.deleteById(Long.valueOf(bankCardVo.getId()));
        MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_INFO, MsgCode.BANK_CARD_DELETE_SUCCESS));
        return true;
    }

}
