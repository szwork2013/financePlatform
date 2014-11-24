package com.sunlights.account.service.impl;

import com.google.common.collect.Lists;
import com.sunlights.account.AccountConstant;
import com.sunlights.account.dal.HoldRewardDao;
import com.sunlights.account.dal.impl.HoldRewardDaoImpl;
import com.sunlights.account.service.HoldRewardService;
import com.sunlights.account.service.RewardFlowService;
import com.sunlights.account.vo.HoldRewardVo;
import com.sunlights.account.vo.RewardFlowVo;
import com.sunlights.common.utils.CommonUtil;
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
            holdRewardVo.setObtain("0");
            holdRewardVo.setExchange("0");
            holdRewardVo.setTotalCash("0.00");
            return holdRewardVo;
        }

        transf(holdReward, holdRewardVo);

        List<RewardFlowVo> list = Lists.newArrayList();
        List<RewardFlow> rewardFlows = rewardFlowService.findByCustIdAndRewardType(custId, rewardType);

        transRewardFlow(rewardFlows, list);

        holdRewardVo.setRewardFlowVos(list);

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
            if(rewardFlow.getOperatorType().equals(AccountConstant.REWARD_FLOW_OBTAIN)) {
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
        holdRewardVo.setObtain(takePrefix(holdReward.getGetReward(),"+"));
        holdRewardVo.setExchange(takePrefix((holdReward.getGetReward() - holdReward.getHoldReward()), "-"));
        holdRewardVo.setTotalCash(holdReward.getHoldMoney().toString());
    }

}
