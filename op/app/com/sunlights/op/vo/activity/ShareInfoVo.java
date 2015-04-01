package com.sunlights.op.vo.activity;

import com.sunlights.op.common.constants.ActivityConstant;
import models.ShareInfo;

/**
 * Created by Administrator on 2014/12/21.
 */
public class ShareInfoVo extends ShareInfo {

    private String shareTypeStr;

    private String isRefIdStr;

    public String getShareTypeStr() {
        return ShareInfoType.getDescByType(getShareType());
    }

    public void setShareTypeStr(String shareTypeStr) {
        this.shareTypeStr = shareTypeStr;
    }

    public String getIsRefIdStr() {
        if(ActivityConstant.COMMOM_ONE.equals(getRelateRefId())) {
            return "有关";
        }
        return "无关";
    }

    public void setIsRefIdStr(String isRefIdStr) {
        this.isRefIdStr = isRefIdStr;
    }
}
