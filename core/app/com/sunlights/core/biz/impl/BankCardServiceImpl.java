package com.sunlights.core.biz.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.core.biz.BankCardService;
import com.sunlights.core.biz.BankService;
import com.sunlights.core.dal.BankCardDao;
import com.sunlights.core.integration.CommonClient;
import com.sunlights.core.models.Bank;
import com.sunlights.core.models.BankCard;
import com.sunlights.core.vo.BankCardVo;
import com.sunlights.common.page.PageService;
import com.sunlights.common.page.Pager;
import com.sunlights.common.utils.StringUtils;
import com.sunlights.common.utils.msg.Message;
import com.sunlights.common.utils.msg.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class BankCardServiceImpl implements BankCardService {
    @Autowired
    private BankCardDao bankCardDao;
    @Autowired
    private CommonClient commonClient;
    @Autowired
    private PageService pageService;
    @Autowired
    private BankService bankService;

    @Override
    public List<BankCardVo> findBankCardsByToken(String token, Pager pager) {
        if (StringUtils.isBlankOrNull(token)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return null;
        }
        String customerId = commonClient.findCustomerIdByToken(token);
        if (StringUtils.isBlankOrNull(customerId)) {
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

    @Transactional
    @Override
    public BankCard createBankCard(String token, BankCardVo bankCardVo) {
        if (StringUtils.isBlankOrNull(token)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return null;
        }
        String customerId = commonClient.findCustomerIdByToken(token);
        if (StringUtils.isBlankOrNull(customerId)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return null;
        }
        BankCard bankCard = new BankCard();
        bankCard.setCustomerId(customerId);
        if (StringUtils.isBlankOrNull(bankCardVo.getBankCode())) {
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

    @Transactional
    @Override
    public boolean deleteBankCard(String token, BankCardVo bankCardVo) {
        if (StringUtils.isBlankOrNull(token)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR,  MsgCode.LOGIN_TIMEOUT));
            return false;
        }
        String customerId = commonClient.findCustomerIdByToken(token);
        if (StringUtils.isBlankOrNull(customerId)) {
            MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_ERROR, MsgCode.LOGIN_TIMEOUT));
            return false;
        }
        bankCardDao.deleteById(Long.valueOf(bankCardVo.getId()));
        MessageUtil.getInstance().addMessage(new Message(Message.SEVERITY_INFO, MsgCode.BANK_CARD_DELETE_SUCCESS));
        return true;
    }

}
