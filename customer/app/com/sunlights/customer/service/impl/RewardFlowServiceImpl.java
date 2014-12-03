package com.sunlights.customer.service.impl;


import com.sunlights.common.utils.CommonUtil;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.RewardFlowDao;
import com.sunlights.customer.dal.impl.RewardFlowDaoImpl;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import com.sunlights.customer.vo.RewardResultVo;
import models.HoldReward;
import models.RewardFlow;
import play.Logger;

import java.util.Date;
import java.util.List;


/**
 * Created by tangweiqun on 2014/11/19.
 */
public class RewardFlowServiceImpl implements RewardFlowService {

    private RewardFlowDao rewardFlowDao = new RewardFlowDaoImpl();


    @Override
    public void saveRewardFlow(RewardFlow rewardFlow) {
        rewardFlowDao.saveRewardFlow(rewardFlow);
        Logger.info("保存资金流水");
    }

    @Override
    public RewardFlow findTodayFlowByCustIdAndScene(String custId, String scene) {
        String startDate = null;
        String endDate = null;
        try {
            String dateStr = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_SHORT);
            startDate = dateStr + " 00:00:00";
            endDate = dateStr + " 23:59:59";
            RewardFlow paramter = new RewardFlow();
            paramter.setCustId(custId);
            paramter.setScene(scene);
            RewardFlow rewardFlow = rewardFlowDao.findOneByCondition(paramter, startDate, endDate);

            return rewardFlow;
        } catch (Exception e) {
            Logger.error("获取当天奖励流水错误", e);
        }
        return null;
    }

    @Override
    public List<RewardFlow> findByCustIdAndRewardType(String custId, String rewardType) {
        try {
            RewardFlow rewardFlow = new RewardFlow();
            rewardFlow.setCustId(custId);
            rewardFlow.setRewardType(rewardType);
            return rewardFlowDao.findByCondition(rewardFlow);
        } catch (Exception e) {
            Logger.error("查询奖励流水错误", e);
        }
        return null;
    }

    @Override
    public RewardResultVo getLastObtainRewars(String custId, String scene) {
        try {
            RewardResultVo vo = null;

            RewardFlow rewardFlow = new RewardFlow();
            rewardFlow.setCustId(custId);
            rewardFlow.setScene(scene);
            List<RewardFlow> result = rewardFlowDao.findByCondition(rewardFlow);
            RewardFlow temp = null;
            if(result != null && result.size() > 0) {
                temp = result.get(0);
            }
            if(temp != null) {
                vo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_NOMAL);
                vo.setAlreadyGet(temp.getRewardAmt());
                vo.setNotGet(0L);
            }
            return vo;
        } catch (Exception e) {
            Logger.error("获取客户最近金豆收益错误", e);
        }

        return null;
    }


}
