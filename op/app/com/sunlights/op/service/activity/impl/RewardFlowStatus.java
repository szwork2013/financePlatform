package com.sunlights.op.service.activity.impl;

/**
 * Created by Administrator on 2014/12/20.
 */
public enum RewardFlowStatus {
    OBTAIN_SUCC(1, "获取成功"),
    OBTAIN_FAIL(2, "获取失败"),
    WITHDRAW_SUCC(3, "撤销成功"),
    WITHDRAW_FAIL(4, "撤销失败"),
    EXCHANGE_SUCC(5, "兑换成功"),
    EXCHANGE_FAIL(6, "兑换失败"),
    EXCHANGEING(7, "兑换中");


    private int status;
    private String desc;

    private RewardFlowStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static String getDescByStatus(int status) {
        for(RewardFlowStatus rewardFlowStatus : RewardFlowStatus.values()) {
            if(rewardFlowStatus.getStatus() == status) {
                return rewardFlowStatus.getDesc();
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
