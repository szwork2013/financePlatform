package com.sunlights.customer.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.vo.BankCardFormVo;
import com.sunlights.customer.vo.BankCardVo;
import models.BankCard;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/19.
 */
public interface BankCardService {

    public String getBankCardByCustId(String custId);

    public BankCard createBankCard(String customerId, BankCardVo bankCardVo);

    public void saveAllBankCard(String customerId, List<BankCardFormVo> list);


    public List<BankCardVo> findBankCardsByToken(String token, PageVo pageVo);

    public boolean deleteBankCard(Long bankCardId);

}
