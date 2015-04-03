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
public class TradeForecastVo implements Serializable {
    private String applySerial;
    private String fundCode;
    private String tradeAccount;
    private List<TradeForecastDetailVo> list = Lists.newArrayList();

    public String getApplySerial() {
        return applySerial;
    }

    public void setApplySerial(String applySerial) {
        this.applySerial = applySerial;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getTradeAccount() {
        return tradeAccount;
    }

    public void setTradeAccount(String tradeAccount) {
        this.tradeAccount = tradeAccount;
    }

    public List<TradeForecastDetailVo> getList() {
        return list;
    }

    public void setList(List<TradeForecastDetailVo> list) {
        this.list = list;
    }
}
