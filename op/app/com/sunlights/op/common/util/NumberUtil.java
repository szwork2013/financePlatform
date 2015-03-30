package com.sunlights.op.common.util;

import java.math.BigDecimal;

/**
 * Created by xuelong.gu on 2014/11/26.
 */
public class NumberUtil {

    /**
     * 利率格式化
     * @param rate
     * @return
     */
    public static String formatRate(BigDecimal rate){
        return rate.multiply(new BigDecimal(100)).toPlainString()+"%";
    }
}
