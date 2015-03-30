package com.sunlights.op.vo;

import com.sunlights.common.FundCategory;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.op.common.util.NumberUtil;
import models.FundCompany;
import models.FundNav;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: FundManageVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class FundManageVo {
    private String fundcode;
    private String aliascode;
    private String fundname;
    private String fundnameabbr;
    private String currDate;
    private Integer fundType;
    private Integer investmentType;
    private BigDecimal netvalue;
    private BigDecimal totalNetvalue;
    private BigDecimal percent;
    private BigDecimal incomePerTenThousand;
    private BigDecimal percentSevenDays;
    private BigDecimal yield1M;
    private BigDecimal yield3M;
    private BigDecimal yield6M;
    private BigDecimal yield12M;
    private BigDecimal yieldThisYear;
    private BigDecimal cgs3Year;
    private BigDecimal lastestTotalAsset;
    private Integer onSale;
    private String riskLevel;
    private String shareType;
    private Integer purchaseState;
    private Integer subscribeState;
    private Integer aipState;
    private Integer recommendation;
    private BigDecimal chargeRateValue;
    private BigDecimal discount;
    private BigDecimal saleChargeRateValue;
    private Integer isMonetary;
    private Integer isStf;
    private BigDecimal purchaseLimitMin;
    private BigDecimal redeemLimitMin;
    private Integer rapidRedeem;
    private String iaGuid;
    private String fundManagementFees;
    private String fundTrusteeFees;
    private Date createTime;
    private Date updateTime;

    private String companyName;

    public FundManageVo() {
        super();
    }

    public FundManageVo(FundNav fundNav, FundCompany fundCompany) {
        inFundNav(fundNav);
        this.companyName = fundCompany.getCompanyName();
    }

    public void inFundNav(FundNav fundNav) {
        try {
            ConverterUtil.fromEntity(this, fundNav);
        } catch (ConverterException e) {
            e.printStackTrace();
        }
    }

    public FundNav convertToFund() {
        FundNav fundNav = new FundNav();
        try {
            ConverterUtil.toEntity(fundNav, this);
        } catch (ConverterException e) {
            e.printStackTrace();
        }
        return fundNav;
    }


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

    public String getFundTypeFormat() {
        return isMonetary == 1 ? FundCategory.MONETARY.getDescription() : (isStf == 1 ? FundCategory.STF.getDescription() : "");
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

    public String getPercentSevenDaysFormat() {
        return NumberUtil.formatRate(percentSevenDays);
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
