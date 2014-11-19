package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: PFundHistory.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
@Table(name = "p_fund_history", schema = "public")
public class FundHistory extends IdEntity {

    @Column(name = "fund_code")
    private String fundCode;

    @Column(name = "min_apply_amount")
    private BigDecimal minApplyAmount;

    @Column(name = "lowest_redemption")
    private BigDecimal lowestRedemption;

    @Column(name = "million_of_profit")
    private BigDecimal millionOfProfit;

    @Column(name = "one_week_profit")
    private BigDecimal oneWeekProfit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "nav_date")
    private Date navDate;

    @Column(name = "is_apply")
    private String isApply;
    @Column(name = "is_redemption")
    private String isRedemption;
    @Column(name = "product_status")
    private String productStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "fund_company_id")
    private String fundCompanyId;

    @Column(name = "chi_name")
    private String chiName;

    @Column(name = "chi_name_abbr")
    private String chiNameAbbr;

    @Column(name = "eng_name")
    private String engName;

    @Column(name = "eng_name_abbr")
    private String engNameAbbr;

    @Column(name = "secu_abbr")
    private String secuAbbr;

    @Column(name = "fund_scale", precision = 18, scale = 5)
    private BigDecimal fundScale;

    @Column(name = "fund_type")
    private String fundType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "scale_time")
    private Date scaleTime;

    @Column(name = "invest_period")
    private String investPeriod;

    @Column(name = "charge")
    private Long charge;

    @Column(name = "to_account_type")
    private String toAccountType;

    @Column(name = "supplier_code")
    private String supplierCode;

    @Column(name = "risk_level")
    private String riskLevel;


    @Column(name = "init_buyed_count")
    private Long initBuyedCount;

    @Column(name = "one_month_buyed_count")
    private Long oneMonthBuyedCount;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public BigDecimal getMinApplyAmount() {
        return minApplyAmount;
    }

    public void setMinApplyAmount(BigDecimal minApplyAmount) {
        this.minApplyAmount = minApplyAmount;
    }

    public BigDecimal getLowestRedemption() {
        return lowestRedemption;
    }

    public void setLowestRedemption(BigDecimal lowestRedemption) {
        this.lowestRedemption = lowestRedemption;
    }

    public BigDecimal getMillionOfProfit() {
        return millionOfProfit;
    }

    public void setMillionOfProfit(BigDecimal millionOfProfit) {
        this.millionOfProfit = millionOfProfit;
    }

    public BigDecimal getOneWeekProfit() {
        return oneWeekProfit;
    }

    public void setOneWeekProfit(BigDecimal oneWeekProfit) {
        this.oneWeekProfit = oneWeekProfit;
    }

    public Date getNavDate() {
        return navDate;
    }

    public void setNavDate(Date navDate) {
        this.navDate = navDate;
    }

    public String getIsApply() {
        return isApply;
    }

    public void setIsApply(String isApply) {
        this.isApply = isApply;
    }

    public String getIsRedemption() {
        return isRedemption;
    }

    public void setIsRedemption(String isRedemption) {
        this.isRedemption = isRedemption;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getFundCompanyId() {
        return fundCompanyId;
    }

    public void setFundCompanyId(String fundCompanyId) {
        this.fundCompanyId = fundCompanyId;
    }

    public String getChiName() {
        return chiName;
    }

    public void setChiName(String chiName) {
        this.chiName = chiName;
    }

    public String getChiNameAbbr() {
        return chiNameAbbr;
    }

    public void setChiNameAbbr(String chiNameAbbr) {
        this.chiNameAbbr = chiNameAbbr;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getEngNameAbbr() {
        return engNameAbbr;
    }

    public void setEngNameAbbr(String engNameAbbr) {
        this.engNameAbbr = engNameAbbr;
    }

    public String getSecuAbbr() {
        return secuAbbr;
    }

    public void setSecuAbbr(String secuAbbr) {
        this.secuAbbr = secuAbbr;
    }

    public BigDecimal getFundScale() {
        return fundScale;
    }

    public void setFundScale(BigDecimal fundScale) {
        this.fundScale = fundScale;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public Date getScaleTime() {
        return scaleTime;
    }

    public void setScaleTime(Date scaleTime) {
        this.scaleTime = scaleTime;
    }

    public String getInvestPeriod() {
        return investPeriod;
    }

    public void setInvestPeriod(String investPeriod) {
        this.investPeriod = investPeriod;
    }

    public Long getCharge() {
        return charge;
    }

    public void setCharge(Long charge) {
        this.charge = charge;
    }

    public String getToAccountType() {
        return toAccountType;
    }

    public void setToAccountType(String toAccountType) {
        this.toAccountType = toAccountType;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Long getInitBuyedCount() {
        return initBuyedCount;
    }

    public void setInitBuyedCount(Long initBuyedCount) {
        this.initBuyedCount = initBuyedCount;
    }

    public Long getOneMonthBuyedCount() {
        return oneMonthBuyedCount;
    }

    public void setOneMonthBuyedCount(Long oneMonthBuyedCount) {
        this.oneMonthBuyedCount = oneMonthBuyedCount;
    }
}

