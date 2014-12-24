package com.sunlights.customer.service.impl;

import com.google.common.collect.Lists;

import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.HoldRewardDao;
import com.sunlights.customer.dal.RewardTypeDao;
import com.sunlights.customer.dal.impl.HoldRewardDaoImpl;
import com.sunlights.customer.dal.impl.RewardTypeDaoImpl;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.RewardTypeService;
import com.sunlights.customer.service.rewardrules.RewardFlowStatus;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import com.sunlights.customer.vo.HoldRewardVo;
import com.sunlights.customer.vo.RewardFlowVo;
import models.HoldReward;
import models.RewardFlow;
import models.RewardType;
import play.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class HoldRewardServiceImpl implements HoldRewardService {

    private HoldRewardDao holdRewardDao = new HoldRewardDaoImpl();

    private RewardTypeService rewardTypeService = new RewardTypeServiceImpl();

    private ActivityService activityService = new ActivityServiceImpl();

    private RewardFlowService rewardFlowService = new RewardFlowServiceImpl();

    @Override
    public void modifyHoldReward(String custId, String rewardType,String activityType, BigDecimal money, Long rewardAmt) {
        //TODO 需要上锁--数据库的行级排它锁
        HoldReward oldHoldReward = holdRewardDao.findByCondition(custId, rewardType, activityType, true);
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
            newHoldReward.setActivityType(activityType);
            holdRewardDao.doInsert(newHoldReward);
        } else {
            oldHoldReward.setGetMoney(oldHoldReward.getGetMoney().add(money));
            oldHoldReward.setGetReward(oldHoldReward.getGetReward() + rewardAmt);
            oldHoldReward.setHoldMoney(oldHoldReward.getHoldMoney().add(money));
            oldHoldReward.setHoldReward(oldHoldReward.getHoldReward() + rewardAmt);
            oldHoldReward.setUpdateTime(new Date());
            holdRewardDao.doUpdate(oldHoldReward);
        }
        Logger.info("修改持有奖励成功");
    }

    @Override
    public Long getHoldRewardByCustId(String custId, String rewardType) {
        HoldReward holdReward = holdRewardDao.findByCustIdAndRewardType(custId, rewardType);
        if (holdReward == null) {
            return 0L;
        }
        return holdReward.getHoldReward() - holdReward.getFrozenReward();
    }

    @Override
    public HoldRewardVo getMyRewardDetail(String custId, String rewardType) {
        HoldReward holdReward = holdRewardDao.findByCustIdAndRewardType(custId, rewardType);
        RewardType rewardTypeModel = rewardTypeService.findByTypeCode(rewardType);
        HoldRewardVo holdRewardVo = new HoldRewardVo();
        if(holdReward == null) {
            holdRewardVo.setTotalReward("0");

            holdRewardVo.setTotalCash("0.00");
            return holdRewardVo;
        }

        transf(holdReward, holdRewardVo);

        holdRewardVo.setRuleUrl(activityService.getFileFuleUrl(rewardTypeModel.getRuleUrl(), "activity.html5Path"));

        List<RewardFlowVo> list = Lists.newArrayList();
        List<RewardFlow> rewardFlows = rewardFlowService.findByCustIdAndRewardType(custId, rewardType);

        transRewardFlow(rewardFlows, list);



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
        holdRewardVo.setTotalReward(takePrefix(holdReward.getHoldReward() - holdReward.getFrozenReward(),"+"));

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
        //TODO 临时解决方案，等待产品提供详细的解决方案
        if (rewardFlowRecordVo.getOperatorType().equals(ActivityConstant.REWARD_FLOW_OBTAIN)) {
            flow.setStatus(RewardFlowStatus.OBTAIN_SUCC.getStatus());
        } else {
            flow.setStatus(RewardFlowStatus.EXCHANGEING.getStatus());
        }
        flow.setCreateTime(new Date());
        flow.setScene(rewardFlowRecordVo.getScene());
        flow.setRewardAmt(rewardFlowRecordVo.getRewardAmtResult());
        flow.setMoney(rewardFlowRecordVo.getMoneyResult());
        flow.setActivityType(rewardFlowRecordVo.getActivityType());
        rewardFlowService.saveRewardFlow(flow);
        if (rewardFlowRecordVo.getOperatorType().equals(ActivityConstant.REWARD_FLOW_OBTAIN)) {
            HoldReward holdReward = new HoldReward();
            holdReward.setCustId(rewardFlowRecordVo.getCustId());
            holdReward.setRewardType(rewardFlowRecordVo.getRewardType());
            holdReward.setGetMoney(rewardFlowRecordVo.getMoneyResult());
            holdReward.setGetReward(rewardFlowRecordVo.getRewardAmtResult());
            modifyHoldReward(rewardFlowRecordVo.getCustId(), rewardFlowRecordVo.getRewardType(),rewardFlowRecordVo.getActivityType(), rewardFlowRecordVo.getMoneyResult(), rewardFlowRecordVo.getRewardAmtResult());
        }
    }

    @Override
    public void frozenReward(String custId, String rewardType, String activityType, Long frozenAmt, BigDecimal exchangeMoney) {
        Logger.debug("冻结奖励custId = " + custId + " rewardType = " + rewardType + " frozenAmt = " + frozenAmt + " exchangeMoney = " + exchangeMoney + " activityType = " + activityType);
        //TODO 需要上锁
        List<HoldReward> list = holdRewardDao.findByCustIdAndRewardType(custId, rewardType, activityType, true);
        if(list == null || list.isEmpty()) {
            return;
        }
        for(HoldReward holdReward : list) {
            if(holdReward.getHoldReward() <= frozenAmt) {
                holdReward.setFrozenReward(holdReward.getFrozenReward() + holdReward.getHoldReward());
                holdReward.setFrozenMoney(holdReward.getFrozenMoney().add(holdReward.getHoldMoney()));
                frozenAmt = frozenAmt - holdReward.getHoldReward();
            } else {
                holdReward.setFrozenReward(holdReward.getFrozenReward() + frozenAmt);
                holdReward.setFrozenMoney(holdReward.getFrozenMoney().add(exchangeMoney));
                frozenAmt = holdReward.getHoldReward() - frozenAmt;
                exchangeMoney = holdReward.getHoldMoney().subtract(exchangeMoney);
            }
            holdRewardDao.doUpdate(holdReward);
        }
    }

    @Override
    public HoldRewardVo getTotalReward(String custId) {
        List<HoldReward> holdRewards = holdRewardDao.findListByCustIdAndRewardType(custId, null, false);
        Long totalGoldBean = Long.valueOf(0);
        BigDecimal goldBeanCash = BigDecimal.ZERO;
        BigDecimal redPacket = BigDecimal.ZERO;
        HoldRewardVo holdRewardVo = new HoldRewardVo();

        for(HoldReward holdReward : holdRewards) {
            if(ActivityConstant.REWARD_TYPE_JINDOU.equals(holdReward.getRewardType())) {
                totalGoldBean += (holdReward.getHoldReward() - holdReward.getFrozenReward());
                goldBeanCash = goldBeanCash.add(holdReward.getHoldMoney().subtract(holdReward.getFrozenMoney()));
            }
            if(ActivityConstant.REWARD_TYPE_REDPACKET.equals(holdReward.getRewardType())) {
                redPacket = redPacket.add(holdReward.getHoldMoney().subtract(holdReward.getFrozenMoney()));
            }
        }
        RewardType rewardTypeModel = rewardTypeService.findByTypeCode(ActivityConstant.REWARD_TYPE_JINDOU);


        holdRewardVo.setRedPacket(redPacket.equals(BigDecimal.ZERO) ? "0.00" : redPacket.toString());
        holdRewardVo.setTotalCash(goldBeanCash.equals(BigDecimal.ZERO) ? "0.00" : goldBeanCash.toString());
        holdRewardVo.setTotalReward(totalGoldBean.toString());
        holdRewardVo.setRuleUrl(activityService.getFileFuleUrl(rewardTypeModel.getRuleUrl(), "activity.html5Path"));

        return holdRewardVo;
    }


}
