package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import play.Logger;

/**
 * 购买场景获取奖励的处理链入口
 * 后面的功能链上的处理类包括：
 * 1：@see com.sunlights.account.service.rewardrules.obtain.PurchaseObtainValideHandler 确定购买活动是否是首次购买
 * 2：@see com.sunlights.account.service.rewardrules.obtain.ReCommendHandler 获取当前客户的推荐人
 * 3：@see com.sunlights.account.service.rewardrules.obtain.ObtainRuleGainHandler 获取参加的活动及其获取奖励规则
 * 4：@see com.sunlights.account.service.rewardrules.obtain.RewardFlowHandler 生成奖励流水（分邀请人和被邀请人）
 * 5：@see com.sunlights.account.service.rewardrules.obtain.CustJoinActivityHandler 客户参加活动后的总结
 * 6：@see com.sunlights.account.service.rewardrules.obtain.ResultAssignHandler 获取奖励成功后结果赋值
 * <p/>
 * Created by tangweiqun on 2014/12/2.
 */
public class PurchaseObtainRuleHandler extends AbstractObtainRuleHandler {

    @Override
    public void obtain(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        Logger.debug("申购送奖励开始 requestVo = " + requestVo);
        PurchaseObtainValideHandler purchaseObtainValideHandler = new PurchaseObtainValideHandler();
        ObtainRuleGainHandler obtainRuleGainHandler = new ObtainRuleGainHandler();

        FilterInvalidGainRuleHandler countAttendActivityNumValideHandler = new FilterInvalidGainRuleHandler();

        ReCommendHandler reCommendHandler = new ReCommendHandler();
        //ValidBankCardHandler validBankCardHandler = new ValidBankCardHandler();
        RewardFlowHandler rewardFlowHandler = new RewardFlowHandler();
        RewardAccountHandler rewardAccountHandler = new RewardAccountHandler();
        CustJoinActivityHandler custJoinActivityHandler = new CustJoinActivityHandler();
        ResultAssignHandler resultAssignHandler = new ResultAssignHandler();
        PurchaseObtainSendMessageHandler purchaseObtainSendMessageHandler = new PurchaseObtainSendMessageHandler();

        setNextHandler(purchaseObtainValideHandler)
                .setNextHandler(obtainRuleGainHandler)
                .setNextHandler(countAttendActivityNumValideHandler)
                .setNextHandler(reCommendHandler)
                        //.setNextHandler(validBankCardHandler)
                .setNextHandler(rewardFlowHandler)
                .setNextHandler(rewardAccountHandler)
                .setNextHandler(custJoinActivityHandler)
                .setNextHandler(resultAssignHandler)
                .setNextHandler(purchaseObtainSendMessageHandler);

        getNextHandler().obtain(requestVo, responseVo);

        Logger.debug("申购送奖励结束 responseVo = " + responseVo);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

    }

    @Override
    public String toString() {
        return "PurchaseObtainRuleHandler";
    }


}
