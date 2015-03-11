package com.sunlights.trade.service;

import com.sunlights.account.vo.TotalCapitalInfo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.trade.vo.CapitalProductTradeVo;
import com.sunlights.trade.vo.TradeFormVo;
import com.sunlights.trade.vo.TradeSearchFormVo;
import com.sunlights.trade.vo.TradeVo;

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
     *
     * @param token             客户号
     * @param tradeSearchFormVo
     * @return
     */
    public List<TradeVo> getTradeListByToken(String token, TradeSearchFormVo tradeSearchFormVo, PageVo pageVo);

    /**
     * 产品详情及记录
     *
     * @param token
     * @param tradeSearchFormVo
     * @return
     */
    public CapitalProductTradeVo findCapitalProductDetailTrade(String token, TradeSearchFormVo tradeSearchFormVo);


    /**
     * 下单
     *
     * @param tradeFormVo
     * @param token
     */
    public TotalCapitalInfo tradeFundOrder(TradeFormVo tradeFormVo, String token);

    /**
     * 赎回
     *
     * @param tradeFormVo
     * @param token
     * @return
     */
    public TotalCapitalInfo tradeFundRedeem(TradeFormVo tradeFormVo, String token);
}
