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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradeObtainRewardFailVo)) return false;

        TradeObtainRewardFailVo that = (TradeObtainRewardFailVo) o;

        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null) return false;
        if (supplySum != null ? !supplySum.equals(that.supplySum) : that.supplySum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fundCode != null ? fundCode.hashCode() : 0;
        result = 31 * result + (supplySum != null ? supplySum.hashCode() : 0);
        return result;
    }
}
