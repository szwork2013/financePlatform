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


public class TradeStatusInfoVo implements Serializable{
    private String timeInfo;
    private String desc;

    public String getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(String timeInfo) {
        this.timeInfo = timeInfo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
