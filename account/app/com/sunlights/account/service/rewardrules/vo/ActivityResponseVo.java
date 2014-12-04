package com.sunlights.account.service.rewardrules.vo;

import com.sunlights.common.vo.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangweiqun on 2014/12/1.
 */
public class ActivityResponseVo {
    private Long alreadyGet;

    private Long notGet;

    private String status;

    private Message message;

    private boolean isFlowStop;

    private List<RewardFlowRecordVo> rewardFlowRecordVos;

    public Long getAlreadyGet() {
        return alreadyGet;
    }

    public void setAlreadyGet(Long alreadyGet) {
        this.alreadyGet = alreadyGet;
    }

    public Long getNotGet() {
        return notGet;
    }

    public void setNotGet(Long notGet) {
        this.notGet = notGet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isFlowStop() {
        return isFlowStop;
    }

    public void setFlowStop(boolean isFlowStop) {
        this.isFlowStop = isFlowStop;
    }

    public List<RewardFlowRecordVo> getRewardFlowRecordVos() {
        return rewardFlowRecordVos;
    }

    public void setRewardFlowRecordVos(List<RewardFlowRecordVo> rewardFlowRecordVos) {
        this.rewardFlowRecordVos = rewardFlowRecordVos;
    }

    public void addRewardFlowRecordVo(RewardFlowRecordVo rewardFlowRecordVo) {
        if(this.rewardFlowRecordVos == null) {
            rewardFlowRecordVos = new ArrayList<RewardFlowRecordVo>();
        }

        rewardFlowRecordVos.add(rewardFlowRecordVo);
    }
}
