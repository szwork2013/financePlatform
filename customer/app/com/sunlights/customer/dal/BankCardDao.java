package com.sunlights.customer.dal;

import models.BankCard;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/19.
 */
public interface BankCardDao {

    public BankCard getByCustId(String custId);

    public BankCard create(BankCard bankCard);

    public void deleteById(Long bankCardId);

    public List<BankCard> findBankCards(String customerId);

    public void updateBankCard(BankCard bankCard);

    public boolean hasBankCard(String bankCardNo);

}
