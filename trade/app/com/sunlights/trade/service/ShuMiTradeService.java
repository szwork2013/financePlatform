package com.sunlights.trade.service;

import com.sunlights.trade.vo.ShuMiTradeFormVo;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShuMiTradeService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface ShuMiTradeService {
    public String shuMiTradeOrder(ShuMiTradeFormVo shuMiTradeFormVo, String token);

    public String shuMiTradeRedeem(ShuMiTradeFormVo shuMiTradeFormVo, String token);

}
