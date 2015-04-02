package com.sunlights.customer.service.share;

import com.sunlights.customer.vo.ShareInfoVo;
import models.ShareInfo;

/**
 * Created by Administrator on 2014/12/17.
 */
public class ShareInfoContext {
    //分享种类  是分享产品  、 分享活动，分享app等
    private String type;
    //如果分享和具体的业务类型有关则需要，比如分享活动是指具体分享某个活动的,则refId为活动的id
    private String refId;
    //客户号
    private String custNo;

    private ShareInfo shareInfo;

    private ShareInfoVo shareInfoVo;

    private String commonParamter;

    private String appId;

    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

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
