package com.sunlights.customer.dal;

import models.ShortUrl;

/**
 * Created by Administrator on 2014/12/16.
 */
public interface ShortUrlDao {

    public void doSave(ShortUrl shortUrl);

    public ShortUrl getShortUrl(String type, String refId, String mobile);
}
