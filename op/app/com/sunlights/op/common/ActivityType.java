package com.sunlights.op.common;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: ActivityType.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ActivityType {

    /**
     * 注册类
     */
    private static final String ACTIVITY_TYPE_REGISTER = "ATT001";
    /**
     * 首次购买类
     */
    private static final String ACTIVITY_TYPE_FIRST_PURCHASE = "ATT002";
    /**
     * 购买类
     */
    private static final String ACTIVITY_TYPE_PURCHASE = "ATT003";
    /**
     * 签到类
     */
    private static final String ACTIVITY_TYPE_SIGNIN = "ATT004";
    /**
     * 邀请类
     */
    private static final String ACTIVITY_TYPE_INVITE = "ATT005";

    public static final ActivityType REGISTER = new ActivityType(ACTIVITY_TYPE_REGISTER, "注册");
    public static final ActivityType FIRST_PURCHASE = new ActivityType(ACTIVITY_TYPE_FIRST_PURCHASE, "首次购买");
    public static final ActivityType PURCHASE = new ActivityType(ACTIVITY_TYPE_PURCHASE, "购买");
    public static final ActivityType SIGNIN = new ActivityType(ACTIVITY_TYPE_SIGNIN, "签到");
    private static final ActivityType INVITE = new  ActivityType(ACTIVITY_TYPE_INVITE,"邀请");

    private String key;
    private String value;

    public ActivityType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 查找所有活动类型
     * @return
     */
    public static List<ActivityType> findActivityTypes() {
        List<ActivityType> activityTypes = new ArrayList<ActivityType>();
        activityTypes.add(ActivityType.REGISTER);
        activityTypes.add(ActivityType.FIRST_PURCHASE);
        activityTypes.add(ActivityType.PURCHASE);
        activityTypes.add(ActivityType.SIGNIN);
        activityTypes.add(ActivityType.INVITE);
        return activityTypes;
    }

    /**
     * 根据key查找活动类型
     * @param key
     * @return
     */
    public static ActivityType findActivityTypeByKey(String key) {
        if (StringUtils.isEmpty(key)) return null;
        List<ActivityType> activityTypes = findActivityTypes();
        for (ActivityType activityType : activityTypes) {
            if (activityType.getKey().equals(key)) {
                return activityType;
            }
        }
        return null;
    }

    /**
     * 根据key查找活动类型值
     * @param key
     * @return
     */
    public static String findValueByKey(String key) {
        ActivityType at = findActivityTypeByKey(key);
        return at == null ? key : at.getValue();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
