package com.sunlights.trade.biz;

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
     * @param customerId 客户号
     * @param productType 产品类型，过滤条件 0全部 1基金
     * @return
     */
    public List<TradeVo> getTradeListByCustomerId(String customerId, String productType);
    public List<TradeVo> getTradeListByToken(String token, String productType);
}
