package com.sunlights.core.vo;

import com.sunlights.common.AppConst;
import com.sunlights.common.FundCategory;
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
	// 取现到帐
	private String toAccountType;
	// 风险
	private String riskLevel;
	// 基金公司
	private String companyName;
	// 基金规模
	private String fundScale;
	// 30天购买人数
	private Integer buiersOf30Days;
	// 最新：2014-10-26
	private String currentDate;
	// 成立日期
	private String establishmentDate;
	// 份额规模
	private String latestHoldShares;
	// 基金经理
	private String manager;
	// 托管人
	private String trusteeName;

	public FundDetailVo() {
		super();
	}

	public FundDetailVo(Object[] row) {
		inRow(row);
	}

	public void inRow(Object[] row) {
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
		setCode(row[5] == null ? null : row[5].toString());
		setName(row[6] == null ? null : row[6].toString());
		setSevenDaysIncome(ArithUtil.bigUpScale4(row[7] == null ? null : new BigDecimal(row[7].toString())));
		setMillionIncome(ArithUtil.bigUpScale4(row[8] == null ? null : new BigDecimal(row[8].toString())));
		setPurchasedAmount(ArithUtil.bigUpScale0(row[9] == null ? null : new BigDecimal(row[9].toString())).toString());
		BigDecimal discount = row[11] == null ? null : new BigDecimal(row[11].toString());
		setDiscount((row[10] == null || BigDecimal.ZERO.compareTo(new BigDecimal(row[10].toString())) == 0 || discount == null) ? "免手续费" : discount
				.multiply(new BigDecimal("100")) + "折");
		setDiscountValue(ArithUtil.bigUpScale4(discount));
		setPurchaseState(row[12] == null ? null : Integer.valueOf(row[12].toString()));

		Integer isMonetary = row[13] == null ? null : Integer.valueOf(row[13].toString());
		Integer isStf = row[14] == null ? null : Integer.valueOf(row[14].toString());
		setCategory(isMonetary == 1 ? AppConst.FUND_CATEGORY_MONETARY : (isStf == 1 ? AppConst.FUND_CATEGORY_STF : ""));
		setCategoryDesc(FundCategory.findFundCategoryBy(getCategory()).getDescription());
		setPurchasedMethod(isMonetary == 1 ? "随买随卖" : (isStf == 1 ? "7天" : ""));

		Integer rapidRedeem = row[15] == null ? null : Integer.valueOf(row[15].toString());
		setToAccountType(FundDict.getRapidRedeem(rapidRedeem));
		String risk = row[16] == null ? null : row[16].toString();
		setRiskLevel(FundDict.getRiskLevel(risk));
		BigDecimal lastestTotalAsset = row[17] == null ? null : new BigDecimal(row[17].toString());
		setFundScale(lastestTotalAsset == null ? null : ArithUtil.bigToScale2(lastestTotalAsset.divide(new BigDecimal("100000000"))) + "亿");
		setCurrentDate(row[18] == null ? null : row[18].toString());
		setEstablishmentDate(row[20] == null ? null : row[20].toString());
		setLatestHoldShares(row[21] == null ? null : ArithUtil.bigUpScale0(new BigDecimal(row[21].toString())).toString());
		setManager(row[22] == null ? null : row[22].toString());
		setTrusteeName(row[23] == null ? null : row[23].toString());
	}

	public FundDetailVo(FundNav fundNav, ProductManage pm, FundCompany fundCompany) {
		super(fundNav, pm);
		inFundDetail(fundNav, pm, fundCompany);
	}

	public void inFundDetail(FundNav fundNav, ProductManage pm, FundCompany fundCompany) {
		// 取现到帐
		this.toAccountType = FundDict.getRapidRedeem(fundNav.getRapidRedeem());
		// 风险
		this.riskLevel = FundDict.getRiskLevel(fundNav.getRiskLevel());
		// 基金公司 简称
		this.companyName = fundCompany.getAbbrName();
		// 基金规模
		BigDecimal scale = fundNav.getLastestTotalAsset();

		this.fundScale = scale == null ? null : ArithUtil.bigToScale2(scale.divide(new BigDecimal("100000000"))) + "亿";
		// 最新：2014-10-26
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

	public String getEstablishmentDate () {
		return establishmentDate;
	}

	public void setEstablishmentDate (String establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public String getLatestHoldShares () {
		return latestHoldShares;
	}

	public void setLatestHoldShares (String latestHoldShares) {
		this.latestHoldShares = latestHoldShares;
	}

	public String getManager () {
		return manager;
	}

	public void setManager (String manager) {
		this.manager = manager;
	}

	public String getTrusteeName () {
		return trusteeName;
	}

	public void setTrusteeName (String trusteeName) {
		this.trusteeName = trusteeName;
	}
}
