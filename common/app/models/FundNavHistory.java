package models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: FundNavHistory.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
@javax.persistence.Table(name = "fundnav_history", schema = "public")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FundNavHistory {

    @Id
    @javax.persistence.Column(name = "fundcode")
    private String fundcode;

    @javax.persistence.Column(name = "aliascode")
    private String aliascode;

    @javax.persistence.Column(name = "fundname")
    private String fundname;

    @javax.persistence.Column(name = "fundnameabbr")
    private String fundnameabbr;

    @javax.persistence.Column(name = "curr_date")
    private String currDate;

    @javax.persistence.Column(name = "fund_type")
    private Integer fundType;

    @javax.persistence.Column(name = "investment_type")
    private Integer investmentType;

    @javax.persistence.Column(name = "netvalue")
    private BigDecimal netvalue;

    @javax.persistence.Column(name = "total_netvalue")
    private BigDecimal totalNetvalue;

    @javax.persistence.Column(name = "percent")
    private BigDecimal percent;

    @javax.persistence.Column(name = "income_per_ten_thousand")
    private BigDecimal incomePerTenThousand;

    @javax.persistence.Column(name = "percent_seven_days")
    private BigDecimal percentSevenDays;

    @javax.persistence.Column(name = "yield_1m")
    private BigDecimal yield1M;

    @javax.persistence.Column(name = "yield_3m")
    private BigDecimal yield3M;

    @javax.persistence.Column(name = "yield_6m")
    private BigDecimal yield6M;

    @javax.persistence.Column(name = "yield_12m")
    private BigDecimal yield12M;

    @javax.persistence.Column(name = "yield_this_year")
    private BigDecimal yieldThisYear;

    @javax.persistence.Column(name = "cgs_3_year")
    private BigDecimal cgs3Year;

    @javax.persistence.Column(name = "lastest_total_asset")
    private BigDecimal lastestTotalAsset;

    @javax.persistence.Column(name = "on_sale")
    private Integer onSale;

    @javax.persistence.Column(name = "risk_level")
    private String riskLevel;

    @javax.persistence.Column(name = "share_type")
    private String shareType;

    @javax.persistence.Column(name = "purchase_state")
    private Integer purchaseState;

    @javax.persistence.Column(name = "subscribe_state")
    private Integer subscribeState;

    @javax.persistence.Column(name = "aip_state")
    private Integer aipState;

    @javax.persistence.Column(name = "recommendation")
    private Integer recommendation;

    @javax.persistence.Column(name = "charge_rate_value")
    private BigDecimal chargeRateValue;

    @javax.persistence.Column(name = "discount")
    private BigDecimal discount;

    @javax.persistence.Column(name = "sale_charge_rate_value")
    private BigDecimal saleChargeRateValue;

    @javax.persistence.Column(name = "is_monetary")
    private Integer isMonetary;

    @javax.persistence.Column(name = "is_stf")
    private Integer isStf;

    @javax.persistence.Column(name = "purchase_limit_min")
    private BigDecimal purchaseLimitMin;

    @javax.persistence.Column(name = "redeem_limit_min")
    private BigDecimal redeemLimitMin;

    @javax.persistence.Column(name = "rapid_redeem")
    private Integer rapidRedeem;

    @javax.persistence.Column(name = "ia_guid")
    private String iaGuid;

    @javax.persistence.Column(name = "fund_management_fees")
    private String fundManagementFees;

    @javax.persistence.Column(name = "fund_trustee_fees")
    private String fundTrusteeFees;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "update_time")
    private Date updateTime;

    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }

    public String getAliascode() {
        return aliascode;
    }

    public void setAliascode(String aliascode) {
        this.aliascode = aliascode;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public String getFundnameabbr() {
        return fundnameabbr;
    }

    public void setFundnameabbr(String fundnameabbr) {
        this.fundnameabbr = fundnameabbr;
    }

    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }

    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    public Integer getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(Integer investmentType) {
        this.investmentType = investmentType;
    }

    public BigDecimal getNetvalue() {
        return netvalue;
    }

    public void setNetvalue(BigDecimal netvalue) {
        this.netvalue = netvalue;
    }

    public BigDecimal getTotalNetvalue() {
        return totalNetvalue;
    }

    public void setTotalNetvalue(BigDecimal totalNetvalue) {
        this.totalNetvalue = totalNetvalue;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public BigDecimal getIncomePerTenThousand() {
        return incomePerTenThousand;
    }

    public void setIncomePerTenThousand(BigDecimal incomePerTenThousand) {
        this.incomePerTenThousand = incomePerTenThousand;
    }

    public BigDecimal getPercentSevenDays() {
        return percentSevenDays;
    }

    public void setPercentSevenDays(BigDecimal percentSevenDays) {
        this.percentSevenDays = percentSevenDays;
    }

    public BigDecimal getYield1M() {
        return yield1M;
    }

    public void setYield1M(BigDecimal yield1M) {
        this.yield1M = yield1M;
    }

    public BigDecimal getYield3M() {
        return yield3M;
    }

    public void setYield3M(BigDecimal yield3M) {
        this.yield3M = yield3M;
    }

    public BigDecimal getYield6M() {
        return yield6M;
    }

    public void setYield6M(BigDecimal yield6M) {
        this.yield6M = yield6M;
    }

    public BigDecimal getYield12M() {
        return yield12M;
    }

    public void setYield12M(BigDecimal yield12M) {
        this.yield12M = yield12M;
    }

    public BigDecimal getYieldThisYear() {
        return yieldThisYear;
    }

    public void setYieldThisYear(BigDecimal yieldThisYear) {
        this.yieldThisYear = yieldThisYear;
    }

    public BigDecimal getCgs3Year() {
        return cgs3Year;
    }

    public void setCgs3Year(BigDecimal cgs3Year) {
        this.cgs3Year = cgs3Year;
    }

    public BigDecimal getLastestTotalAsset() {
        return lastestTotalAsset;
    }

    public void setLastestTotalAsset(BigDecimal lastestTotalAsset) {
        this.lastestTotalAsset = lastestTotalAsset;
    }

    public Integer getOnSale() {
        return onSale;
    }

    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public Integer getPurchaseState() {
        return purchaseState;
    }

    public void setPurchaseState(Integer purchaseState) {
        this.purchaseState = purchaseState;
    }

    public Integer getSubscribeState() {
        return subscribeState;
    }

    public void setSubscribeState(Integer subscribeState) {
        this.subscribeState = subscribeState;
    }

    public Integer getAipState() {
        return aipState;
    }

    public void setAipState(Integer aipState) {
        this.aipState = aipState;
    }

    public Integer getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Integer recommendation) {
        this.recommendation = recommendation;
    }

    public BigDecimal getChargeRateValue() {
        return chargeRateValue;
    }

    public void setChargeRateValue(BigDecimal chargeRateValue) {
        this.chargeRateValue = chargeRateValue;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getSaleChargeRateValue() {
        return saleChargeRateValue;
    }

    public void setSaleChargeRateValue(BigDecimal saleChargeRateValue) {
        this.saleChargeRateValue = saleChargeRateValue;
    }

    public Integer getIsMonetary() {
        return isMonetary;
    }

    public void setIsMonetary(Integer isMonetary) {
        this.isMonetary = isMonetary;
    }

    public Integer getIsStf() {
        return isStf;
    }

    public void setIsStf(Integer isStf) {
        this.isStf = isStf;
    }

    public BigDecimal getPurchaseLimitMin() {
        return purchaseLimitMin;
    }

    public void setPurchaseLimitMin(BigDecimal purchaseLimitMin) {
        this.purchaseLimitMin = purchaseLimitMin;
    }

    public BigDecimal getRedeemLimitMin() {
        return redeemLimitMin;
    }

    public void setRedeemLimitMin(BigDecimal redeemLimitMin) {
        this.redeemLimitMin = redeemLimitMin;
    }

    public Integer getRapidRedeem() {
        return rapidRedeem;
    }

    public void setRapidRedeem(Integer rapidRedeem) {
        this.rapidRedeem = rapidRedeem;
    }

    public String getIaGuid() {
        return iaGuid;
    }

    public void setIaGuid(String iaGuid) {
        this.iaGuid = iaGuid;
    }

    public String getFundManagementFees() {
        return fundManagementFees;
    }

    public void setFundManagementFees(String fundManagementFees) {
        this.fundManagementFees = fundManagementFees;
    }

    public String getFundTrusteeFees() {
        return fundTrusteeFees;
    }

    public void setFundTrusteeFees(String fundTrusteeFees) {
        this.fundTrusteeFees = fundTrusteeFees;
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
