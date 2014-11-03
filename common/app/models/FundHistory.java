package models;


import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>Project: fsp</p>
 * <p>Title: Fund.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
@Table(name = "P_FUND_HISTORY")
public class FundHistory extends IdEntity {

    @Column(name = "MIN_APPLY_AMOUNT", precision = 18, scale = 4)
    private BigDecimal minApplyAmount;//最小申购额度
    @Column(name = "LOWEST_REDEMPTION")
    private int lowestRedemption;//最低赎回份额
    @Column(name = "ONE_YEAR_PROFIT", precision = 18, scale = 4)
    private BigDecimal oneYearProfit;//年化收益
    @Column(name = "MILLION_OF_PROFIT", precision = 18, scale = 4)
    private BigDecimal millionOfProfit;//万份收益
    @Column(name = "ONE_WEEK_PROFIT", precision = 18, scale = 4)
    private BigDecimal oneWeekProfit;//一周年化收益
    @Column(name = "NAV_DATE")
    private Timestamp navDate;//净值日期
    @Column(name = "IS_APPLY")
    private String isApply;//可否申购
    @Column(name = "IS_REDEMPTION")
    private String isRedemption;//可否赎回
    @Column(name = "PRODUCT_STATUS")
    private String productStatus;//基金状态


    @Column(name = "COMPANY_NAME", length = 10)
    private String companyName;//基金公司名称
    @Column(name = "FUND_CODE", length = 10)
    private String fundCode;//基金代码
    @Column(name = "CHI_NAME", length = 100)
    private String chiName;//中文名称
    @Column(name = "CHI_NAME_ABBR", length = 50)
    private String chiNameAbbr;//中文名称简称
    @Column(name = "ENG_NAME", length = 100)
    private String engName;//英文名称
    @Column(name = "ENG_NAME_ABBR", length = 50)
    private String engNameAbbr;//英文名称简称
    @Column(name = "SECU_ABBR", length = 20)
    private String secuAbbr;//证券简称
    @Column(name = "FUND_SCALE", precision = 18, scale = 4)
    private BigDecimal fundScale;//基金规模
    @Column(name = "FUND_TYPE")
    private String fundType;//基金类型
    @Column(name = "INVEST_PERIOD")
    private String investPeriod;//投资期限
    @Column(name = "CHARGE", precision = 18, scale = 4)
    private BigDecimal charge;//手续费
    @Column(name = "SUPPILER_ID")
    private Long supplierId;//供应商ID
    @Column(name = "RISK_LEVEL")
    private String riskLevel;//风险等级
    @Column(name = "TO_ACCOUNT_TYPE")
    private String toAccountType;//到帐方式
    @Column(name = "INIT_BUYED_COUNT")
    private Long initBuyedCount;
    @Column(name = "ONE_MONTH_BUYED_COUNT")
    private Long oneMonthBuyedCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "CREATE_BY", length = 30)
    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "UPDATE_BY", length = 30)
    private String updateBy;


    public BigDecimal getMinApplyAmount() {
        return minApplyAmount;
    }

    public void setMinApplyAmount(BigDecimal minApplyAmount) {
        this.minApplyAmount = minApplyAmount;
    }

    public int getLowestRedemption() {
        return lowestRedemption;
    }

    public void setLowestRedemption(int lowestRedemption) {
        this.lowestRedemption = lowestRedemption;
    }

    public BigDecimal getOneYearProfit() {
        return oneYearProfit;
    }

    public void setOneYearProfit(BigDecimal oneYearProfit) {
        this.oneYearProfit = oneYearProfit;
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

    public Timestamp getNavDate() {
        return navDate;
    }

    public void setNavDate(Timestamp navDate) {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
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

    public String getInvestPeriod() {
        return investPeriod;
    }

    public void setInvestPeriod(String investPeriod) {
        this.investPeriod = investPeriod;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getToAccountType() {
        return toAccountType;
    }

    public void setToAccountType(String toAccountType) {
        this.toAccountType = toAccountType;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
}
