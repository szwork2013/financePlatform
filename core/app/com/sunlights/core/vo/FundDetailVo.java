package com.sunlights.core.vo;

import com.sunlights.common.AppConst;
import com.sunlights.common.FundDict;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import models.FundCompany;
import models.FundNav;
import models.ProductManage;

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

	public FundDetailVo () {
		super();
	}

	public FundDetailVo (Object[] row) {
		CommonService commonService = new CommonService();
		setPeopleOfPurchased(row[0] == null ? null : Integer.valueOf(row[0].toString()));
		setBuiersOf30Days(row[1] == null ? null : Integer.valueOf(row[1].toString()));
		String productType = row[2] == null ? null : row[2].toString();
		setType(productType);
		setTypeDesc(commonService.findValueByCatPointKey(productType));
		String recommendType = row[3] == null ? null : row[3].toString();
		setGroup(recommendType);
		setGroupDesc(commonService.findValueByCatPointKey(recommendType));
		String recommendFlag = row[4] == null ? null : row[4].toString();
		setTag(recommendFlag);
		setTagDesc(commonService.findValueByCatPointKey(recommendFlag));
		setMillionIncome(row[5] == null ? null : row[5].toString());
		setPurchasedAmount(row[6] == null ? null : row[6].toString());
		BigDecimal discount = row[8] == null ? null : new BigDecimal(row[8].toString());
		setDiscount((row[7] == null || discount == null) ? "免手续费" : discount.multiply(new BigDecimal("100")) + "折");
		setDiscountValue(ArithUtil.bigUpScale4(discount));
		setPurchaseState(row[9] == null ? null : Integer.valueOf(row[9].toString()));

		Integer isMonetary = row[10] == null ? null : Integer.valueOf(row[10].toString());
		Integer isStf = row[11] == null ? null : Integer.valueOf(row[11].toString());
		setCategory(isMonetary == 1 ? AppConst.FUND_CATEGORY_MONETARY : (isStf == 1 ? AppConst.FUND_CATEGORY_STF : ""));
		setPurchasedMethod(isMonetary == 1 ? "随买随卖" : (isStf == 1 ? "7天" : ""));


	}

	public FundDetailVo (FundNav fundNav, ProductManage pm, FundCompany fundCompany) {
		super(fundNav, pm);
		inFundDetail(fundNav, pm, fundCompany);
	}

	public void inFundDetail (FundNav fundNav, ProductManage pm, FundCompany fundCompany) {
		//取现到帐
		this.toAccountType = FundDict.getRapidRedeem(fundNav.getRapidRedeem());
		//风险
		this.riskLevel = FundDict.getRiskLevel(fundNav.getRiskLevel());
		//基金公司 简称
		this.companyName = fundCompany.getAbbrName();
		// 基金规模
		BigDecimal scale = fundNav.getLastestTotalAsset();

		this.fundScale = scale == null ? null : ArithUtil.bigToScale2(scale.divide(new BigDecimal("100000000"))) + "亿";
		//最新：2014-10-26
		this.currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_SHORT);
		this.buiersOf30Days = pm.getOneMonthBuyedCount();
	}

	public String getToAccountType () {
		return toAccountType;
	}

	public void setToAccountType (String toAccountType) {
		this.toAccountType = toAccountType;
	}

	public String getRiskLevel () {
		return riskLevel;
	}

	public void setRiskLevel (String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getCompanyName () {
		return companyName;
	}

	public void setCompanyName (String companyName) {
		this.companyName = companyName;
	}

	public String getFundScale () {
		return fundScale;
	}

	public void setFundScale (String fundScale) {
		this.fundScale = fundScale;
	}

	public Integer getBuiersOf30Days () {
		return buiersOf30Days;
	}

	public void setBuiersOf30Days (Integer buiersOf30Days) {
		this.buiersOf30Days = buiersOf30Days;
	}

	public String getCurrentDate () {
		return currentDate;
	}

	public void setCurrentDate (String currentDate) {
		this.currentDate = currentDate;
	}
}
