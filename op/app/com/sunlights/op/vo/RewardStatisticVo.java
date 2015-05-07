package com.sunlights.op.vo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by tangweiqun on 2015/5/6.
 */
public class RewardStatisticVo {
    private String customerName;

    private String mobile;

    private String rewardAcctBalance;

    private Long goldBeanNum;

    private Long canExchangeBeans;

    private Long alreadyExchangeBeans;

    private String redPacketNum;

    private String canExchangeRedPacket;

    private String alreadyExchangeRedPacket;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRewardAcctBalance() {
        return rewardAcctBalance;
    }

    public void setRewardAcctBalance(String rewardAcctBalance) {
        this.rewardAcctBalance = rewardAcctBalance;
    }

    public Long getGoldBeanNum() {
        return goldBeanNum;
    }

    public void setGoldBeanNum(Long goldBeanNum) {
        this.goldBeanNum = goldBeanNum;
    }

    public Long getCanExchangeBeans() {
        return canExchangeBeans;
    }

    public void setCanExchangeBeans(Long canExchangeBeans) {
        this.canExchangeBeans = canExchangeBeans;
    }

    public Long getAlreadyExchangeBeans() {
        return alreadyExchangeBeans;
    }

    public void setAlreadyExchangeBeans(Long alreadyExchangeBeans) {
        this.alreadyExchangeBeans = alreadyExchangeBeans;
    }

    public String getRedPacketNum() {
        return redPacketNum;
    }

    public void setRedPacketNum(String redPacketNum) {
        this.redPacketNum = redPacketNum;
    }

    public String getCanExchangeRedPacket() {
        return canExchangeRedPacket;
    }

    public void setCanExchangeRedPacket(String canExchangeRedPacket) {
        this.canExchangeRedPacket = canExchangeRedPacket;
    }

    public String getAlreadyExchangeRedPacket() {
        return alreadyExchangeRedPacket;
    }

    public void setAlreadyExchangeRedPacket(String alreadyExchangeRedPacket) {
        this.alreadyExchangeRedPacket = alreadyExchangeRedPacket;
    }

}
