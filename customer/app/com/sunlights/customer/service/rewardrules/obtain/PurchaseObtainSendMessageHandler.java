package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.common.DictConst;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.ActivityReturnMsgService;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import play.Logger;

import java.text.MessageFormat;
import java.util.List;

/**
 * 购买消息发送
 * <p/>
 * Created by tangweiqun on 2014/12/19.
 */
public class PurchaseObtainSendMessageHandler extends AbstractObtainRuleHandler {
    private ActivityReturnMsgService activityReturnMsgService = ActivityServiceFactory.getActivityReturnMsgService();

    public PurchaseObtainSendMessageHandler() {

    }

    public PurchaseObtainSendMessageHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {

        String scene = requestVo.getScene();
        String custNo = requestVo.getCustId();

        if (!ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE.equals(scene)) {
            Logger.debug("购买暂时不支持发消息");
            return;
        }

        MessageHeaderVo messageHeaderVo = null;
        List<RewardFlowRecordVo> rewardFlowRecordVos = responseVo.getRewardFlowRecordVos();
        RewardFlowRecordVo rewardFlowRecordVo = null;
        for (int i = 0; i < rewardFlowRecordVos.size(); i++) {
            rewardFlowRecordVo = rewardFlowRecordVos.get(i);
            //String template = Configuration.root().getString("message." + rewardFlowRecordVo.getRewardType());
            String template = activityReturnMsgService.getReturnMsg(rewardFlowRecordVo.getScene(), rewardFlowRecordVo.getActivityType(), rewardFlowRecordVo.getRewardType(),
                    ActivityConstant.RETURN_MSG_CATEGORY_MESSAGE_SEND, null);

            if (rewardFlowRecordVo.isRecommender()) {
                messageHeaderVo = new MessageHeaderVo(DictConst.PUSH_TYPE_2, ActivityConstant.ACTIVITY_INVITE_SCENE_CODE, requestVo.getRecommendCustId());
            } else {
                messageHeaderVo = new MessageHeaderVo(DictConst.PUSH_TYPE_2, scene, custNo);
            }
            messageHeaderVo.buildParams(MessageFormat.format(template, rewardFlowRecordVo.getRewardAmtFromTrans()));
            responseVo.addMessageHeaderVo(messageHeaderVo);
        }
    }

    @Override
    public String toString() {
        return "PurchaseObtainSendMessageHandler";
    }


}
