package com.sunlights.account.vo;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/20.
 */
public class HoldRewardVo implements Serializable{
    //总金豆数量
    private String totalReward;
    //总赚取
    private String obtain;
    //等价现金
    private String totalCash;
    //已兑换
    private String exchange;

    private List<RewardFlowVo> rewardFlowVos = Lists.newArrayList();

    public String getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(String totalReward) {
        this.totalReward = totalReward;
    }

    public String getObtain() {
        return obtain;
    }

    public void setObtain(String obtain) {
        this.obtain = obtain;
    }

    public String getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(String totalCash) {
        this.totalCash = totalCash;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public List<RewardFlowVo> getRewardFlowVos() {
        return rewardFlowVos;
    }

    public void setRewardFlowVos(List<RewardFlowVo> rewardFlowVos) {
        this.rewardFlowVos = rewardFlowVos;
    }
}
