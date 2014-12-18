package com.sunlights.customer.web;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.HoldRewardService;
import com.sunlights.customer.service.ObtainRewardRuleService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import com.sunlights.customer.service.impl.HoldRewardServiceImpl;
import com.sunlights.customer.service.impl.ObtainRewardRuleServiceImpl;
import com.sunlights.customer.service.rewardrules.RewardRuleFactory;
import com.sunlights.customer.service.rewardrules.query.QueryRewardHandler;
import com.sunlights.customer.service.rewardrules.query.QueryRewardHandlerImpl;
import com.sunlights.customer.vo.ActivityParamter;
import com.sunlights.customer.vo.HoldRewardVo;
import com.sunlights.customer.vo.ObtainRewardVo;
import com.sunlights.customer.vo.RewardResultVo;
import models.CustomerSession;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Result;


/**
 * Created by tangweiqun on 2014/11/19.
 */

@Transactional
public class RewardController extends ActivityBaseController {

    private HoldRewardService holdRewardService = new HoldRewardServiceImpl();

    private QueryRewardHandler queryRewardHandler = new QueryRewardHandlerImpl();

    /**
     * 获取登录客户签到可以获取的奖励数
     * @return
     */
    public Result getSingInCanObtainRewards() {
        String scene = ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE;
        ObtainRewardVo obtainRewardVo = new ObtainRewardVo();
        obtainRewardVo.setScene(scene);
        CustomerSession customerSession = getCustomerSession();
        String custNo = customerSession.getCustomerId();
        RewardResultVo rewardResultVo = queryRewardHandler.getSigninCanObtainReward(custNo, null);
        obtainRewardVo.setAlreadyGet(rewardResultVo.getAlreadyGet());
        obtainRewardVo.setNotGet(rewardResultVo.getNotGet());
        obtainRewardVo.setStatus(rewardResultVo.getStatus());

        Logger.debug("RewardResultVo = " + rewardResultVo.getReturnMessage().getSummary());

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.ACTIVITY_QUERY_SUCC), obtainRewardVo);
        return ok(messageUtil.toJson());
    }


    /**
     *  我的金豆详情
     * @return
     */
    public Result getMyRewardDetail() {
        CustomerSession customerSession = getCustomerSession();
        String custNo = customerSession.getCustomerId();

        HoldRewardVo holdRewardVo = holdRewardService.getTotalReward(custNo);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.REWARD_QUERY_SUCC), holdRewardVo);

        return ok(messageUtil.toJson());
    }

    public Result getRewardFlows() {
        //TODO
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.REWARD_FLOW_QUERY_SUCC), null);

        return ok(messageUtil.toJson());
    }
}
