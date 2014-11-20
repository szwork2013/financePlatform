package com.sunlights.account.vo;

import java.io.Serializable;

/**
 * Created by tangweiqun on 2014/11/20.
 */
public class RewardFlowVo implements Serializable{

    private String title;

    private String amount;

    private String createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
