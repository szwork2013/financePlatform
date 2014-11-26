package com.sunlights.core.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.dal.BankCardDao;
import com.sunlights.core.dal.impl.BankCardDaoImpl;
import com.sunlights.core.service.BankCardService;
import com.sunlights.core.service.BankService;
import com.sunlights.core.vo.BankCardVo;
import com.sunlights.customer.service.impl.CustomerService;
import models.Bank;
import models.BankCard;
import models.CustomerSession;
import play.db.jpa.Transactional;

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
    private BankCardDao bankCardDao = new BankCardDaoImpl();
    private CustomerService customerService = new CustomerService();
    private PageService pageService = new PageService();
    private BankService bankService = new BankServiceImpl();

    @Override
    public List<BankCardVo> findBankCardsByToken(String token, PageVo pageVo) {
        CustomerSession customerSession = customerService.getCustomerSession(token);
        pageVo.put("EQS_customerId", customerSession.getCustomerId());

        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.core.vo.BankCardVo(c,b)");
        xsql.append(" from BankCard c , Bank b");
        xsql.append(" where c.bankId = b.id");
        xsql.append(" /~ and c.customerId = {customerId} ~/ ");
        xsql.append(" /~ and c.bankCardNo = {bankCardNo} ~/ ");
        xsql.append(" /~ and c.bankType like {bankType} ~/ ");
        xsql.append(" /~ and b.bankName like {bankName} ~/ ");
        xsql.append(" /~ and c.status = {status} ~/ ");

        List<BankCardVo> bankCardVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        pageVo.getList().addAll(bankCardVos);
        MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), bankCardVos);
        return bankCardVos;
    }

    @Transactional
    @Override
    public BankCard createBankCard(String token, BankCardVo bankCardVo) {
        CustomerSession customerSession = customerService.getCustomerSession(token);
        BankCard bankCard = new BankCard();
        bankCard.setCustomerId(customerSession.getCustomerId());
//        if (StringUtils.isEmpty(bankCardVo.getBankCode())) {
//            MessageUtil.getInstance().setMessage(new Message(Severity.ERROR, MsgCode.BIND_CARD_FAIL_EMPTY_BANK));
//            return null;
//        }
        if (bankCardDao.hasBankCard(bankCardVo.getBankCardNo())) {
            MessageUtil.getInstance().setMessage(new Message(Severity.ERROR, MsgCode.BIND_CARD_FAIL_ALREADY_BIND));
            return null;
        }
//        Bank bank = bankService.findBankByBankCode(bankCardVo.getBankCode());
//        bankCard.setBankCode(bank.getBankCode());
//        bankCard.setBankId(bank.getId());
        Bank bank = bankService.findBankByBankName(bankCardVo.getBankName());
        if (bank != null) {
            bankCard.setBankCode(bank.getBankCode());
            bankCard.setBankId(bank.getId());
        }
        bankCard.setBankCardNo(bankCardVo.getBankCardNo());
        bankCard.setBankSerial(bankCardVo.getBankSerial());
        bankCard.setCreateTime(new Timestamp(new Date().getTime()));
        bankCard.setStatus(bankCardVo.getValidateStatus());
        bankCard.setUpdateTime(new Timestamp(new Date().getTime()));
        bankCard.setStatus("Y");
        bankCard.setBankType("0");
        BankCard card = bankCardDao.create(bankCard);
        MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.BANK_CARD_ADD_SUCCESS));
        return card;
    }

    @Transactional
    @Override
    public boolean deleteBankCard(String token, BankCardVo bankCardVo) {
        bankCardDao.deleteById(Long.valueOf(bankCardVo.getId()));
        MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.BANK_CARD_DELETE_SUCCESS));
        return true;
    }

}
