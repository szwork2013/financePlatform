package com.sunlights.customer.service.share;


import com.sunlights.common.utils.CommonUtil;
import com.sunlights.customer.ActivityConstant;
import models.Activity;
import models.ShareInfo;
import play.Logger;

import java.util.Date;

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
        String date = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_SHORT);
        sb.append("?code=" + context.getRefId() + "&type=" + context.getType() + "&time=" + date);

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
