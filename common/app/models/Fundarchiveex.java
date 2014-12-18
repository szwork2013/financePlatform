package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2014/12/18.
 */
@Entity
public class Fundarchiveex {
    private String smGuid;

    @Id
    @javax.persistence.Column(name = "sm_guid")
    public String getSmGuid() {
        return smGuid;
    }

    public void setSmGuid(String smGuid) {
        this.smGuid = smGuid;
    }

    private String frontEndCode;

    @Basic
    @javax.persistence.Column(name = "front_end_code")
    public String getFrontEndCode() {
        return frontEndCode;
    }

    public void setFrontEndCode(String frontEndCode) {
        this.frontEndCode = frontEndCode;
    }

    private String backEndCode;

    @Basic
    @javax.persistence.Column(name = "back_end_code")
    public String getBackEndCode() {
        return backEndCode;
    }

    public void setBackEndCode(String backEndCode) {
        this.backEndCode = backEndCode;
    }

    private String fundCode;

    @Basic
    @javax.persistence.Column(name = "fund_code")
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    private String aliasCode;

    @Basic
    @javax.persistence.Column(name = "alias_code")
    public String getAliasCode() {
        return aliasCode;
    }

    public void setAliasCode(String aliasCode) {
        this.aliasCode = aliasCode;
    }

    private String pinYinCode;

    @Basic
    @javax.persistence.Column(name = "pin_yin_code")
    public String getPinYinCode() {
        return pinYinCode;
    }

    public void setPinYinCode(String pinYinCode) {
        this.pinYinCode = pinYinCode;
    }

    private String fundName;

    @Basic
    @javax.persistence.Column(name = "fund_name")
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    private String fundNameAbbr;

    @Basic
    @javax.persistence.Column(name = "fund_name_abbr")
    public String getFundNameAbbr() {
        return fundNameAbbr;
    }

    public void setFundNameAbbr(String fundNameAbbr) {
        this.fundNameAbbr = fundNameAbbr;
    }

    private String fundNameAbbr2;

    @Basic
    @javax.persistence.Column(name = "fund_name_abbr2")
    public String getFundNameAbbr2() {
        return fundNameAbbr2;
    }

    public void setFundNameAbbr2(String fundNameAbbr2) {
        this.fundNameAbbr2 = fundNameAbbr2;
    }

    private String establishmentDate;

