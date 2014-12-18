package com.sunlights.customer.service.impl;


import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.RewardFlowDao;
import com.sunlights.customer.dal.impl.RewardFlowDaoImpl;
import com.sunlights.customer.service.*;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import com.sunlights.customer.vo.Data4ExchangeItem;
import com.sunlights.customer.vo.RewardFlowVo;
import com.sunlights.customer.vo.RewardResultVo;
import models.ActivityScene;
import models.HoldReward;
import models.RewardFlow;
import models.RewardType;
import play.Configuration;
import play.Logger;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by tangweiqun on 2014/11/19.
 */
public class RewardFlowServiceImpl implements RewardFlowService {

    private RewardFlowDao rewardFlowDao = new RewardFlowDaoImpl();

    private RewardTypeService rewardTypeService = new RewardTypeServiceImpl();



    @Override
    public void saveRewardFlow(RewardFlow rewardFlow) {
        rewardFlowDao.saveRewardFlow(rewardFlow);
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
                vo = new RewardResultVo();
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

    @Override
    public List<RewardFlowVo> getMyFlowDetail(PageVo pageVo) {
        List<RewardFlow> rewardFlows = rewardFlowDao.getMyFlowByPage(pageVo);
        List<RewardFlowVo> rewardFlowVos = new ArrayList<RewardFlowVo>();
        transRewardFlow(rewardFlows, rewardFlowVos);
        return rewardFlowVos;
    }

    private void transRewardFlow(List<RewardFlow> rewardFlows, List<RewardFlowVo> rewardFlowVos) {
        if(rewardFlows == null || rewardFlows.isEmpty()) {
            return;
        }
        RewardFlowVo rewardFlowVo = null;
        for(RewardFlow rewardFlow : rewardFlows) {
            rewardFlowVo = new RewardFlowVo();
            RewardType rewardType = rewardTypeService.findByTypeCode(rewardFlow.getRewardType());
            rewardFlowVo.setTitle(rewardFlow.getActivityTitle());
            rewardFlowVo.setCreateTime(CommonUtil.dateToString(rewardFlow.getCreateTime(), CommonUtil.DATE_FORMAT_LONG));
            if(rewardFlow.getOperatorType().equals(ActivityConstant.REWARD_FLOW_OBTAIN)) {
                rewardFlowVo.setAmount(takePrefix(rewardFlow.getRewardAmt() / rewardType.getUnit(), "+"));
            } else {
                rewardFlowVo.setAmount(takePrefix(rewardFlow.getRewardAmt() / rewardType.getUnit(), "-"));
            }
            rewardFlowVos.add(rewardFlowVo);
        }
    }

    private String takePrefix(Long originalData, String token) {
        return originalData == 0L ? "0" : token + originalData;
    }


    @Override
    public List<Data4ExchangeItem> getItemsByType(String exchangeScene, String custId, String activityType, String rewardType) {
        Logger.debug("custId = " + custId + " activityType = " + activityType + " rewardType = " + rewardType);
        RewardFlow rewardFlow = new RewardFlow();
        rewardFlow.setCustId(custId);
        rewardFlow.setActivityType(activityType);
        rewardFlow.setRewardType(rewardType);
        rewardFlow.setOperatorType(ActivityConstant.REWARD_FLOW_OBTAIN);
        List<Data4ExchangeItem> items = new ArrayList<Data4ExchangeItem>();
        Data4ExchangeItem item = new Data4ExchangeItem();
        try {
            List<RewardFlow> rewardFlows = rewardFlowDao.findByCondition(rewardFlow);
            for(RewardFlow temp : rewardFlows) {
                item.setTitle(temp.getActivityTitle());
                item.setCreateTime(CommonUtil.dateToString(temp.getCreateTime(), CommonUtil.DATE_FORMAT_LONG));
                String detaiTemplate = Configuration.root().getString(exchangeScene + "." + rewardType + "." + activityType);
                item.setDetail(MessageFormat.format(detaiTemplate, temp.getMoney()));
                items.add(item);
            }
        } catch (Exception e) {
            Logger.error("解析时间错误", e);
        }
        return items;
    }
}
