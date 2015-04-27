package com.sunlights.common.vo;

import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: SmsMessageVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class SmsMessageVo implements Serializable {
    private String smsId;
    private List<String> mobileList = Lists.newArrayList();
    private String content;
    private String successInd = AppConst.STATUS_INVALID;

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public List<String> getMobileList() {
        return mobileList;
    }

    public void setMobileList(List<String> mobileList) {
        this.mobileList = mobileList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSuccessInd() {
        return successInd;
    }

    public void setSuccessInd(String successInd) {
        this.successInd = successInd;
    }
}
