package com.sunlights.customer.service.impl;

import com.sunlights.common.utils.ShortURLUtil;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.ActivityDao;
import com.sunlights.customer.dal.ShareInfoDao;
import com.sunlights.customer.dal.ShortUrlDao;
import com.sunlights.customer.dal.impl.ActivityDaoImpl;
import com.sunlights.customer.dal.impl.ShareInfoDaoImpl;
import com.sunlights.customer.dal.impl.ShortUrlDaoImpl;
import com.sunlights.customer.service.ShortUrlService;
import models.Activity;
import models.ShareInfo;
import models.ShortUrl;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by tangweiqun on 2014/12/16.
 */
public class ShortUrlServiceImpl implements ShortUrlService {
    private ShortUrlDao shortUrlDao = new ShortUrlDaoImpl();
    private ActivityDao activityDao = new ActivityDaoImpl();


    @Override
    public String add(String type, String refId, ShareInfo shareInfo) {
        String shareTitle = shareInfo.getTitle();
        String descContent = shareInfo.getContent();
        String imgUrl = shareInfo.getImageUrl();
        String appid = ActivityConstant.APP_ID;
        String baseUrl = shareInfo.getBaseUrl();

        String paramter = "?appid=" + appid + "&imgUrl=" + imgUrl + "&descContent=" + descContent + "&shareTitle=" + shareTitle;
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl);

        if(Integer.valueOf(ActivityConstant.ACCOUNT_COMMON_ONE).equals(shareInfo.getRelateRefId())) {
            if(ActivityConstant.SHARE_TYPE_ACTIVITY.equals(type)) {
                Activity activity = activityDao.findById(Long.valueOf(refId));
                sb.append("/" + activity.getUrl());
                sb.append(paramter);
                sb.append("&activityId = " + refId);
            }else if(ActivityConstant.SHARE_TYPE_INVITER.equals(type)){
                sb.append(paramter);
                sb.append("&mobile = " + refId);
            } else {
                sb.append(paramter);
            }
        }

        String longUrl = sb.toString();

        String shortUrlStr = ShortURLUtil.getShortURL(longUrl);

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setShareType(type);
        shortUrl.setRefId(refId);
        shortUrl.setShortUrl(shortUrlStr);

        shortUrlDao.doSave(shortUrl);

        return shortUrlStr;
    }

    @Override
    public String getShortUrl(String type, String refId, ShareInfo shareInfo) {
        ShortUrl shortUrl = shortUrlDao.getShortUrl(type, refId);
        if(shortUrl != null) {
            return shortUrl.getShortUrl();
        }

        String shortUrlStr = add(type, refId, shareInfo);


        return shortUrlStr;
    }
}
