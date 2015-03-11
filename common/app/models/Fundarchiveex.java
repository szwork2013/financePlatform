package models;

import com.sunlights.common.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;

/**
 * Created by Administrator on 2014/12/18.
 */
@Entity
@Table(name = "fundarchiveex")
public class Fundarchiveex extends IdEntity {

    @javax.persistence.Column(name = "sm_guid")
    private String smGuid;


    public String getSmGuid() {
        return smGuid;
    }

    public void setSmGuid(String smGuid) {
        this.smGuid = smGuid;
    }

    @Basic
    @javax.persistence.Column(name = "front_end_code")
    private String frontEndCode;


    public String getFrontEndCode() {
        return frontEndCode;
    }

    public void setFrontEndCode(String frontEndCode) {
        this.frontEndCode = frontEndCode;
    }

    @Basic
    @javax.persistence.Column(name = "back_end_code")
    private String backEndCode;


    public String getBackEndCode() {
        return backEndCode;
    }

    public void setBackEndCode(String backEndCode) {
        this.backEndCode = backEndCode;
    }

    @Basic
    @javax.persistence.Column(name = "fund_code")
    private String fundCode;


    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Basic
    @javax.persistence.Column(name = "alias_code")
    private String aliasCode;


    public String getAliasCode() {
        return aliasCode;
    }

    public void setAliasCode(String aliasCode) {
        this.aliasCode = aliasCode;
    }

    @Basic
    @javax.persistence.Column(name = "pin_yin_code")
    private String pinYinCode;


    public String getPinYinCode() {
        return pinYinCode;
    }

    public void setPinYinCode(String pinYinCode) {
        this.pinYinCode = pinYinCode;
    }

    @Basic
    @javax.persistence.Column(name = "fund_name")
    private String fundName;


    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Basic
    @javax.persistence.Column(name = "fund_name_abbr")
    private String fundNameAbbr;


    public String getFundNameAbbr() {
        return fundNameAbbr;
    }

    public void setFundNameAbbr(String fundNameAbbr) {
        this.fundNameAbbr = fundNameAbbr;
    }

    @Basic
    @javax.persistence.Column(name = "fund_name_abbr2")
    private String fundNameAbbr2;


    public String getFundNameAbbr2() {
        return fundNameAbbr2;
    }

    public void setFundNameAbbr2(String fundNameAbbr2) {
        this.fundNameAbbr2 = fundNameAbbr2;
    }

    @Basic
    @javax.persistence.Column(name = "establishment_date")
    private String establishmentDate;


