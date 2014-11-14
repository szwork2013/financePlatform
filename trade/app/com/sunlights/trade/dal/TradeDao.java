package com.sunlights.trade.dal;

import com.sunlights.common.vo.PageVo;
import com.sunlights.trade.vo.TradeVo;
import models.Trade;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: TradeDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface TradeDao {

  public String getTradeNoSeq();

  /**
   * 获取交易记录
   * @param customerId        客户号
   * @param productCode
   * @return
   */
  public List<TradeVo> getTradeListByCustomerId(String customerId, String productCode, PageVo pageVo);

  public Trade saveTrade(Trade trade);

  public Trade updateTrade(Trade trade);

}
