package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import play.Logger;

/**
 * 签到场景获取奖励的处理链的开头
 * 后面的功能链上的处理类包括：
 * 1：@see com.sunlights.account.service.rewardrules.obtain.RegisterObtainValideHandler 确定购买活动是否是首次购买
 * 2：@see com.sunlights.account.service.rewardrules.obtain.ObtainRuleGainHandler 获取参加的活动及其获取奖励规则
 * 3：@see com.sunlights.account.service.rewardrules.obtain.RewardFlowHandler 生成奖励流水（分邀请人和被邀请人）
 * 4：@see com.sunlights.account.service.rewardrules.obtain.CustJoinActivityHandler 客户参加活动后的总结
 * 5：@see com.sunlights.account.service.rewardrules.obtain.ResultAssignHandler 获取奖励成功后结果赋值
 *
 * Created by tangweiqun on 2014/12/2.
 */
public class SigninObtainRuleHandler extends AbstractObtainRuleHandler {

    @Override
    public void obtain(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        Logger.debug("签到送奖励开始 requestVo = " + requestVo);

        setNextHandler(
                new SigninObtainValideHandler(
                        new ObtainRuleGainHandler(
                                new RewardFlowHandler(
                                        new SignInCustJoinActivityHandler(
                                                new OldResultAssignHandler())))));
        getNextHandler().obtain(requestVo, responseVo);

        Logger.debug("签到送奖励结束 responseVo = " + responseVo);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

    }

    @Override
    public String toString() {
        return "SigninObtainRuleHandler";
    }
}
