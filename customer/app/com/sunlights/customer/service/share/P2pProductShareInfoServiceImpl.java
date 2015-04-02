package com.sunlights.customer.service.share;


import com.sunlights.common.utils.CommonUtil;
import models.ShareInfo;
import play.Logger;

import java.util.Date;

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
        String date = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_SHORT);
        sb.append("?code=" + context.getRefId() + "&shareType=" + context.getType() + "&time=" + date);

        return sb.toString();
    }

    @Override
    public String getShortUrl(ShareInfoContext context) {
        return saveURL(context);
    }

    @Override
    public void prepareShareInfo(ShareInfoContext context) {

    }
}
