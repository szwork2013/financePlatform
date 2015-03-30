package com.sunlights.op.common.util;

import play.Configuration;

/**
 * Created by guxuelong on 2014/12/13.
 */
public class ConfigUtil {
    public static final String SMTP_FROM = "smtp.from";
    public static final String SMTP_SUBJECT = "smtp.subject";
    public static final String SMTP_TO = "smtp.to";
    public static final String TICKET_URL = "ticket.url";

    /**
     * 根据application.conf的key获取value
     *
     * @param key
     * @return
     */
    public  static String getConfigValue(String key){
        return Configuration.root().getString(key);
    }
}


