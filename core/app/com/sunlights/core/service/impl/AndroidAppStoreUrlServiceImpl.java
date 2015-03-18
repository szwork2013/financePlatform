package com.sunlights.core.service.impl;

import com.sunlights.core.service.AndroidAppStoreUrlService;
import play.Configuration;

import java.util.List;
import java.util.Random;

/**
 * Created by tangweiqun on 2015/3/18.
 *
 * 获取安卓应用市场下载的url
 */
public class AndroidAppStoreUrlServiceImpl implements AndroidAppStoreUrlService {


    public static final String ANDROID_APPSTORE_URLS = "android.appstore.urls";

    private Random rand = new Random(25);

    @Override
    public String getAppStoreUrl() {
        List<String> listUrls = Configuration.root().getStringList(ANDROID_APPSTORE_URLS);
        if(listUrls == null && listUrls.isEmpty()) {
            return null;
        }

        int length = listUrls.size();

        return listUrls.get(rand.nextInt(length));
    }


}
