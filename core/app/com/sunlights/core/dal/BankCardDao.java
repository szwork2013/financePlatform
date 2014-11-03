package com.sunlights.core.dal;

import models.BankCard;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankCardDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface BankCardDao {
    public BankCard create(BankCard bankCard);
    public void deleteById(Long bankId);
    public boolean hasBankCard(String bankCardNo);
}
