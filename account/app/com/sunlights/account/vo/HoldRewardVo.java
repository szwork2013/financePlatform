package com.sunlights.account.vo;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/20.
 */
public class HoldRewardVo implements Serializable{
    //总金豆数量
    private String totalReward;
    //总赚取
    private String gots;
    //等价现金
    private String totalCash;
    //已兑换
    private String payed;

    private List<RewardFlowVo> records = Lists.newArrayList();

    public String getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(String totalReward) {
        this.totalReward = totalReward;
    }


    public String getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(String totalCash) {
        this.totalCash = totalCash;
    }


    public List<RewardFlowVo> getRecords() {
        return records;
    }

    public void setRecords(List<RewardFlowVo> records) {
        this.records = records;
    }

    public String getGots() {
        return gots;
    }

    public void setGots(String gots) {
        this.gots = gots;
    }

    public String getPayed() {
        return payed;
    }

    public void setPayed(String payed) {
        this.payed = payed;
    }
}
