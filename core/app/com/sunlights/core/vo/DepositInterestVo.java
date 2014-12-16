package com.sunlights.core.vo;

import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.ConverterUtil;
import models.DepositInterest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yuan on 2014/12/15.
 */
public class DepositInterestVo {

    private Date date;
    private BigDecimal current;

    private BigDecimal fullThreeMonths;


    private BigDecimal fullHalfYear;


    private BigDecimal fullOneYear;

    private BigDecimal fullTwoYear;

    private BigDecimal fullThreeYear;

    private BigDecimal fullFiveYear;

    private BigDecimal shortageOneYear;

    private BigDecimal shortageThreeYear;

    private BigDecimal shortageFiveYear;

    private BigDecimal agreementSavings;

    private BigDecimal callDepositOneDay;

    private BigDecimal callDepositOneWeek;

    private Date createTime;

    private Date updateTime;

    public DepositInterestVo() {
        super();
    }


    public DepositInterestVo(DepositInterest depositInterest) {
        inDepositInterest(depositInterest);
    }


    public void inDepositInterest(DepositInterest depositInterest) {
        try {
            ConverterUtil.fromEntity(this,depositInterest);
        } catch (ConverterException e) {
            e.printStackTrace();
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getCurrent() {
        return current;
    }

    public void setCurrent(BigDecimal current) {
        this.current = current;
    }

    public BigDecimal getFullThreeMonths() {
        return fullThreeMonths;
    }

    public void setFullThreeMonths(BigDecimal fullThreeMonths) {
        this.fullThreeMonths = fullThreeMonths;
    }

    public BigDecimal getFullHalfYear() {
        return fullHalfYear;
    }

    public void setFullHalfYear(BigDecimal fullHalfYear) {
        this.fullHalfYear = fullHalfYear;
    }

    public BigDecimal getFullOneYear() {
        return fullOneYear;
    }

    public void setFullOneYear(BigDecimal fullOneYear) {
        this.fullOneYear = fullOneYear;
    }

    public BigDecimal getFullTwoYear() {
        return fullTwoYear;
    }

    public void setFullTwoYear(BigDecimal fullTwoYear) {
        this.fullTwoYear = fullTwoYear;
    }

    public BigDecimal getFullThreeYear() {
        return fullThreeYear;
    }

    public void setFullThreeYear(BigDecimal fullThreeYear) {
        this.fullThreeYear = fullThreeYear;
    }

    public BigDecimal getFullFiveYear() {
        return fullFiveYear;
    }

    public void setFullFiveYear(BigDecimal fullFiveYear) {
        this.fullFiveYear = fullFiveYear;
    }

    public BigDecimal getShortageOneYear() {
        return shortageOneYear;
    }

    public void setShortageOneYear(BigDecimal shortageOneYear) {
        this.shortageOneYear = shortageOneYear;
    }

    public BigDecimal getShortageThreeYear() {
        return shortageThreeYear;
    }

    public void setShortageThreeYear(BigDecimal shortageThreeYear) {
        this.shortageThreeYear = shortageThreeYear;
    }

    public BigDecimal getShortageFiveYear() {
        return shortageFiveYear;
    }

    public void setShortageFiveYear(BigDecimal shortageFiveYear) {
        this.shortageFiveYear = shortageFiveYear;
    }

    public BigDecimal getAgreementSavings() {
        return agreementSavings;
    }

    public void setAgreementSavings(BigDecimal agreementSavings) {
        this.agreementSavings = agreementSavings;
    }

    public BigDecimal getCallDepositOneDay() {
        return callDepositOneDay;
    }

    public void setCallDepositOneDay(BigDecimal callDepositOneDay) {
        this.callDepositOneDay = callDepositOneDay;
    }

    public BigDecimal getCallDepositOneWeek() {
        return callDepositOneWeek;
    }

    public void setCallDepositOneWeek(BigDecimal callDepositOneWeek) {
        this.callDepositOneWeek = callDepositOneWeek;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
