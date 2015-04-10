package com.sunlights.customer.service.share;

import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.ShareInfoDao;
import com.sunlights.customer.dal.ShortUrlDao;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.dal.impl.ShortUrlDaoImpl;
import com.sunlights.customer.factory.ShareInfoDaoFactory;
import com.sunlights.customer.vo.ShareInfoVo;
import models.Customer;
import models.ShareInfo;
import models.ShortUrl;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public abstract class AbstractShareInfoService implements ShareInfoService {
    private ShareInfoDao shareInfoDao = ShareInfoDaoFactory.getShareInfoDao();
    private ShortUrlDao shortUrlDao = new ShortUrlDaoImpl();
    private services.ShortUrlService shortUrlService = new services.ShortUrlService();
    private CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public ShareInfoVo getShareInfoByType(ShareInfoContext context) {
        //根据分享种类获取配置的分享信息
        ShareInfo shareInfo = getBasicShareInfoModel(context.getType(), context.getRefId());
        if (shareInfo == null) {
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
        String mobile = getMobile(context.getCustNo());
        context.setMobile(mobile);
        ShortUrl shortUrl = shortUrlDao.getShortUrl(context.getType(), context.getRefId(),mobile);

        if(shortUrl != null) {
            return shortUrl.getShortUrl();
        }

        return saveURL(context);
    }

    public String saveURL(ShareInfoContext context) {
        String longUrl = getLongUrl(context);
        Logger.info("longUrl :" + longUrl);

        String shortUrlStr = shortUrlService.getShortURL(longUrl);
        Logger.info("shortUrlStr :" + shortUrlStr);

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setShareType(context.getType());
        shortUrl.setRefId(context.getRefId());
        shortUrl.setShortUrl(shortUrlStr);
        shortUrl.setMobile(context.getMobile());
        shortUrlDao.doSave(shortUrl);

        return shortUrlStr;
    }


    @Override
    public ShareInfo getBasicShareInfoModel(String type, String refId) {
        ShareInfo shareInfoParent = shareInfoDao.getParentByType(type);

        Logger.debug("shareInfoParent = " + shareInfoParent + " type = " + type);

        List<ShareInfo> specialInfos = shareInfoDao.getByParentId(shareInfoParent.getId());

        if(specialInfos == null || specialInfos.isEmpty()) {
            return shareInfoParent;
        }

        return transform(specialInfos, shareInfoParent, refId);
    }

    private ShareInfo transform(List<ShareInfo> specialInfos, ShareInfo shareInfoParent, String refId) {
        Logger.debug("refId = " + refId);
        ShareInfo shareInfoTemp = shareInfoParent;
        for(ShareInfo temp : specialInfos) {
            Logger.debug("temp == " + temp);
            if(temp.getRefId().equals(refId)) {
                shareInfoTemp = new ShareInfo();
                shareInfoTemp.setBaseUrl(getNotNullStr(temp.getBaseUrl(), shareInfoParent.getBaseUrl()));
                shareInfoTemp.setContent(getNotNullStr(temp.getContent(), shareInfoParent.getContent()));
                shareInfoTemp.setImageUrl(getNotNullStr(temp.getImageUrl(), shareInfoParent.getImageUrl()));
                shareInfoTemp.setTitle(getNotNullStr(temp.getTitle(), shareInfoParent.getTitle()));
                shareInfoTemp.setRelateRefId(temp.getRelateRefId());

            }
        }
        return shareInfoTemp;
    }

    /**
     * 获得手机号
     *
     * @return
     */
    protected String getMobile(String custNo) {
        if (StringUtils.isEmpty(custNo)) {
            return "99999999999";
        }
        Customer customer = customerDao.getCustomerByCustomerId(custNo);
        String mobile = customer.getMobile();//获得手机号
        Logger.debug("获得的手机号为:" + mobile);
        return mobile;
    }

    public abstract String getLongUrl(ShareInfoContext context);

    public abstract void prepareShareInfo(ShareInfoContext context);
}
