package com.sunlights.customer.service.rewardrules.obtain;


import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.RewardFlowRecordVo;
import com.sunlights.customer.vo.ObtainRewardVo;
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
            return;
        }

        for(RewardFlowRecordVo rewardFlowRecordVo : rewardFlowRecordVos) {
            ObtainRewardVo obtainRewardVo = new ObtainRewardVo();
            obtainRewardVo.setScene(rewardFlowRecordVo.getScene());
            obtainRewardVo.setStatus(rewardFlowRecordVo.getStatus());
            obtainRewardVo.setAlreadyGet(rewardFlowRecordVo.getRewardAmtResult());
            obtainRewardVo.setNotGet(rewardFlowRecordVo.getNotGet());
            responseVo.addObtainRewardVo(obtainRewardVo);
        }



    }

    @Override
    public String toString() {
        return "ResultAssignHandler";
    }

}
