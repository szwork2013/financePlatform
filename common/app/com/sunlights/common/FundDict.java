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
public final class FundDict {
    /**
     * 0:低风险 1:中风险 2:高风险
     */
    public enum RiskLevel {
        R0("低风险"), R1("中风险"), R2("高风险");
        private String description;

        RiskLevel(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static String getRiskLevel(String riskLevel) {
        RiskLevel level = Enum.valueOf(RiskLevel.class, "R" + riskLevel);
        return level == null ? riskLevel : level.getDescription();
    }

    /**
     * 1:支持快速赎回 0:不支持快速赎回
     */
    public enum RapidRedeem {
        R0("非快速赎回"), R1("快速赎回");
        private String description;

        RapidRedeem(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static String getRapidRedeem(Integer rapidRedeem) {
        RapidRedeem redeem = Enum.valueOf(RapidRedeem.class, "R" + rapidRedeem);
        return redeem == null ? rapidRedeem.toString() : redeem.getDescription();
    }


}

