package com.sunlights.common.service;

import org.junit.Test;
import play.Logger;
import play.test.WithApplication;
import services.ShortUrlService;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShortUrlServiceTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ShortUrlServiceTest extends WithApplication {
    @Test
    public void getShortUrlTest() {
        String path = "http://www.baidu.com";

        services.ShortUrlService shortUrlService = new ShortUrlService();

        String shortUrlStr = shortUrlService.getShortURL(path);

        Logger.info(">>shortURl:" + shortUrlStr);

    }
}
