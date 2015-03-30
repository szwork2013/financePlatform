package com.sunlights.trade.dal;

import com.sunlights.trade.vo.TradeStatusInfoVo;
import models.TradeStatusChange;

import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: TradeStatusChangeDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface TradeStatusChangeDao {

    public TradeStatusChange createTradeStatusChange(TradeStatusChange tradeStatusChange);
    public TradeStatusChange updateTradeStatusChange(TradeStatusChange tradeStatusChange);

    public List<TradeStatusInfoVo> findTradeStatusChangeList(String tradeNo);

}
