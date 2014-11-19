package com.sunlights.core.vo;

import com.sunlights.common.service.CommonService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import models.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Project: fsp</p>
 * <p>Title: FundDetailVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class FundDetailVo extends FundVo {
    //取现到帐
    private String toAccountType;
    //风险
    private String riskLevel;
    //基金公司
    private String companyName;
    // 基金规模
    private String fundScale;
    // 30天购买人数
    private Integer buiersOf30Days;
    //最新：2014-10-26
    private String currentDate;


    public FundDetailVo() {
        super();
    }

    public FundDetailVo(FundNav fundNav, ProductManage pm, FundCompany fundCompany) {
        super(fundNav, pm);
        inFundDetail(fundNav, pm, fundCompany);
    }

    public void inFundDetail(FundNav fundNav, ProductManage pm, FundCompany fundCompany) {
        //取现到帐
        this.toAccountType = fundNav.getRapidRedeem() + "";
        //风险
        this.riskLevel = fundNav.getRiskLevel() + "";
        //基金公司
        this.companyName = fundCompany.getCompanyName();
        // 基金规模
        BigDecimal scale = fundNav.getLastestTotalAsset();

        this.fundScale = scale == null ? null : ArithUtil.bigToScale2(scale.divide(new BigDecimal("100000000"))) + "亿";
        //最新：2014-10-26
        this.currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_SHORT);
        this.buiersOf30Days = pm.getOneMonthBuyedCount();
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFundScale() {
        return fundScale;
    }

    public void setFundScale(String fundScale) {
        this.fundScale = fundScale;
    }

    public Integer getBuiersOf30Days() {
        return buiersOf30Days;
    }

    public void setBuiersOf30Days(Integer buiersOf30Days) {
        this.buiersOf30Days = buiersOf30Days;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}
