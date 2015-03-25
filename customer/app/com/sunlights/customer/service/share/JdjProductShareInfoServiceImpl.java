package com.sunlights.customer.service.share;


import com.sunlights.customer.ActivityConstant;
import models.Activity;
import models.ShareInfo;
import play.Logger;

/**
 * Created by tangweiqun on 2015/3/24.
 */
public class JdjProductShareInfoServiceImpl extends AbstractShareInfoService {

    @Override
    public String getLongUrl(ShareInfoContext context) {
        Logger.debug("JdjProductShareInfoServiceImpl getLongUrl call");

        ShareInfo shareInfo = context.getShareInfo();
        StringBuilder sb = new StringBuilder();
        sb.append(shareInfo.getBaseUrl());
        sb.append("?productCode=" + context.getRefId() + "&type=" + context.getType());

        return sb.toString();
    }

    @Override
    public void prepareShareInfo(ShareInfoContext context) {

    }
}
