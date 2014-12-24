package com.sunlights.customer.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.dal.BankCardDao;
import com.sunlights.customer.dal.impl.BankCardDaoImpl;
import com.sunlights.customer.service.BankCardService;
import com.sunlights.customer.vo.BankCardFormVo;
import com.sunlights.customer.vo.BankCardVo;
import models.BankCard;
import models.CustomerSession;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/12/19.
 */
public class BankCardServiceImpl implements BankCardService {

    private BankCardDao bankCardDao = new BankCardDaoImpl();
    private CustomerService customerService = new CustomerService();
    private PageService pageService = new PageService();

    @Override
    public BankCard createBankCard(String customerId, BankCardVo bankCardVo) {
        if (bankCardDao.hasBankCard(bankCardVo.getBankCard())) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.BIND_CARD_FAIL_ALREADY_BIND));
        }
        BankCard bankCard = new BankCard();
        bankCard.setCustomerId(customerId);
        bankCard.setBankName(bankCardVo.getBankName());
        bankCard.setBankCardNo(bankCardVo.getBankCard());
        bankCard.setBankSerial(bankCardVo.getBankSerial());
        bankCard.setCreateTime(new Timestamp(new Date().getTime()));
        bankCard.setLoadAllInd(AppConst.STATUS_INVALID);
        BankCard card = bankCardDao.create(bankCard);
        MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.BANK_CARD_ADD_SUCCESS));
        return card;
    }

    @Override
    public void saveAllBankCard(String customerId, List<BankCardFormVo> list) {
        List<BankCard> bankCardList = bankCardDao.findBankCards(customerId);
        for (BankCard bankCard : bankCardList) {
            bankCard.setDeleted(true);
            bankCard.setUpdateTime(DBHelper.getCurrentTime());
            bankCardDao.updateBankCard(bankCard);
        }

        for (BankCardFormVo bankCardFormVo : list) {
            try {
                BankCard bankCard = new BankCard();
                bankCard = ConverterUtil.toEntity(bankCard, bankCardFormVo);
                bankCard.setBankCardNo(bankCardFormVo.getNo());
                bankCard.setCreateTime(DBHelper.getCurrentTime());
                bankCard.setLoadAllInd(AppConst.STATUS_VALID);
                bankCard.setCustomerId(customerId);
                bankCardDao.create(bankCard);
            } catch (ConverterException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public String getBankCardByCustId(String custId) {
        BankCard bankCard = bankCardDao.getByCustId(custId);
        if (bankCard != null){
            return bankCard.getBankCardNo();
        }
        return null;
    }


    @Override
    public List<BankCardVo> findBankCardsByToken(String token, PageVo pageVo) {
        CustomerSession customerSession = customerService.getCustomerSession(token);
        pageVo.put("EQS_customerId", customerSession.getCustomerId());

        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.customer.vo.BankCardVo(c)");
        xsql.append(" from BankCard c");
        xsql.append(" where 1 = 1");
        xsql.append(" /~ and c.customerId = {customerId} ~/ ");
        xsql.append(" /~ and c.bankCardNo = {bankCardNo} ~/ ");
        xsql.append(" /~ and c.bankName like {bankName} ~/ ");
        xsql.append(" /~ and c.deleted = {deleted} ~/ ");

        List<BankCardVo> bankCardVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        pageVo.getList().addAll(bankCardVos);
        MessageUtil.getInstance().setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), bankCardVos);
        return bankCardVos;
    }

    @Override
    public boolean deleteBankCard(Long bankCardId) {
        bankCardDao.deleteById(bankCardId);
        return true;
    }

}
