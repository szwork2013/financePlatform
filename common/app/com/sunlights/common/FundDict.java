package com.sunlights.common;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: FundDict.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class FundDict {
    /**
     * 0:低风险 1:中风险 2:高风险
     */
    public enum RiskLevel {
        R0("低风险");
        private String description;

        RiskLevel(String description) {
            this.description = description;
        }
    }
}

