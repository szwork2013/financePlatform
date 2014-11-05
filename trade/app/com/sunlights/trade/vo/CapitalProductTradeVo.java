package com.sunlights.trade.vo;

import com.sunlights.account.vo.HoldCapitalVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: CapitalProductTradeVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class CapitalProductTradeVo implements Serializable{
    private HoldCapitalVo holdCapitalVo;
    private List list;
    private int tradeCount;

    public HoldCapitalVo getHoldCapitalVo() {
        return holdCapitalVo;
    }

    public void setHoldCapitalVo(HoldCapitalVo holdCapitalVo) {
        this.holdCapitalVo = holdCapitalVo;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }
}
