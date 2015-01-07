package com.sunlights.common.vo;

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
public class MessageHeaderVo implements Serializable{
    private String messageType;
    private String scene;
    private String customerId;
    private List<String> params = Lists.newArrayList();

    public MessageHeaderVo(){

    }

    public MessageHeaderVo(String messageType, String scene, String customerId){
        this.messageType = messageType;
        this.scene = scene;
        this.customerId = customerId;
    }

    public void buildParams(String... strings){
        if (strings == null) {
            return;
        }
        params = Arrays.asList(strings);
    }

    public void addParamter(String paramter) {
        this.params.add(paramter);
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
}
