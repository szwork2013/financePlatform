package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.MessageRuleMapping;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/14.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessagePushMappingVo  implements Serializable {
    private Long id ;
    private String methodName;
    private String ruleCode;
    private String messageType;
    private String scene;
    private String sceneDesc;
    private Long activityId;
    private String activityDesc;
    private String status;

    private String messageTypeDes;

    public MessagePushMappingVo() {
        super();
    }

    public MessagePushMappingVo(MessageRuleMapping message) {
        inMessageRuleMapping(message);
    }


    public void inMessageRuleMapping(MessageRuleMapping message) {
        this.id = message.getId();
        this.methodName=message.getMethodName();
        this.ruleCode=message.getRuleCode();
        this.messageType=message.getMessageType();
        this.scene=message.getScene();
        this.activityId=message.getActivityId();
        this.status = message.getStatus();

    }

    public MessageRuleMapping convertToMessageRuleMapping() {
        MessageRuleMapping messRuleMp = new MessageRuleMapping();
        messRuleMp.setId(this.id);
        messRuleMp.setMethodName(this.methodName);
        messRuleMp.setRuleCode(this.ruleCode);
        messRuleMp.setMessageType(this.messageType);
        messRuleMp.setScene(this.scene);
        messRuleMp.setActivityId(this.activityId);
        messRuleMp.setStatus(this.status);


        return messRuleMp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getMessageTypeDes() {
        return messageTypeDes;
    }

    public void setMessageTypeDes(String messageTypeDes) {
        this.messageTypeDes = messageTypeDes;
    }

    public String getSceneDesc() {
        return sceneDesc;
    }

    public void setSceneDesc(String sceneDesc) {
        this.sceneDesc = sceneDesc;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }
}
