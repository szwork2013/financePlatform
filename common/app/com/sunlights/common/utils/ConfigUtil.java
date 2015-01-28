package com.sunlights.common.utils;

import org.apache.commons.httpclient.HttpClient;

import play.Configuration;
import play.Logger;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ConfigurationUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ConfigUtil {

    public static String proxy_host = "proxy_host";
    public static String proxy_port = "proxy_port";

    public static String getValueStr(String name){
        Configuration root = Configuration.root();

        String value = root.getString(name);
        Logger.info(name + "：" + value);

        return value;
    }

    public static int getValueInt(String name){
        Configuration root = Configuration.root();

        int value = root.getInt(name);
        Logger.info(name + "：" + value);

        return value;
    }

    public static void setProxy(HttpClient httpClient) {
        String proxyHost = getValueStr(proxy_host);
        Logger.info("proxy_host:"+ proxyHost);
        if(proxyHost != null) {
            int proxyPort = getValueInt(proxy_port);
            Logger.info(" proxy_port:"+proxyPort);
            httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);
        }
    }
}