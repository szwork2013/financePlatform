package com.sunlights.common;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgCenterConst.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public enum MsgCenterCategory {
    REGISTER("register", MsgCenterRuleConst.REGISTER_BEAN);



    private String methodName;
    private String ruleCode;

    private MsgCenterCategory(String methodName, String ruleCode){
        this.methodName = methodName;
        this.ruleCode = ruleCode;
    }

    public static String getRuleCodeByMethodName(String methodName) {
        MsgCenterCategory[] categories = MsgCenterCategory.values();
        for (MsgCenterCategory category : categories) {
            if (category.methodName.equals(methodName)) {
                return category.ruleCode;
            }
        }
        throw new IllegalArgumentException("Cannot find MsgCenterCategory for:" + methodName);
    }


    public static MsgCenterCategory findMsgCenterCategory(String methodName) {
        return Enum.valueOf(MsgCenterCategory.class, methodName);
    }
}
