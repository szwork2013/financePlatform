package com.sunlights.customer.dal;

import com.sunlights.common.vo.PageVo;
import models.RewardFlow;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public interface RewardFlowDao {

    public RewardFlow saveRewardFlow(RewardFlow rewardFlow);

    public List<RewardFlow> findByCondition(RewardFlow rewardFlow, String startDate, String endDate, boolean isAsc) throws Exception ;

    public RewardFlow findOneByCondition(RewardFlow rewardFlow, String startDate, String endDate) throws Exception ;

    public List<RewardFlow> findByCondition(RewardFlow rewardFlow) throws Exception ;


    public List<RewardFlow> getMyFlowByPage(PageVo pageVo);

    public List<RewardFlow> getByType(String custId, String activityType, String rewardType);

}
