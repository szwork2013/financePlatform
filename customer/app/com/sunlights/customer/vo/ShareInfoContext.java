package com.sunlights.customer.vo;

import models.ShareInfo;

/**
 * Created by Administrator on 2014/12/17.
 */
public class ShareInfoContext {

    private String type;

    private String refId;

    private String custNo;

    private ShareInfo shareInfo;

    private ShareInfoVo shareInfoVo;

    private String commonParamter;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public ShareInfo getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(ShareInfo shareInfo) {
        this.shareInfo = shareInfo;
    }

    public String getCommonParamter() {
        return commonParamter;
    }

    public void setCommonParamter(String commonParamter) {
        this.commonParamter = commonParamter;
    }

    public ShareInfoVo getShareInfoVo() {
        return shareInfoVo;
    }

    public void setShareInfoVo(ShareInfoVo shareInfoVo) {
        this.shareInfoVo = shareInfoVo;
    }
}
