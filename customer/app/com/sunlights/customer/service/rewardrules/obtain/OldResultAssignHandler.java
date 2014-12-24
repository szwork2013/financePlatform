package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityReturnMsgService;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import com.sunlights.customer.vo.ActivityResultVo;
import com.sunlights.customer.vo.ObtainRewardVo;
import play.Configuration;
import play.Logger;

import java.text.MessageFormat;
import java.util.List;

/**
 *
 * 为了兼容注册和签到送金豆的接口，这个返回接口所需要的数据结构
 *
 * Created by tangweiqun on 2014/12/4.
 */
public class OldResultAssignHandler extends AbstractObtainRuleHandler{

    private ActivityReturnMsgService activityReturnMsgService = ActivityServiceFactory.getActivityReturnMsgService();
    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        List<RewardFlowRecordVo> rewardFlowRecordVos = responseVo.getRewardFlowRecordVos();
        if(rewardFlowRecordVos == null || rewardFlowRecordVos.isEmpty()) {
            Logger.info("没有获得的奖励结果");
            Message message = new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE);
            message.setSummary("参加活动失败");
            message.setDetail(MsgCode.NOT_CONFIG_ACTIVITY_SCENE.getMessage());
            responseVo.setMessage(message);
            return;
        }


        for(RewardFlowRecordVo rewardFlowRecordVo : rewardFlowRecordVos) {
            ObtainRewardVo obtainRewardVo = new ObtainRewardVo();
            obtainRewardVo.setScene(rewardFlowRecordVo.getScene());
            obtainRewardVo.setStatus(rewardFlowRecordVo.getStatus());
            obtainRewardVo.setAlreadyGet(rewardFlowRecordVo.getRewardAmtResult());
            obtainRewardVo.setNotGet(rewardFlowRecordVo.getNotGet());

            responseVo.addObtainRewardVo(obtainRewardVo);
            Logger.debug("rewardFlowRecordVo.getScene() = " + rewardFlowRecordVo.getScene() + " rewardFlowRecordVo.getActivityType() = " + rewardFlowRecordVo.getActivityType() + " rewardFlowRecordVo.getRewardType() = " + rewardFlowRecordVo.getRewardType());
            //String detail = MessageFormat.format(Configuration.root().getString("detail." + rewardFlowRecordVo.getRewardType() + "." + rewardFlowRecordVo.getScene()), obtainRewardVo.getAlreadyGet());
            String template = activityReturnMsgService.getReturnMsg(rewardFlowRecordVo.getScene(), rewardFlowRecordVo.getActivityType(), rewardFlowRecordVo.getRewardType(),
                    ActivityConstant.RETURN_MSG_CATEGORY_REWARD_TRADE, MsgCode.OPERATE_SUCCESS.getCode());
            Logger.debug("template = " + template);
            String detail = MessageFormat.format(template, obtainRewardVo.getAlreadyGet());
            Message message = responseVo.getMessage();
            //message.setSummary(Configuration.root().getString("summary." + rewardFlowRecordVo.getRewardType() + "." + rewardFlowRecordVo.getScene()));
            message.setDetail(detail);


            responseVo.setMessage(message);

            Logger.debug("返回老接口结果成功 rewardFlowRecordVo = " + rewardFlowRecordVo);

        }


    }

    @Override
    public String toString() {
        return "OldResultAssignHandler";
    }
}
