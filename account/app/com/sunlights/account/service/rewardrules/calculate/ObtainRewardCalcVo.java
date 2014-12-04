package com.sunlights.account.service.rewardrules.calculate;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2014/12/2.
 */
public class ObtainRewardCalcVo {
    /**
     * 计算出最终的奖励数
     */
    private Long rewardAmtResult;

    /**
     * 计算出最终奖励数的折现
     */
    private BigDecimal rewardMoneyResult;

    public Long getRewardAmtResult() {
        return rewardAmtResult;
    }

    public void setRewardAmtResult(Long rewardAmtResult) {
        this.rewardAmtResult = rewardAmtResult;
    }

    public BigDecimal getRewardMoneyResult() {
        return rewardMoneyResult;
    }

    public void setRewardMoneyResult(BigDecimal rewardMoneyResult) {
        this.rewardMoneyResult = rewardMoneyResult;
    }
}
