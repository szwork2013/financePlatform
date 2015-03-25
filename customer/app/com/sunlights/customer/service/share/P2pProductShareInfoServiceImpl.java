package com.sunlights.customer.service.share;


import models.ShareInfo;
import play.Logger;

/**
 * Created by tangweiqun on 2015/3/24.
 */
public class P2pProductShareInfoServiceImpl extends AbstractShareInfoService {

    @Override
    public String getLongUrl(ShareInfoContext context) {
        Logger.debug("P2pProductShareInfoServiceImpl getLongUrl call");

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
