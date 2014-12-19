package com.sunlights.customer.service;

import com.sunlights.common.utils.ShortURLUtil;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.ShareInfoDao;
import com.sunlights.customer.dal.ShortUrlDao;
import com.sunlights.customer.dal.impl.ShareInfoDaoImpl;
import com.sunlights.customer.dal.impl.ShortUrlDaoImpl;
import com.sunlights.customer.vo.ShareInfoContext;
import com.sunlights.customer.vo.ShareInfoVo;
import models.ShareInfo;
import models.ShortUrl;
import play.Configuration;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public abstract class AbstractShareInfoService implements ShareInfoService {
    private ShareInfoDao shareInfoDao = new ShareInfoDaoImpl();
    private ShortUrlDao shortUrlDao = new ShortUrlDaoImpl();

    @Override
    public ShareInfoVo getShareInfoByType(ShareInfoContext context) {
        ShareInfo shareInfo = shareInfoDao.getByType(context.getType());
        if(shareInfo == null) {
            throw new RuntimeException("不支持的分享");
        }
        ShareInfoVo shareInfoVo = new ShareInfoVo();
        context.setShareInfo(shareInfo);
        context.setShareInfoVo(shareInfoVo);

        prepareShareInfo(context);

        String shortUrl = getShortUrl(context);

        shareInfoVo.setShortUrl(shortUrl);
        shareInfoVo.setContent(getNotNullStr(shareInfoVo.getContent(), shareInfo.getContent()));
        shareInfoVo.setTitle(getNotNullStr(shareInfoVo.getTitle(), shareInfo.getTitle()));
        shareInfoVo.setImageUrl(getNotNullStr(shareInfoVo.getImageUrl(), shareInfo.getImageUrl()));
        return shareInfoVo;
    }

    private String getNotNullStr(String desc, String orig) {
        return desc == null ? orig : desc;
    }

    public String getShortUrl(ShareInfoContext context) {
        ShortUrl shortUrl = shortUrlDao.getShortUrl(context.getType(), context.getRefId());
        if(shortUrl != null) {
            return shortUrl.getShortUrl();
        }

        String shortUrlStr = add(context);

        return shortUrlStr;
    }

    public String add(ShareInfoContext context) {
        String shareTitle = context.getShareInfo().getTitle();
        String descContent = context.getShareInfo().getContent();
        String imgUrl = context.getShareInfo().getImageUrl();
        String appid = Configuration.root().getString("appId");

        String commonParamter = "?appid=" + appid + "&imgUrl=" + imgUrl + "&descContent=" + descContent + "&shareTitle=" + shareTitle;
        context.setCommonParamter(commonParamter);

        String longUrl = getLongUrl(context);
        String shortUrlStr = ShortURLUtil.getShortURL(longUrl);

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setShareType(context.getType());
        shortUrl.setRefId(context.getRefId());
        shortUrl.setShortUrl(shortUrlStr);

        shortUrlDao.doSave(shortUrl);

        return shortUrlStr;
    }

    public abstract String getLongUrl(ShareInfoContext context);

    public abstract void prepareShareInfo(ShareInfoContext context);
}
