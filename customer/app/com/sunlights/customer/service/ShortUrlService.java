package com.sunlights.customer.service;

import models.ShareInfo;

/**
 * Created by Administrator on 2014/12/15.
 */
public interface ShortUrlService {

    public String add(String type, String refId, ShareInfo shareInfo);

    public String getShortUrl(String type, String refId, ShareInfo shareInfo);

}
