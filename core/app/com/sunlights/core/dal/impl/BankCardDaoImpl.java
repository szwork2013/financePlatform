package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.BankCardDao;
import models.BankCard;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankCardDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class BankCardDaoImpl extends EntityBaseDao implements BankCardDao {
  @Override
  public BankCard create(BankCard bankCard) {
    return super.create(bankCard);
  }

  @Override
  public void deleteById(Long bankId) {
    BankCard bankCard = super.find(BankCard.class, bankId);
    super.delete(bankCard);
  }

  @Override
  public void deleteByNo(String no) {
    List<BankCard> bankCards = super.findBy(BankCard.class, "bankCardNo", no);
    if (!bankCards.isEmpty()) {
      super.delete(bankCards.get(0));
    }
  }

  @Override
  public boolean hasBankCard(String bankCardNo) {
    List<BankCard> bankCards = super.findBy(BankCard.class, "bankCardNo", bankCardNo);
    return !bankCards.isEmpty();
  }

}
