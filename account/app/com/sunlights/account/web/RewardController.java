package com.sunlights.account.web;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.service.ActivityService;
import com.sunlights.account.service.HoldRewardService;
import com.sunlights.account.service.ObtainRewardRuleService;
import com.sunlights.account.service.impl.ActivityServiceImpl;
import com.sunlights.account.service.impl.HoldRewardServiceImpl;
import com.sunlights.account.service.impl.ObtainRewardRuleServiceImpl;
import com.sunlights.account.service.rewardrules.IObtainRewardRule;
import com.sunlights.account.service.rewardrules.RewardRuleFactory;
import com.sunlights.account.vo.ActivityParamter;
import com.sunlights.account.vo.HoldRewardVo;
import com.sunlights.account.vo.ObtainRewardVo;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
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
    private ActivityService activityService = new ActivityServiceImpl();
    private ObtainRewardRuleService obtainRewardRuleService = new ObtainRewardRuleServiceImpl();

    private HoldRewardService holdRewardService = new HoldRewardServiceImpl();

    /**
     * 获取登录客户签到可以获取的奖励数
     * @return
     */
    public Result getSingInCanObtainRewards() {
        String scene = AccountConstant.ACTIVITY_SIGNIN_SCENE_CODE;
        ObtainRewardVo obtainRewardVo = new ObtainRewardVo();
        obtainRewardVo.setScene(scene);
        RewardResultVo rewardResultVo = getCanObtainRewards(scene);

        Message returnMessage = rewardResultVo.getReturnMessage();
        if(MsgCode.ACTIVITY_QUERY_SUCC.getCode().equals(returnMessage.getCode())) {
            obtainRewardVo.setScene(scene);
            obtainRewardVo.setObtainReward(rewardResultVo.getRewards());
            obtainRewardVo.setCanNotObtain(rewardResultVo.isCanNotObtain());
        }
        messageUtil.setMessage(returnMessage, obtainRewardVo);
        return ok(messageUtil.toJson());
    }

    /**
     * 获取客户点击一个活动场景可以获取到的奖励数
     *
     * @param scene 活动场景
     * @return
     */
    public RewardResultVo getCanObtainRewards(String scene) {
        //1：获取请求参数
        ActivityParamter activityParamter = getActivityParamter();

        //2:获取获取奖励需要的参数
        CustomerSession customerSession = getCustomerSession();
        String custNo = customerSession.getCustomerId();
        if(StringUtils.isEmpty(scene)) {
            scene = activityParamter.getScene();
        }
        //3:获取相对应场景的奖励获取规则的处理类
        IObtainRewardRule iObtainRewardRule = RewardRuleFactory.getIObtainRuleHandler(scene);
        RewardResultVo rewardResultVo = null;
        if(iObtainRewardRule == null) {
            Logger.info("还没有配置签到的场景");
            rewardResultVo = new RewardResultVo();
            Message message = new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE);
            rewardResultVo.setReturnMessage(message);
            return rewardResultVo;
        }
        rewardResultVo = iObtainRewardRule.getCanObtainRewards(custNo);
        return rewardResultVo;
    }

    /**
     *  我的财富--》我的金豆
     * @return
     */
    public Result getMyRewardTotal() {
        CustomerSession customerSession = getCustomerSession();
        String custNo = customerSession.getCustomerId();

        Long totalHoldReward = holdRewardService.getHoldRewardByCustId(custNo, AccountConstant.REWARD_TYPE_JINDOU);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.ACTIVITY_QUERY_SUCC), totalHoldReward);

        return ok(messageUtil.toJson());
    }

    /**
     *  我的金豆详情
     * @return
     */
    public Result getMyRewardDetail() {
        CustomerSession customerSession = getCustomerSession();
        String custNo = customerSession.getCustomerId();

        HoldRewardVo holdRewardVo = holdRewardService.getMyRewardDetail(custNo, AccountConstant.REWARD_TYPE_JINDOU);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.ACTIVITY_QUERY_SUCC), holdRewardVo);

        return ok(messageUtil.toJson());
    }
}
