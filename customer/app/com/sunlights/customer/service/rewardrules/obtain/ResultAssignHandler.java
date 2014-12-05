package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import com.sunlights.customer.vo.ActivityResultVo;
import com.sunlights.customer.vo.ObtainRewardVo;
import play.Configuration;
import play.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取奖励成功后结果赋值
 * Created by tangweiqun on 2014/12/2.
 */
public class ResultAssignHandler extends AbstractObtainRuleHandler {

    public ResultAssignHandler() {

    }

    public ResultAssignHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

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

            String detail = rewardFlowRecordVo.getRewardAmtFromTrans() + Configuration.root().getString("detail-" + rewardFlowRecordVo.getRewardType());

            ActivityResultVo activityResultVo = new ActivityResultVo();
            activityResultVo.setTitle(rewardFlowRecordVo.getActivityTitle());
            activityResultVo.setRewardType(rewardFlowRecordVo.getRewardType());
            activityResultVo.setDetail(detail);
            activityResultVo.setRuleUrl(rewardFlowRecordVo.getRuleUrl());
            responseVo.addActivityResultVo(activityResultVo);


        }



    }

    @Override
    public String toString() {
        return "ResultAssignHandler";
    }

}
