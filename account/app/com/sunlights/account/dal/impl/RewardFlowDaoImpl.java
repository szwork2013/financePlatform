package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.RewardFlowDao;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.CommonUtil;
import models.RewardFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class RewardFlowDaoImpl extends EntityBaseDao implements RewardFlowDao {

    @Override
    public void saveRewardFlow(RewardFlow rewardFlow) {
        create(rewardFlow);
    }

    @Override
    public List<RewardFlow> findByCondition(RewardFlow rewardFlow, String startDate, String endDate, boolean isAsc) throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select h from RewardFlow h where 1 =1")
                .append("/~  and h.custId  = {custId} ~/")
                .append("/~  and h.rewardType  = {rewardType} ~/")
                .append("/~  and h.scene  = {scene} ~/")
                .append("/~  and h.createTime  >= {startDate} ~/")
                .append("/~  and h.createTime  <= {endDate} ~/")
                .append(" order by h.createTime ");
        if(!isAsc) {
            jpql.append(" desc ");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_custId", rewardFlow.getCustId());
        params.put("EQS_scene", rewardFlow.getScene());
        params.put("EQS_rewardType", rewardFlow.getRewardType());
        params.put("GED_startDate", startDate == null ? null : CommonUtil.stringToDate(startDate, CommonUtil.DATE_FORMAT_LONG));
        params.put("LED_endDate", endDate == null ? null : CommonUtil.stringToDate(endDate, CommonUtil.DATE_FORMAT_LONG));

        return findByMap(jpql.toString(), params);
    }

    @Override
    public RewardFlow findOneByCondition(RewardFlow rewardFlow, String startDate, String endDate) throws Exception {
        List<RewardFlow> rewardFlows = findByCondition(rewardFlow, startDate, endDate, true);
        if(rewardFlows == null || rewardFlows.isEmpty()) {
            return null;
        }
        return rewardFlows.get(0);
    }

    @Override
    public List<RewardFlow> findByCondition(RewardFlow rewardFlow) throws Exception {
        return findByCondition(rewardFlow, null, null, false);
    }
}
