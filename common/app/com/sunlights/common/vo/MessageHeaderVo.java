package com.sunlights.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MessageActivityVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageHeaderVo implements Serializable {
    private String messageType;
    private String scene;
    private String customerId;
    private String mobile;//获取验证码时未注册 传入mobile，其它情况不传
    private List<String> params = Lists.newArrayList();//系统自动发送时传入模版占位符参数
    //唯有messageType= DictConst.PUSH_TYPE_5 = FP.PUSH.TYPE.5 时才有效
    // 1、后台系统手动发送消息时传入待发消息编码集合
    // 2、同一推送类型同一场景下对应多条消息规则且模版不一致 如 兑换话费 有兑换成功推送、兑换失败推送
    private String ruleCode;


    public MessageHeaderVo() {

    }

    public MessageHeaderVo(String messageType, String scene, String customerId) {
        this.messageType = messageType;
        this.scene = scene;
        this.customerId = customerId;
    }

    public void buildParams(String... strings) {
        if (strings == null) {
            return;
        }
        params = Arrays.asList(strings);
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }
}
