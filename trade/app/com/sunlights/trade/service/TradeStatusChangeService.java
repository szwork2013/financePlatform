package com.sunlights.trade.service;

import com.sunlights.trade.vo.TradeForecastDetailVo;
import com.sunlights.trade.vo.TradeForecastFormVo;
import models.Trade;
import models.TradeStatusChange;

import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: TradeStatusChangeService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface TradeStatusChangeService {

    public void createTradeStatusChange(Trade trade);

    public TradeStatusChange updateTradeStatusChange(TradeStatusChange tradeStatusChange);

    public List<TradeForecastDetailVo> findTradeStatusChangeList(TradeForecastFormVo tradeInfoFormVo);
}
