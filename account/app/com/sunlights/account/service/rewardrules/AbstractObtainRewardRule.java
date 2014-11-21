package com.sunlights.account.service.rewardrules;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.service.*;
import com.sunlights.account.service.impl.*;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import models.*;
import play.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/20.
 */
public abstract class AbstractObtainRewardRule implements IObtainRewardRule{
    private ActivityService activityService = new ActivityServiceImpl();

    private ObtainRewardRuleService obtainRewardRuleService = new ObtainRewardRuleServiceImpl();

    private RewardTypeService rewardTypeService = new RewardTypeServiceImpl();

    private RewardFlowService rewardFlowService = new RewardFlowServiceImpl();

    private HoldRewardService holdRewardService = new HoldRewardServiceImpl();

    private ExchangeRewardRuleService exchangeRewardRuleService = new ExchangeRewardRuleServiceImpl();

    @Override
    public RewardResultVo obtainReward(String custId) {
        RewardResultVo rewardResultVo = new RewardResultVo();
        //1:获取该场景下该给客户多少奖励数
        rewardResultVo = getCanObtainRewards(custId);
        Message message = rewardResultVo.getReturnMessage();
        if(!MsgCode.ACTIVITY_QUERY_SUCC.getCode().equals(message.getCode())) {
            Logger.info("获取金豆失败 ：" + message.getSummary());
            return rewardResultVo;
        }

        Long rewardAmt = rewardResultVo.getRewards();
        String rewardType = rewardResultVo.getRewardType();
        Long rewardAmtResult = calRewardAmt(rewardAmt, rewardType);
        BigDecimal moneyResult = calMoney(rewardAmtResult, rewardType);

        genRewardFlow(custId, rewardResultVo, rewardType, rewardAmtResult, moneyResult);

        message = new Message(Severity.INFO, MsgCode.OBTAIN_SUCC);
        rewardResultVo.setRewards(rewardAmtResult);
        rewardResultVo.setReturnMessage(message);
        rewardResultVo.setRewardType(rewardType);
        return rewardResultVo;
    }

    /**
     * 根据奖励类型来计算真正获取到的奖励数
     * @param originalAmt
     * @param rewardType
     * @return
     */
    public Long calRewardAmt(Long originalAmt, String rewardType) {
        RewardType rewardTypeObj = rewardTypeService.findByTypeCode(rewardType);
        Integer unit = rewardTypeObj.getUnit();
        return Long.valueOf(originalAmt.intValue() * unit);
    }

    /**
     * 根据奖励类型来计算获取到的奖励的折现
     *
     * @param rewardAmtResult
     * @param rewardType
     * @return
     */
    public BigDecimal calMoney(Long rewardAmtResult, String rewardType) {
        ExchangeRewardRule exchangeRewardRule = exchangeRewardRuleService.findByRewardType(rewardType);

        BigDecimal rate = exchangeRewardRule.getRate();
        BigDecimal money = rate.multiply(BigDecimal.valueOf(rewardAmtResult));

        return money;
    }

    /**
     * 生成获取奖励的流水，并统计奖励收益
     * @param custId
     * @param rewardResultVo
     * @param rewardType
     * @param rewardAmtResult
     * @param moneyResult
     */
    public void genRewardFlow(String custId, RewardResultVo rewardResultVo, String rewardType,Long rewardAmtResult, BigDecimal moneyResult) {
        RewardFlow flow = new RewardFlow();
        flow.setActivityId(rewardResultVo.getActivityId());
        flow.setActivityTitle(rewardResultVo.getActivityTitle());
        flow.setCustId(custId);
        flow.setRewardType(rewardResultVo.getRewardType());
        flow.setOperatorType(AccountConstant.REWARD_FLOW_OBTAIN);
        flow.setStatus(AccountConstant.REWARD_FLOW_STATUS_ALREADY_OBTAIN);
        flow.setCreateTime(new Date());
        flow.setScene(this.getScene());
        flow.setRewardAmt(rewardAmtResult);
        flow.setMoney(moneyResult);
        rewardFlowService.saveRewardFlow(flow);

        HoldReward holdReward = new HoldReward();
        holdReward.setCustId(custId);
        holdReward.setRewardType(rewardType);
        holdReward.setGetMoney(moneyResult);
        holdReward.setGetReward(rewardAmtResult);
        holdRewardService.modifyHoldReward(custId, rewardType, moneyResult, rewardAmtResult);
    }



    @Override
    public RewardResultVo getCanObtainRewards(String custId) {
        RewardResultVo vo = new RewardResultVo();
        Message message = null;
        vo = validate(custId);
        message = vo.getReturnMessage();
        if(!MsgCode.ACTIVITY_QUERY_SUCC.getCode().equals(message.getCode())) {
            Logger.info("签到失败 ：" + message.getSummary());
            vo.setCanNotObtain(false);
        }
        //1：根据活动场景获取活动主键
        List<Activity> activities = activityService.getActivityByScene(this.getScene());
        if(activities == null || activities.isEmpty()) {
            message = new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE);
            vo.setReturnMessage(message);
            return vo;
        }
        //2:根据活动主键获取该活动下获取奖励的所有规则，将所有规则中的应发奖励相加便是该场景下可以获取的奖励数
        List<ObtainRewardRule> obtainRewardRules = obtainRewardRuleService.getByActivityId(activities.get(0).getId());
        if(obtainRewardRules == null || obtainRewardRules.isEmpty()) {
            message = new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE);
            vo.setReturnMessage(message);
            return vo;
        }
        vo.setRewards(obtainRewardRules.get(0).getShouldReward());
        if(vo.isCanNotObtain()) {
            message = new Message(Severity.INFO, MsgCode.ACTIVITY_QUERY_SUCC);
            vo.setReturnMessage(message);
            vo.setScene(this.getScene());
            vo.setActivityId(activities.get(0).getId());
            vo.setActivityTitle(activities.get(0).getTitle());
            vo.setRewardType(obtainRewardRules.get(0).getRewardType());
        }
        return vo;
    }

    /**
     * 检验--供子类特殊化
     * 默认是返回成功
     * @param custId
     * @return
     */
    public RewardResultVo validate(String custId) {
        RewardResultVo vo = new RewardResultVo();
        Message message = null;
        message = new Message(Severity.INFO, MsgCode.ACTIVITY_QUERY_SUCC);
        vo.setCanNotObtain(true);
        vo.setReturnMessage(message);
        return vo;
    }
}
