package com.sunlights.op.vo;

import java.io.Serializable;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: SmsMessageVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class SmsMessageVo implements Serializable{
    private String sendTime;
    private String sendStatus;
    private String sendStatusDesc;
    private int count;

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSendStatusDesc() {
        return sendStatusDesc;
    }

    public void setSendStatusDesc(String sendStatusDesc) {
        this.sendStatusDesc = sendStatusDesc;
    }
}