    @Basic
    @javax.persistence.Column(name = "establishment_date")
    public String getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(String establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    private String expireDate;

    @Basic
    @javax.persistence.Column(name = "expire_date")
    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    private String manager;

    @Basic
    @javax.persistence.Column(name = "manager")
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    private String investAdvisorGuid;

    @Basic
    @javax.persistence.Column(name = "invest_advisor_guid")
    public String getInvestAdvisorGuid() {
        return investAdvisorGuid;
    }

    public void setInvestAdvisorGuid(String investAdvisorGuid) {
        this.investAdvisorGuid = investAdvisorGuid;
    }

    private String investAdvisorName;

    @Basic
    @javax.persistence.Column(name = "invest_advisor_name")
    public String getInvestAdvisorName() {
        return investAdvisorName;
    }

    public void setInvestAdvisorName(String investAdvisorName) {
        this.investAdvisorName = investAdvisorName;
    }

    private String trusteeName;

    @Basic
    @javax.persistence.Column(name = "trustee_name")
    public String getTrusteeName() {
        return trusteeName;
    }

    public void setTrusteeName(String trusteeName) {
        this.trusteeName = trusteeName;
    }

    private Integer fundType;

    @Basic
    @javax.persistence.Column(name = "fund_type")
    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    private String fundTypeName;

    @Basic
    @javax.persistence.Column(name = "fund_type_name")
    public String getFundTypeName() {
        return fundTypeName;
    }

    public void setFundTypeName(String fundTypeName) {
        this.fundTypeName = fundTypeName;
    }

    private Integer fundState;

    @Basic
    @javax.persistence.Column(name = "fund_state")
    public Integer getFundState() {
        return fundState;
    }

    public void setFundState(Integer fundState) {
        this.fundState = fundState;
    }

    private Integer investmentType;

    @Basic
    @javax.persistence.Column(name = "investment_type")
    public Integer getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(Integer investmentType) {
        this.investmentType = investmentType;
    }

    private String investmentTypeName;

    @Basic
    @javax.persistence.Column(name = "investment_type_name")
    public String getInvestmentTypeName() {
        return investmentTypeName;
    }

    public void setInvestmentTypeName(String investmentTypeName) {
        this.investmentTypeName = investmentTypeName;
    }

    private Integer investmentStyle;

    @Basic
    @javax.persistence.Column(name = "investment_style")
    public Integer getInvestmentStyle() {
        return investmentStyle;
    }

    public void setInvestmentStyle(Integer investmentStyle) {
        this.investmentStyle = investmentStyle;
    }

    private String investmentStyleName;

    @Basic
    @javax.persistence.Column(name = "investment_style_name")
    public String getInvestmentStyleName() {
        return investmentStyleName;
    }

    public void setInvestmentStyleName(String investmentStyleName) {
        this.investmentStyleName = investmentStyleName;
    }

    private String investmentOrientation;

    @Basic
    @javax.persistence.Column(name = "investment_orientation")
    public String getInvestmentOrientation() {
        return investmentOrientation;
    }

    public void setInvestmentOrientation(String investmentOrientation) {
        this.investmentOrientation = investmentOrientation;
    }

    private String investmentTarget;

    @Basic
    @javax.persistence.Column(name = "investment_target")
    public String getInvestmentTarget() {
        return investmentTarget;
    }

    public void setInvestmentTarget(String investmentTarget) {
        this.investmentTarget = investmentTarget;
    }

    private String investmentField;

    @Basic
    @javax.persistence.Column(name = "investment_field")
    public String getInvestmentField() {
        return investmentField;
    }

    public void setInvestmentField(String investmentField) {
        this.investmentField = investmentField;
    }

    private String performanceBenchMark;

    @Basic
    @javax.persistence.Column(name = "performance_bench_mark")
    public String getPerformanceBenchMark() {
        return performanceBenchMark;
    }

    public void setPerformanceBenchMark(String performanceBenchMark) {
        this.performanceBenchMark = performanceBenchMark;
    }

    private String profitDistributionRule;

    @Basic
    @javax.persistence.Column(name = "profit_distribution_rule")
    public String getProfitDistributionRule() {
        return profitDistributionRule;
    }

    public void setProfitDistributionRule(String profitDistributionRule) {
        this.profitDistributionRule = profitDistributionRule;
    }

    private String assetAllocation;

    @Basic
    @javax.persistence.Column(name = "asset_allocation")
    public String getAssetAllocation() {
        return assetAllocation;
    }

    public void setAssetAllocation(String assetAllocation) {
        this.assetAllocation = assetAllocation;
    }

    private String briefIntro;

    @Basic
    @javax.persistence.Column(name = "brief_intro")
    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    private String riskReturnCharacter;

    @Basic
    @javax.persistence.Column(name = "risk_return_character")
    public String getRiskReturnCharacter() {
        return riskReturnCharacter;
    }

    public void setRiskReturnCharacter(String riskReturnCharacter) {
        this.riskReturnCharacter = riskReturnCharacter;
    }

    private String fundManagementFees;

    @Basic
    @javax.persistence.Column(name = "fund_management_fees")
    public String getFundManagementFees() {
        return fundManagementFees;
    }

    public void setFundManagementFees(String fundManagementFees) {
        this.fundManagementFees = fundManagementFees;
    }

    private String fundTrusteeFees;

    @Basic
    @javax.persistence.Column(name = "fund_trustee_fees")
    public String getFundTrusteeFees() {
        return fundTrusteeFees;
    }

    public void setFundTrusteeFees(String fundTrusteeFees) {
        this.fundTrusteeFees = fundTrusteeFees;
    }

    private BigDecimal foundedSize;

    @Basic
    @javax.persistence.Column(name = "founded_size")
    public BigDecimal getFoundedSize() {
        return foundedSize;
    }

    public void setFoundedSize(BigDecimal foundedSize) {
        this.foundedSize = foundedSize;
    }

    private BigDecimal foundedHoldShares;

    @Basic
    @javax.persistence.Column(name = "founded_hold_shares")
    public BigDecimal getFoundedHoldShares() {
        return foundedHoldShares;
    }

    public void setFoundedHoldShares(BigDecimal foundedHoldShares) {
        this.foundedHoldShares = foundedHoldShares;
    }

    private BigDecimal foundedTotalAsset;

    @Basic
    @javax.persistence.Column(name = "founded_total_asset")
    public BigDecimal getFoundedTotalAsset() {
        return foundedTotalAsset;
    }

    public void setFoundedTotalAsset(BigDecimal foundedTotalAsset) {
        this.foundedTotalAsset = foundedTotalAsset;
    }

    private BigDecimal latestSize;

    @Basic
    @javax.persistence.Column(name = "latest_size")
    public BigDecimal getLatestSize() {
        return latestSize;
    }

    public void setLatestSize(BigDecimal latestSize) {
        this.latestSize = latestSize;
    }

    private BigDecimal latestHoldShares;

    @Basic
    @javax.persistence.Column(name = "latest_hold_shares")
    public BigDecimal getLatestHoldShares() {
        return latestHoldShares;
    }

    public void setLatestHoldShares(BigDecimal latestHoldShares) {
        this.latestHoldShares = latestHoldShares;
    }

    private BigDecimal latestTotalAsset;

    @Basic
    @javax.persistence.Column(name = "latest_total_asset")
    public BigDecimal getLatestTotalAsset() {
        return latestTotalAsset;
    }

    public void setLatestTotalAsset(BigDecimal latestTotalAsset) {
        this.latestTotalAsset = latestTotalAsset;
    }

    private Integer isdeleted;

    @Basic
    @javax.persistence.Column(name = "isdeleted")
    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    private String updatetime;

    @Basic
    @javax.persistence.Column(name = "updatetime")
    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

}
