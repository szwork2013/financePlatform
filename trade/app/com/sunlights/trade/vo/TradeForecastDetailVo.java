package com.sunlights.trade.vo;

import java.io.Serializable;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: TradeStatusInfoVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */


public class TradeForecastDetailVo implements Serializable{
    private String time;
    private String desc;
    private String completeInd;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCompleteInd() {
        return completeInd;
    }

    public void setCompleteInd(String completeInd) {
        this.completeInd = completeInd;
    }
}
