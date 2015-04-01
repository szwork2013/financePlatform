package com.sunlights.op.common.constants;

import java.math.BigDecimal;

/**
 * Created by guxuelong on 2014/12/15.
 */
public class MonitorConstants {
    /** 阀值 默认值 */
    //万份收益阀值(大于0；小于等于10)
    public static final BigDecimal THRESHOLD_IPTT_MIN = new BigDecimal(0);
    public static final BigDecimal THRESHOLD_IPTT_MAX = new BigDecimal(10);
    //七日年化(大于0；小于等于15%)
    public static final BigDecimal THRESHOLD_PSD_MIN = new BigDecimal(0);
    public static final BigDecimal THRESHOLD_PSD_MAX = new BigDecimal(0.15);
    // 起购金额
    public static final BigDecimal THRESHOLD_PLM_MIN = new BigDecimal(0);
    public static final BigDecimal THRESHOLD_PLM_MAX = new BigDecimal(5000000);
    // 基金规模
    public static final BigDecimal THRESHOLD_LTA = new BigDecimal(50000000);
    // 风险等级
    public static final String RISK_LEVEL_LOW = "0";
    public static final String RISK_LEVEL_MID = "1";
    public static final String RISK_LEVEL_HIGH = "2";
    // 取现到账
    public static final Integer FAST_REDEEM = 1;
    public static final Integer GENERAL_REDEEM = 0;

    /** 监控阀值类型 */
    public static final String MONITOR_TYPE_IPTT = "万份收益";
    public static final String MONITOR_TYPE_PSD = "七日年化利率";
    public static final String MONITOR_TYPE_PLM = "起购金额";
    public static final String MONITOR_TYPE_LTA = "基金规模";
    public static final String MONITOR_TYPE_COMPANY = "基金公司GUID";
    public static final String MONITOR_TYPE_RISK_LEVEL = "风险等级值";
    public static final String MONITOR_TYPE_REDEEM = "取现类型";

}
