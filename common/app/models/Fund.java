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
@Table(name = "p_fund", schema = "public")
public class Fund extends IdEntity {
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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fund that = (Fund) o;

        if (getId() != that.getId()) return false;
        if (charge != null ? !charge.equals(that.charge) : that.charge != null) return false;
        if (chiName != null ? !chiName.equals(that.chiName) : that.chiName != null) return false;
        if (chiNameAbbr != null ? !chiNameAbbr.equals(that.chiNameAbbr) : that.chiNameAbbr != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (engName != null ? !engName.equals(that.engName) : that.engName != null) return false;
        if (engNameAbbr != null ? !engNameAbbr.equals(that.engNameAbbr) : that.engNameAbbr != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null) return false;
        if (fundCompanyId != null ? !fundCompanyId.equals(that.fundCompanyId) : that.fundCompanyId != null)
            return false;
        if (fundScale != null ? !fundScale.equals(that.fundScale) : that.fundScale != null) return false;
        if (fundType != null ? !fundType.equals(that.fundType) : that.fundType != null) return false;
        if (initBuyedCount != null ? !initBuyedCount.equals(that.initBuyedCount) : that.initBuyedCount != null)
            return false;
        if (investPeriod != null ? !investPeriod.equals(that.investPeriod) : that.investPeriod != null) return false;
        if (isApply != null ? !isApply.equals(that.isApply) : that.isApply != null) return false;
        if (isRedemption != null ? !isRedemption.equals(that.isRedemption) : that.isRedemption != null) return false;
        if (lowestRedemption != null ? !lowestRedemption.equals(that.lowestRedemption) : that.lowestRedemption != null)
            return false;
        if (millionOfProfit != null ? !millionOfProfit.equals(that.millionOfProfit) : that.millionOfProfit != null)
            return false;
        if (minApplyAmount != null ? !minApplyAmount.equals(that.minApplyAmount) : that.minApplyAmount != null)
            return false;
        if (navDate != null ? !navDate.equals(that.navDate) : that.navDate != null) return false;
        if (oneMonthBuyedCount != null ? !oneMonthBuyedCount.equals(that.oneMonthBuyedCount) : that.oneMonthBuyedCount != null)
            return false;
        if (oneWeekProfit != null ? !oneWeekProfit.equals(that.oneWeekProfit) : that.oneWeekProfit != null)
            return false;
        if (productStatus != null ? !productStatus.equals(that.productStatus) : that.productStatus != null)
            return false;
        if (riskLevel != null ? !riskLevel.equals(that.riskLevel) : that.riskLevel != null) return false;
        if (scaleTime != null ? !scaleTime.equals(that.scaleTime) : that.scaleTime != null) return false;
        if (secuAbbr != null ? !secuAbbr.equals(that.secuAbbr) : that.secuAbbr != null) return false;
        if (supplierCode != null ? !supplierCode.equals(that.supplierCode) : that.supplierCode != null) return false;
        if (toAccountType != null ? !toAccountType.equals(that.toAccountType) : that.toAccountType != null)
            return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (minApplyAmount != null ? minApplyAmount.hashCode() : 0);
        result = 31 * result + (lowestRedemption != null ? lowestRedemption.hashCode() : 0);
        result = 31 * result + (millionOfProfit != null ? millionOfProfit.hashCode() : 0);
        result = 31 * result + (oneWeekProfit != null ? oneWeekProfit.hashCode() : 0);
        result = 31 * result + (navDate != null ? navDate.hashCode() : 0);
        result = 31 * result + (isApply != null ? isApply.hashCode() : 0);
        result = 31 * result + (isRedemption != null ? isRedemption.hashCode() : 0);
        result = 31 * result + (productStatus != null ? productStatus.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (fundCompanyId != null ? fundCompanyId.hashCode() : 0);
        result = 31 * result + (chiName != null ? chiName.hashCode() : 0);
        result = 31 * result + (chiNameAbbr != null ? chiNameAbbr.hashCode() : 0);
        result = 31 * result + (engName != null ? engName.hashCode() : 0);
        result = 31 * result + (engNameAbbr != null ? engNameAbbr.hashCode() : 0);
        result = 31 * result + (secuAbbr != null ? secuAbbr.hashCode() : 0);
        result = 31 * result + (fundScale != null ? fundScale.hashCode() : 0);
        result = 31 * result + (fundType != null ? fundType.hashCode() : 0);
        result = 31 * result + (scaleTime != null ? scaleTime.hashCode() : 0);
        result = 31 * result + (investPeriod != null ? investPeriod.hashCode() : 0);
        result = 31 * result + (charge != null ? charge.hashCode() : 0);
        result = 31 * result + (toAccountType != null ? toAccountType.hashCode() : 0);
        result = 31 * result + (supplierCode != null ? supplierCode.hashCode() : 0);
        result = 31 * result + (riskLevel != null ? riskLevel.hashCode() : 0);
        result = 31 * result + (initBuyedCount != null ? initBuyedCount.hashCode() : 0);
        result = 31 * result + (oneMonthBuyedCount != null ? oneMonthBuyedCount.hashCode() : 0);
        return result;
    }
}
