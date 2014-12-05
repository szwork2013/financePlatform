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
import com.sunlights.customer.service.rewardrules.IObtainRewardRule;
import com.sunlights.customer.service.rewardrules.RewardRuleFactory;
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
@Deprecated
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
        String scene = ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE;
        ObtainRewardVo obtainRewardVo = new ObtainRewardVo();
        obtainRewardVo.setScene(scene);
        RewardResultVo rewardResultVo = getCanObtainRewards(scene);

        Message returnMessage = rewardResultVo.getReturnMessage();
        if(MsgCode.ACTIVITY_QUERY_SUCC.getCode().equals(returnMessage.getCode())) {
            obtainRewardVo.setAlreadyGet(0L);
            obtainRewardVo.setNotGet(rewardResultVo.getNotGet());
            obtainRewardVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_NOMAL);
        } else {
            obtainRewardVo.setAlreadyGet(rewardResultVo.getAlreadyGet());
            obtainRewardVo.setNotGet(0L);
            obtainRewardVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
        }
        returnMessage = new Message(Severity.INFO, MsgCode.ACTIVITY_QUERY_SUCC);
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
        rewardResultVo = iObtainRewardRule.getCanObtainRewards(custNo, null);
        return rewardResultVo;
    }

    /**
     *  我的金豆详情
     * @return
     */
    public Result getMyRewardDetail() {
        CustomerSession customerSession = getCustomerSession();
        String custNo = customerSession.getCustomerId();

        HoldRewardVo holdRewardVo = holdRewardService.getMyRewardDetail(custNo, ActivityConstant.REWARD_TYPE_JINDOU);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.REWARD_QUERY_SUCC), holdRewardVo);

        return ok(messageUtil.toJson());
    }
}
