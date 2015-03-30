package com.sunlights.op.vo.statistics;

import java.math.BigDecimal;

/**
 * Created by libaozhong on 2015/1/16.
 */
public class CustomerInOutSummaryVo {
    /*当前日期*/
    private String eventTime;
    /*累计转入人数*/
    private Integer cumulativeInCustomer;
    /*累计转入金额*/
    private BigDecimal totalCumulativeInCash;
    /*累计转出人数*/
    private Integer cumulativeOutCustomer;
    /*累计转出金额*/
    private BigDecimal totalCumulativeOutCash;
    /*日新增人数*/
    private Integer dayAddCustomer;
    /*日增转入金额*/
    private BigDecimal dayAddInCash;
    /*日增转出金额*/
    private BigDecimal dayAddOutCash;
    /*注册人数*/
    private Integer registeredCustomer;
    public Integer getCumulativeInCustomer() {
        return cumulativeInCustomer;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void setCumulativeInCustomer(Integer cumulativeInCustomer) {
        this.cumulativeInCustomer = cumulativeInCustomer;
    }

    public BigDecimal getTotalCumulativeInCash() {
        return totalCumulativeInCash;
    }

    public void setTotalCumulativeInCash(BigDecimal totalCumulativeInCash) {
        this.totalCumulativeInCash = totalCumulativeInCash;
    }

    public Integer getCumulativeOutCustomer() {
        return cumulativeOutCustomer;
    }

    public void setCumulativeOutCustomer(Integer cumulativeOutCustomer) {
        this.cumulativeOutCustomer = cumulativeOutCustomer;
    }

    public BigDecimal getTotalCumulativeOutCash() {
        return totalCumulativeOutCash;
    }

    public void setTotalCumulativeOutCash(BigDecimal totalCumulativeOutCash) {
        this.totalCumulativeOutCash = totalCumulativeOutCash;
    }

    public Integer getDayAddCustomer() {
        return dayAddCustomer;
    }

    public void setDayAddCustomer(Integer dayAddCustomer) {
        this.dayAddCustomer = dayAddCustomer;
    }

    public BigDecimal getDayAddInCash() {
        return dayAddInCash;
    }

    public void setDayAddInCash(BigDecimal dayAddInCash) {
        this.dayAddInCash = dayAddInCash;
    }

    public BigDecimal getDayAddOutCash() {
        return dayAddOutCash;
    }

    public void setDayAddOutCash(BigDecimal dayAddOutCash) {
        this.dayAddOutCash = dayAddOutCash;
    }

    public Integer getRegisteredCustomer() {
        return registeredCustomer;
    }

    public void setRegisteredCustomer(Integer registeredCustomer) {
        this.registeredCustomer = registeredCustomer;
    }


}
