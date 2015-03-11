package com.sunlights.customer.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangweiqun on 2014/12/4.
 */
public class TradeObtainRewardSuccVo implements Serializable {
    private String fundCode;

    private String supplySum;

    private List<ActivityResultVo> records = new ArrayList<ActivityResultVo>();

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

    public List<ActivityResultVo> getRecords() {
        return records;
    }

    public void setRecords(List<ActivityResultVo> records) {
        this.records = records;
    }
}
