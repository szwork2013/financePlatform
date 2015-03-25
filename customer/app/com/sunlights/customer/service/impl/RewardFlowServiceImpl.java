package com.sunlights.customer.service.impl;


import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.RewardFlowDao;
import com.sunlights.customer.dal.impl.RewardFlowDaoImpl;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.RewardTypeService;
import com.sunlights.customer.service.rewardrules.RewardFlowStatus;
import com.sunlights.customer.vo.Data4ExchangeItem;
import com.sunlights.customer.vo.RewardFlowVo;
import com.sunlights.customer.vo.RewardResultVo;
import models.RewardFlow;
import models.RewardType;
import play.Configuration;
import play.Logger;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by tangweiqun on 2014/11/19.
 */
public class RewardFlowServiceImpl implements RewardFlowService {

    private RewardFlowDao rewardFlowDao = new RewardFlowDaoImpl();

    private RewardTypeService rewardTypeService = ActivityServiceFactory.getRewardTypeService();


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
            if (result != null && result.size() > 0) {
                temp = result.get(0);
            }
            if (temp != null) {
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
        if (rewardFlows == null || rewardFlows.isEmpty()) {
            return;
        }
        RewardFlowVo rewardFlowVo = null;
        for (RewardFlow rewardFlow : rewardFlows) {
            rewardFlowVo = new RewardFlowVo();
            RewardType rewardType = rewardTypeService.findByTypeCode(rewardFlow.getRewardType());
            rewardFlowVo.setTitle(rewardFlow.getActivityTitle());
            rewardFlowVo.setCreateTime(CommonUtil.dateToString(rewardFlow.getCreateTime(), CommonUtil.DATE_FORMAT_LONG));
            if (rewardFlow.getOperatorType().equals(ActivityConstant.REWARD_FLOW_OBTAIN)) {
                rewardFlowVo.setAmount(takePrefix(BigDecimal.valueOf(rewardFlow.getRewardAmt()).divide(BigDecimal.valueOf(rewardType.getUnit())), "+"));
            } else {
                if (ActivityConstant.ACTIVITY_EXCHANGE_BEAN_SCENE_CODE.equals(rewardFlow.getScene())) {
                    rewardFlowVo.setTitle(MessageFormat.format("金豆兑换{0}元话费", rewardFlow.getMoney()));
                }
                rewardFlowVo.setAmount(takePrefix(BigDecimal.valueOf(rewardFlow.getRewardAmt()).divide(BigDecimal.valueOf(rewardType.getUnit())), "-"));
            }
            rewardFlowVo.setStatus(RewardFlowStatus.getDescByStatus(rewardFlow.getStatus()));
            rewardFlowVo.setRewardType(ActivityConstant.REWARD_TYPE_REDPACKET.equals(rewardFlow.getRewardType()) ? "1" : "0");
            rewardFlowVos.add(rewardFlowVo);
        }
    }

    private String takePrefix(BigDecimal originalData, String token) {
        return originalData.equals(BigDecimal.ZERO) ? "0" : token + originalData;
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
        Data4ExchangeItem item = null;
        try {
            List<RewardFlow> rewardFlows = rewardFlowDao.findByCondition(rewardFlow);
            for (RewardFlow temp : rewardFlows) {
                item = new Data4ExchangeItem();
                item.setTitle(temp.getActivityTitle());
                item.setCreateTime(CommonUtil.dateToString(temp.getCreateTime(), CommonUtil.DATE_FORMAT_LONG));

                //TODO 这样的话不能将多个参数替换
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
