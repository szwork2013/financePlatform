package com.sunlights.core.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.BankCardVo;
import models.BankCard;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankCardService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface BankCardService {
  public List<BankCardVo> findBankCardsByToken(String token, PageVo pageVo);

  public BankCard createBankCard(String token, BankCardVo bankCardVo);

  public boolean deleteBankCard(String token, BankCardVo bankCardVo);


}
