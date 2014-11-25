package com.sunlights.account.service.rewardrules;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.vo.ObtainRewardVo;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import play.Logger;

/**
 * Created by tangweiqun on 2014/11/25.
 *
 * 提供给外部模块来调用的服务
 *
 */
public class ObtainRewardFacade {

    public ObtainRewardVo obtainReward(String custNo, String scene, Long activityId) {

        IObtainRewardRule iObtainRewardRule = RewardRuleFactory.getIObtainRuleHandler(scene);
        ObtainRewardVo obtainRewardVo = new ObtainRewardVo();
        if(iObtainRewardRule == null) {
            Logger.info("还没有配置签到的场景");
            obtainRewardVo.setScene(scene);
            obtainRewardVo.setStatus(AccountConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            obtainRewardVo.setAlreadyGet(0L);
            obtainRewardVo.setNotGet(0L);
            return obtainRewardVo;
        }
        //4:处理获取奖励
        RewardResultVo rewardResultVo = iObtainRewardRule.obtainReward(custNo, activityId);

        obtainRewardVo.setScene(scene);
        obtainRewardVo.setStatus(rewardResultVo.getStatus());
        obtainRewardVo.setAlreadyGet(rewardResultVo.getAlreadyGet());
        obtainRewardVo.setNotGet(rewardResultVo.getNotGet());

        return obtainRewardVo;
    }

}
