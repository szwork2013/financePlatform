package com.sunlights.trade.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: TradeInfoVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class TradeInfoVo implements Serializable {
    private String tradeNo;
    private String tradeTime;
    private List<TradeStatusInfoVo> tradeInfoVoList = Lists.newArrayList();

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public List<TradeStatusInfoVo> getTradeInfoVoList() {
        return tradeInfoVoList;
    }

    public void setTradeInfoVoList(List<TradeStatusInfoVo> tradeInfoVoList) {
        this.tradeInfoVoList = tradeInfoVoList;
    }
}
