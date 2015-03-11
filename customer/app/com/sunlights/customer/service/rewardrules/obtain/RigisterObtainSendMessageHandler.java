package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.common.DictConst;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityReturnMsgService;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import models.Customer;
import play.Logger;

import java.text.MessageFormat;
import java.util.List;

/**
 * 注册场景发送消息处理类
 * <p/>
 * Created by tangweiqun on 2014/12/19.
 */
public class RigisterObtainSendMessageHandler extends AbstractObtainRuleHandler {
    private ActivityReturnMsgService activityReturnMsgService = ActivityServiceFactory.getActivityReturnMsgService();

    private CustomerService customerService = new CustomerService();

    public RigisterObtainSendMessageHandler() {

    }

    public RigisterObtainSendMessageHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        String scene = requestVo.getScene();
        String custNo = requestVo.getCustId();

        MessageHeaderVo messageHeaderVo = null;
        List<RewardFlowRecordVo> rewardFlowRecordVos = responseVo.getRewardFlowRecordVos();
        RewardFlowRecordVo rewardFlowRecordVo = null;
        for (int i = 0; i < rewardFlowRecordVos.size(); i++) {
            rewardFlowRecordVo = rewardFlowRecordVos.get(i);
            Logger.debug("rewardFlowRecordVo.getScene() = " + rewardFlowRecordVo.getScene() + " rewardFlowRecordVo.getActivityType() = " + rewardFlowRecordVo.getActivityType() + " rewardFlowRecordVo.getRewardType() = " + rewardFlowRecordVo.getRewardType());
            //String template = Configuration.root().getString("message." + rewardFlowRecordVo.getRewardType());
            String template = activityReturnMsgService.getReturnMsg(rewardFlowRecordVo.getScene(), rewardFlowRecordVo.getActivityType(), rewardFlowRecordVo.getRewardType(),
                    ActivityConstant.RETURN_MSG_CATEGORY_MESSAGE_SEND, null);
            Logger.debug("template = " + template);
            if (rewardFlowRecordVo.isRecommender()) {
                Customer customer = customerService.getCustomerByCustomerId(custNo);
                messageHeaderVo = new MessageHeaderVo(DictConst.PUSH_TYPE_2, ActivityConstant.ACTIVITY_INVITE_SCENE_CODE, requestVo.getRecommendCustId());
                messageHeaderVo.buildParams(customer.getMobile(), MessageFormat.format(template, rewardFlowRecordVo.getRewardAmtResult()));
            } else {
                messageHeaderVo = new MessageHeaderVo(DictConst.PUSH_TYPE_2, scene, custNo);
                messageHeaderVo.buildParams(MessageFormat.format(template, rewardFlowRecordVo.getRewardAmtFromTrans()));
            }
            responseVo.addMessageHeaderVo(messageHeaderVo);
        }

    }

    @Override
    public String toString() {
        return "RigisterObtainSendMessageHandler";
    }
}
