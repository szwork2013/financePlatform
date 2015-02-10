package com.sunlights.customer.dal;

import models.ObtainRewardRule;
import models.Trade;

import java.util.List;

/**
 * Created by Administrator on 2014/11/19.
 */
public interface ObtainRewardRuleDao {

    public List<ObtainRewardRule> getByActivityId(Long activityId);

    public ObtainRewardRule findRewardRuleByActivityId(Long activityId);

    public List<Trade> getTradesByCustId(String custId);

    /**
     * 查询当前 有效的 首次购买  的活动 规则
     * @return
     */
    public List<ObtainRewardRule> findFirstPurchaseEffective(Long activityId);

}
