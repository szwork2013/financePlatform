package models;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yuan on 2014/12/15.
 */
@Entity
@javax.persistence.Table(name = "p_deposit_interest")
public class DepositInterest extends IdEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "\"DATE\"")
    private Date date;

    @javax.persistence.Column(name = "\"CURRENT\"")
    private BigDecimal current;

    @javax.persistence.Column(name = "full_three_months")
    private BigDecimal fullThreeMonths;

    @javax.persistence.Column(name = "full_half_year")
    private BigDecimal fullHalfYear;

    @javax.persistence.Column(name = "full_one_year")
    private BigDecimal fullOneYear;

    @javax.persistence.Column(name = "full_two_year")
    private BigDecimal fullTwoYear;

    @javax.persistence.Column(name = "full_three_year")
    private BigDecimal fullThreeYear;

    @javax.persistence.Column(name = "full_five_year")
    private BigDecimal fullFiveYear;

    @javax.persistence.Column(name = "shortage_one_year")
    private BigDecimal shortageOneYear;

    @javax.persistence.Column(name = "shortage_three_year")
    private BigDecimal shortageThreeYear;

    @javax.persistence.Column(name = "shortage_five_year")
    private BigDecimal shortageFiveYear;

    @javax.persistence.Column(name = "agreement_savings")
    private BigDecimal agreementSavings;

    @javax.persistence.Column(name = "call_deposit_one_day")
    private BigDecimal callDepositOneDay;

    @javax.persistence.Column(name = "call_deposit_one_week")
    private BigDecimal callDepositOneWeek;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "update_time")
    private Date updateTime;


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
