package com.sunlights.common.service;

import com.sunlights.common.utils.ShortURLUtil;
import org.junit.Test;
import play.Logger;
import play.test.WithApplication;

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
        String shortURL = ShortURLUtil.getShortURL(path);

        Logger.info(">>shortURl:" + shortURL);

    }
}
