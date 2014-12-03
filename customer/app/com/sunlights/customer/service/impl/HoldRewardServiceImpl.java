package com.sunlights.customer.service.impl;

import com.google.common.collect.Lists;

import com.sunlights.common.utils.CommonUtil;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.HoldRewardDao;
import com.sunlights.customer.dal.impl.HoldRewardDaoImpl;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import com.sunlights.customer.vo.HoldRewardVo;
import com.sunlights.customer.vo.RewardFlowVo;
import models.HoldReward;
import models.RewardFlow;
import play.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class HoldRewardServiceImpl implements HoldRewardService {

    private HoldRewardDao holdRewardDao = new HoldRewardDaoImpl();

    private RewardFlowService rewardFlowService = new RewardFlowServiceImpl();

    @Override
    public void modifyHoldReward(String custId, String rewardType, BigDecimal money, Long rewardAmt) {
        HoldReward oldHoldReward = holdRewardDao.findByCustIdAndRewardType(custId, rewardType);
        if(oldHoldReward == null) {
            HoldReward newHoldReward = new HoldReward();
            newHoldReward.setCustId(custId);
            newHoldReward.setRewardType(rewardType);
            newHoldReward.setGetMoney(money);
            newHoldReward.setGetReward(rewardAmt);
            newHoldReward.setCreateTime(new Date());
            newHoldReward.setUpdateTime(new Date());
            newHoldReward.setHoldMoney(money);
            newHoldReward.setHoldReward(rewardAmt);
            newHoldReward.setFrozenReward(Long.valueOf(0));
            newHoldReward.setFrozenMoney(BigDecimal.ZERO);
            holdRewardDao.doInsert(newHoldReward);
        } else {
            oldHoldReward.setGetMoney(oldHoldReward.getGetMoney().add(money));
            oldHoldReward.setGetReward(oldHoldReward.getGetReward() + rewardAmt);
            oldHoldReward.setHoldMoney(oldHoldReward.getHoldMoney().add(money));
            oldHoldReward.setHoldReward(oldHoldReward.getHoldReward() + rewardAmt);
            oldHoldReward.setUpdateTime(new Date());
            holdRewardDao.doUpdate(oldHoldReward);
        }
        Logger.info("修改持有奖励");
    }

    @Override
    public Long getHoldRewardByCustId(String custId, String rewardType) {
        HoldReward holdReward = holdRewardDao.findByCustIdAndRewardType(custId, rewardType);
        if (holdReward == null) {
            return 0L;
        }
        return holdReward.getHoldReward();
    }

    @Override
    public HoldRewardVo getMyRewardDetail(String custId, String rewardType) {
        HoldReward holdReward = holdRewardDao.findByCustIdAndRewardType(custId, rewardType);
        HoldRewardVo holdRewardVo = new HoldRewardVo();
        if(holdReward == null) {
            holdRewardVo.setTotalReward("0");
            holdRewardVo.setGots("0");
            holdRewardVo.setPayed("0");
            holdRewardVo.setTotalCash("0.00");
            return holdRewardVo;
        }

        transf(holdReward, holdRewardVo);

        List<RewardFlowVo> list = Lists.newArrayList();
        List<RewardFlow> rewardFlows = rewardFlowService.findByCustIdAndRewardType(custId, rewardType);

        transRewardFlow(rewardFlows, list);

        holdRewardVo.setRecords(list);

        return holdRewardVo;
    }

    public void transRewardFlow(List<RewardFlow> rewardFlows, List<RewardFlowVo> rewardFlowVos) {
        if(rewardFlows == null || rewardFlows.isEmpty()) {
            return;
        }
        RewardFlowVo rewardFlowVo = null;
        for(RewardFlow rewardFlow : rewardFlows) {
            rewardFlowVo = new RewardFlowVo();
            rewardFlowVo.setTitle(rewardFlow.getActivityTitle());
            rewardFlowVo.setCreateTime(CommonUtil.dateToString(rewardFlow.getCreateTime(), CommonUtil.DATE_FORMAT_LONG));
            if(rewardFlow.getOperatorType().equals(ActivityConstant.REWARD_FLOW_OBTAIN)) {
                rewardFlowVo.setAmount(takePrefix(rewardFlow.getRewardAmt(), "+"));
            } else {
                rewardFlowVo.setAmount(takePrefix(rewardFlow.getRewardAmt(), "-"));
            }
            rewardFlowVos.add(rewardFlowVo);
        }
    }
    public String takePrefix(Long originalData, String token) {
        return originalData == 0L ? "0" : token + originalData;
    }



    public void transf(HoldReward holdReward, HoldRewardVo holdRewardVo) {
        holdRewardVo.setTotalReward(takePrefix(holdReward.getHoldReward(),"+"));
        holdRewardVo.setGots(takePrefix(holdReward.getGetReward(),"+"));
        holdRewardVo.setPayed(takePrefix((holdReward.getGetReward() - holdReward.getHoldReward()), "-"));
        holdRewardVo.setTotalCash(holdReward.getHoldMoney().toString());
    }

    @Override
    public void genRewardFlow(RewardFlowRecordVo rewardFlowRecordVo) {
        RewardFlow flow = new RewardFlow();
        flow.setActivityId(rewardFlowRecordVo.getActivityId());
        flow.setActivityTitle(rewardFlowRecordVo.getActivityTitle());
        flow.setCustId(rewardFlowRecordVo.getCustId());
        flow.setRewardType(rewardFlowRecordVo.getRewardType());
        flow.setOperatorType(rewardFlowRecordVo.getOperatorType());
        flow.setStatus(ActivityConstant.REWARD_FLOW_STATUS_ALREADY_OBTAIN);
        flow.setCreateTime(new Date());
        flow.setScene(rewardFlowRecordVo.getScene());
        flow.setRewardAmt(rewardFlowRecordVo.getRewardAmtResult());
        flow.setMoney(rewardFlowRecordVo.getMoneyResult());
        rewardFlowService.saveRewardFlow(flow);
        if (rewardFlowRecordVo.getOperatorType().equals(ActivityConstant.REWARD_FLOW_OBTAIN)) {
            HoldReward holdReward = new HoldReward();
            holdReward.setCustId(rewardFlowRecordVo.getCustId());
            holdReward.setRewardType(rewardFlowRecordVo.getRewardType());
            holdReward.setGetMoney(rewardFlowRecordVo.getMoneyResult());
            holdReward.setGetReward(rewardFlowRecordVo.getRewardAmtResult());
            modifyHoldReward(rewardFlowRecordVo.getCustId(), rewardFlowRecordVo.getRewardType(), rewardFlowRecordVo.getMoneyResult(), rewardFlowRecordVo.getRewardAmtResult());
        }
    }

    @Override
    public void frozenReward(String custId, String rewardType, Long frozenAmt) {

    }
}
