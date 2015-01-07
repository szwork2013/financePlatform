package com.sunlights.customer.service.rewardrules.vo;

import com.google.common.collect.Lists;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.customer.vo.ActivityResultVo;
import com.sunlights.customer.vo.ObtainRewardVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangweiqun on 2014/12/1.
 */
public class ActivityResponseVo {
    //=============================老接口==================
    private Long alreadyGet;

    private Long notGet;
    //=================================================
    private String status;

    /**
     * 结果信息
     */
    private Message message = new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS);

    /**
     * 判断流程是否退出
     */
    private boolean isFlowStop;

    /**
     * 产生奖励流水的元数据
     */
    private List<RewardFlowRecordVo> rewardFlowRecordVos = new ArrayList<RewardFlowRecordVo>();;
    //老接口返回的结果
    private List<ObtainRewardVo>  obtainRewardVos = Lists.newArrayList();
    //新接口返回的结果
    private List<ActivityResultVo> activityResultVos;
    //发送消息需要的信息
    private List<MessageHeaderVo> messageHeaderVos = Lists.newArrayList();

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

    public List<ObtainRewardVo> getObtainRewardVo() {
        return obtainRewardVos;
    }

    public void addObtainRewardVo(ObtainRewardVo obtainRewardVo) {

        obtainRewardVos.add(obtainRewardVo);
    }

    public void addActivityResultVo(ActivityResultVo activityResultVo) {
        if(this.activityResultVos == null) {
            activityResultVos = new ArrayList<ActivityResultVo>();
        }

        activityResultVos.add(activityResultVo);
    }

    public List<MessageHeaderVo> getMessageHeaderVos() {
        return messageHeaderVos;
    }

    public void addMessageHeaderVo(MessageHeaderVo messageHeaderVo) {
        if(this.messageHeaderVos == null) {
            this.messageHeaderVos = Lists.newArrayList();
        }
        if(messageHeaderVo == null) {
            return;
        }
        this.messageHeaderVos.add(messageHeaderVo);
    }

    public List<ActivityResultVo> getActivityResultVos() {
        return activityResultVos;
    }

    @Override
    public String toString() {
        return message.toString();
    }
}