    public String getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(String establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    @Basic
    @javax.persistence.Column(name = "expire_date")
    private String expireDate;


    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    @Basic
    @javax.persistence.Column(name = "manager")
    private String manager;


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Basic
    @javax.persistence.Column(name = "invest_advisor_guid")
    private String investAdvisorGuid;

    public String getInvestAdvisorGuid() {
        return investAdvisorGuid;
    }

    public void setInvestAdvisorGuid(String investAdvisorGuid) {
        this.investAdvisorGuid = investAdvisorGuid;
    }

    @Basic
    @javax.persistence.Column(name = "invest_advisor_name")
    private String investAdvisorName;


    public String getInvestAdvisorName() {
        return investAdvisorName;
    }

    public void setInvestAdvisorName(String investAdvisorName) {
        this.investAdvisorName = investAdvisorName;
    }

    @Basic
    @javax.persistence.Column(name = "trustee_name")
    private String trusteeName;


    public String getTrusteeName() {
        return trusteeName;
    }

    public void setTrusteeName(String trusteeName) {
        this.trusteeName = trusteeName;
    }

    @Basic
    @javax.persistence.Column(name = "fund_type")
    private Integer fundType;


    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    @Basic
    @javax.persistence.Column(name = "fund_type_name")
    private String fundTypeName;


    public String getFundTypeName() {
        return fundTypeName;
    }

    public void setFundTypeName(String fundTypeName) {
        this.fundTypeName = fundTypeName;
    }

    @Basic
    @javax.persistence.Column(name = "fund_state")
    private Integer fundState;


    public Integer getFundState() {
        return fundState;
    }

    public void setFundState(Integer fundState) {
        this.fundState = fundState;
    }

    @Basic
    @javax.persistence.Column(name = "investment_type")
    private Integer investmentType;


    public Integer getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(Integer investmentType) {
        this.investmentType = investmentType;
    }

    @Basic
    @javax.persistence.Column(name = "investment_type_name")
    private String investmentTypeName;


    public String getInvestmentTypeName() {
        return investmentTypeName;
    }

    public void setInvestmentTypeName(String investmentTypeName) {
        this.investmentTypeName = investmentTypeName;
    }

    @Basic
    @javax.persistence.Column(name = "investment_style")
    private Integer investmentStyle;


    public Integer getInvestmentStyle() {
        return investmentStyle;
    }

    public void setInvestmentStyle(Integer investmentStyle) {
        this.investmentStyle = investmentStyle;
    }

    @Basic
    @javax.persistence.Column(name = "investment_style_name")
    private String investmentStyleName;

    public String getInvestmentStyleName() {
        return investmentStyleName;
    }

    public void setInvestmentStyleName(String investmentStyleName) {
        this.investmentStyleName = investmentStyleName;
    }

    @Basic
    @javax.persistence.Column(name = "investment_orientation")
    private String investmentOrientation;


    public String getInvestmentOrientation() {
        return investmentOrientation;
    }

    public void setInvestmentOrientation(String investmentOrientation) {
        this.investmentOrientation = investmentOrientation;
    }

    @Basic
    @javax.persistence.Column(name = "investment_target")
    private String investmentTarget;


    public String getInvestmentTarget() {
        return investmentTarget;
    }

    public void setInvestmentTarget(String investmentTarget) {
        this.investmentTarget = investmentTarget;
    }

    @Basic
    @javax.persistence.Column(name = "investment_field")
    private String investmentField;


    public String getInvestmentField() {
        return investmentField;
    }

    public void setInvestmentField(String investmentField) {
        this.investmentField = investmentField;
    }

    @Basic
    @javax.persistence.Column(name = "performance_bench_mark")
    private String performanceBenchMark;


    public String getPerformanceBenchMark() {
        return performanceBenchMark;
    }

    public void setPerformanceBenchMark(String performanceBenchMark) {
        this.performanceBenchMark = performanceBenchMark;
    }

    @Basic
    @javax.persistence.Column(name = "profit_distribution_rule")
    private String profitDistributionRule;


    public String getProfitDistributionRule() {
        return profitDistributionRule;
    }

    public void setProfitDistributionRule(String profitDistributionRule) {
        this.profitDistributionRule = profitDistributionRule;
    }

    @Basic
    @javax.persistence.Column(name = "asset_allocation")
    private String assetAllocation;


    public String getAssetAllocation() {
        return assetAllocation;
    }

    public void setAssetAllocation(String assetAllocation) {
        this.assetAllocation = assetAllocation;
    }

    @Basic
    @javax.persistence.Column(name = "brief_intro")
    private String briefIntro;


    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    @Basic
    @javax.persistence.Column(name = "risk_return_character")
    private String riskReturnCharacter;


    public String getRiskReturnCharacter() {
        return riskReturnCharacter;
    }

    public void setRiskReturnCharacter(String riskReturnCharacter) {
        this.riskReturnCharacter = riskReturnCharacter;
    }

    @Basic
    @javax.persistence.Column(name = "fund_management_fees")
    private String fundManagementFees;


    public String getFundManagementFees() {
        return fundManagementFees;
    }

    public void setFundManagementFees(String fundManagementFees) {
        this.fundManagementFees = fundManagementFees;
    }

    @Basic
    @javax.persistence.Column(name = "fund_trustee_fees")
    private String fundTrusteeFees;

    public String getFundTrusteeFees() {
        return fundTrusteeFees;
    }

    public void setFundTrusteeFees(String fundTrusteeFees) {
        this.fundTrusteeFees = fundTrusteeFees;
    }

    @Basic
    @javax.persistence.Column(name = "founded_size")
    private BigDecimal foundedSize;

    public BigDecimal getFoundedSize() {
        return foundedSize;
    }

    public void setFoundedSize(BigDecimal foundedSize) {
        this.foundedSize = foundedSize;
    }

    @Basic
    @javax.persistence.Column(name = "founded_hold_shares")
    private BigDecimal foundedHoldShares;


    public BigDecimal getFoundedHoldShares() {
        return foundedHoldShares;
    }

    public void setFoundedHoldShares(BigDecimal foundedHoldShares) {
        this.foundedHoldShares = foundedHoldShares;
    }

    @Basic
    @javax.persistence.Column(name = "founded_total_asset")
    private BigDecimal foundedTotalAsset;

    public BigDecimal getFoundedTotalAsset() {
        return foundedTotalAsset;
    }

    public void setFoundedTotalAsset(BigDecimal foundedTotalAsset) {
        this.foundedTotalAsset = foundedTotalAsset;
    }

    @Basic
    @javax.persistence.Column(name = "latest_size")
    private BigDecimal latestSize;

    public BigDecimal getLatestSize() {
        return latestSize;
    }

    public void setLatestSize(BigDecimal latestSize) {
        this.latestSize = latestSize;
    }

    @Basic
    @javax.persistence.Column(name = "latest_hold_shares")
    private BigDecimal latestHoldShares;


    public BigDecimal getLatestHoldShares() {
        return latestHoldShares;
    }

    public void setLatestHoldShares(BigDecimal latestHoldShares) {
        this.latestHoldShares = latestHoldShares;
    }

    @Basic
    @javax.persistence.Column(name = "latest_total_asset")
    private BigDecimal latestTotalAsset;

    public BigDecimal getLatestTotalAsset() {
        return latestTotalAsset;
    }

    public void setLatestTotalAsset(BigDecimal latestTotalAsset) {
        this.latestTotalAsset = latestTotalAsset;
    }

    @Basic
    @javax.persistence.Column(name = "isdeleted")
    private Integer isdeleted;

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Basic
    @javax.persistence.Column(name = "updatetime")
    private Timestamp updatetime;

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updateTime1) {
        Timestamp smUpdateTime = null;
        try {
            smUpdateTime = new Timestamp(CommonUtil.stringToDate(updateTime1, CommonUtil.DATE_FORMAT_ICU).getTime());
            if (StringUtils.isEmpty(updateTime1)) {
                smUpdateTime = null;
            }
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        this.updatetime = smUpdateTime;
    }


}
