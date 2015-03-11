package com.sunlights.customer.service.rewardrules.query;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.rewardrules.obtain.ObtainRuleGainHandler;
import com.sunlights.customer.service.rewardrules.obtain.SigninObtainValideHandler;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;
import com.sunlights.customer.vo.RewardResultVo;
import play.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/12/5.
 */
public class QueryRewardHandlerImpl implements QueryRewardHandler {

    @Override
    public RewardResultVo getSigninCanObtainReward(String custId, Long activityId) {
        ActivityRequestVo requestVo = new ActivityRequestVo();
        requestVo.setCustId(custId);
        requestVo.setActivityId(activityId);
        requestVo.setScene(ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE);

        ActivityResponseVo responseVo = new ActivityResponseVo();

        try {
            //判断是否还可以签到
            new SigninObtainValideHandler().obtainInternal(requestVo, responseVo);
        } catch (Exception e) {
            Logger.error("查询今日签到信息系统错误", e);
        }
        RewardResultVo rewardResultVo = new RewardResultVo();

        if (responseVo.getMessage().getCode().equals(MsgCode.OPERATE_SUCCESS.getCode())) {
            rewardResultVo.setReturnMessage(new Message(Severity.INFO, MsgCode.ACTIVITY_QUERY_SUCC));
            rewardResultVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_NOMAL);
            rewardResultVo = judge(requestVo, responseVo, rewardResultVo, true);
        } else {
            rewardResultVo.setReturnMessage(new Message(Severity.INFO, MsgCode.ALREADY_SIGN));
            rewardResultVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            rewardResultVo = judge(requestVo, responseVo, rewardResultVo, false);
        }

        return rewardResultVo;
    }

    private RewardResultVo judge(ActivityRequestVo requestVo, ActivityResponseVo responseVo, RewardResultVo rewardResultVo, boolean isSucc) {
        try {
            //判断是否配置了规则  TODO 不能这么玩呀，代码很难看懂， 等待后面重构
            new ObtainRuleGainHandler().obtainInternal(requestVo, responseVo);
        } catch (Exception e) {
            Logger.error("查询今日签到信息系统错误", e);
        }
        if (responseVo.getMessage().getCode().equals(MsgCode.NOT_CONFIG_ACTIVITY_SCENE.getCode())) {
            rewardResultVo.setReturnMessage(new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE));
            rewardResultVo.setNotGet(0L);
            rewardResultVo.setAlreadyGet(0L);
            rewardResultVo.setStatus(null);//活动被关闭的时候返回空
        } else {
            Map<Long, List<ObtainRewardRuleVo>> ruleMap = requestVo.getObtainRewardRuleMap();
            if (ruleMap == null || ruleMap.isEmpty()) {
                rewardResultVo.setReturnMessage(new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE));
                rewardResultVo.setNotGet(0L);
                rewardResultVo.setAlreadyGet(0L);
                rewardResultVo.setStatus(null);//活动被关闭的时候返回空
            } else {
                Long canGet = 0L;
                for (Map.Entry<Long, List<ObtainRewardRuleVo>> entry : ruleMap.entrySet()) {
                    for (ObtainRewardRuleVo obtainRewardRuleVo : entry.getValue()) {
                        canGet += obtainRewardRuleVo.getShouldReward();
                    }
                }
                if (isSucc) {
                    rewardResultVo.setNotGet(canGet);
                    rewardResultVo.setAlreadyGet(0L);
                } else {
                    rewardResultVo.setNotGet(0L);
                    rewardResultVo.setAlreadyGet(canGet);
                    rewardResultVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
                }
            }
        }
        return rewardResultVo;
    }
}
