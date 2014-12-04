package com.sunlights.customer.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/4.
 */
public class TradeObtainRewardFailVo implements Serializable {
    private String fundCode;

    private String supplySum;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getSupplySum() {
        return supplySum;
    }

    public void setSupplySum(String supplySum) {
        this.supplySum = supplySum;
    }
}
