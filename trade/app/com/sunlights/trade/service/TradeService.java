package com.sunlights.trade.service;

import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.vo.PageVo;
import com.sunlights.trade.vo.CapitalProductTradeVo;
import com.sunlights.trade.vo.TradeSearchFormVo;
import com.sunlights.trade.vo.TradeVo;
import models.Trade;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: TradeService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface TradeService {
    /**
     * 获取交易记录
     * @param token 客户号
     * @param tradeSearchFormVo
     * @return
     */
    public List<TradeVo> getTradeListByToken(String token, TradeSearchFormVo tradeSearchFormVo,PageVo pageVo) throws BusinessRuntimeException;

    /**
     * 产品详情及记录
     * @param token
     * @param tradeSearchFormVo
     * @return
     */
    public CapitalProductTradeVo findCapitalProductDetailTrade(String token, TradeSearchFormVo tradeSearchFormVo);


    public Trade createTrade();
}
