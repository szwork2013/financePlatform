package com.sunlights.core.vo;

import java.math.BigDecimal;
import java.util.List;

import models.FundNav;
import models.ProductManage;

import com.sunlights.common.AppConst;
import com.sunlights.common.FundCategory;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;

/**
 * Created by Yuan on 2014/9/1.
 */
public class FundVo extends ProductVo {
	private Integer peopleOfPurchased;//已申购人数
	private String sevenDaysIncome;//七日年化收益率
	private String millionIncome;//万分收益
	private String purchasedMethod;//买卖方式，比如:随买随卖
	private String purchasedAmount;//起购金额
	private String discount;//手续费描述
	private String discountValue;//手续费

	@Override
	public boolean equals(Object o) {
		if(!super.equals(o)){
			return false;
		};
		if (this == o) return true;
		if (!(o instanceof FundVo)) return false;

		FundVo fundVo = (FundVo) o;

		if (activity != null ? !activity.equals(fundVo.activity) : fundVo.activity != null) return false;
		if (discount != null ? !discount.equals(fundVo.discount) : fundVo.discount != null) return false;
		if (discountValue != null ? !discountValue.equals(fundVo.discountValue) : fundVo.discountValue != null)
			return false;
		if (millionIncome != null ? !millionIncome.equals(fundVo.millionIncome) : fundVo.millionIncome != null)
			return false;
		if (peopleOfPurchased != null ? !peopleOfPurchased.equals(fundVo.peopleOfPurchased) : fundVo.peopleOfPurchased != null)
			return false;
		if (purchaseState != null ? !purchaseState.equals(fundVo.purchaseState) : fundVo.purchaseState != null)
			return false;
		if (purchasedAmount != null ? !purchasedAmount.equals(fundVo.purchasedAmount) : fundVo.purchasedAmount != null)
			return false;
		if (purchasedMethod != null ? !purchasedMethod.equals(fundVo.purchasedMethod) : fundVo.purchasedMethod != null)
			return false;
		if (sevenDaysIncome != null ? !sevenDaysIncome.equals(fundVo.sevenDaysIncome) : fundVo.sevenDaysIncome != null)
			return false;

		return true;


	}

	@Override
	public int hashCode() {
		int result = peopleOfPurchased != null ? peopleOfPurchased.hashCode() : 0;
		result = 31 * result + (sevenDaysIncome != null ? sevenDaysIncome.hashCode() : 0);
		result = 31 * result + (millionIncome != null ? millionIncome.hashCode() : 0);
		result = 31 * result + (purchasedMethod != null ? purchasedMethod.hashCode() : 0);
		result = 31 * result + (purchasedAmount != null ? purchasedAmount.hashCode() : 0);
		result = 31 * result + (discount != null ? discount.hashCode() : 0);
		result = 31 * result + (discountValue != null ? discountValue.hashCode() : 0);
		result = 31 * result + (activity != null ? activity.hashCode() : 0);
		result = 31 * result + (purchaseState != null ? purchaseState.hashCode() : 0);
		return result;
	}

	private String activity;//活动
	private Integer purchaseState;//是否可申购

	public FundVo() {

	}

	public FundVo(FundNav fundNav, ProductManage pm) {
		inFundNav(fundNav);
		inProductManage(pm);
	}


	public void inProductManage(ProductManage pm) {
		super.setType(pm.getProductType());
		super.setTypeDesc(new CommonService().findValueByCatPointKey(pm.getProductType()));
		super.setGroup(pm.getRecommendType());
		super.setGroupDesc(new CommonService().findValueByCatPointKey(pm.getRecommendType()));
		super.setTag(pm.getRecommendFlag());
		super.setTagDesc(new CommonService().findValueByCatPointKey(pm.getRecommendFlag()));
		this.setPeopleOfPurchased(pm.getInitBuyedCount());
	}


	public void inFundNav(FundNav fundNav) {
		Integer isMonetary = fundNav.getIsMonetary();
		Integer isStf = fundNav.getIsStf();

		super.setName(fundNav.getFundname());
		super.setCategory(isMonetary == 1 ? AppConst.FUND_CATEGORY_MONETARY : (isStf == 1 ? AppConst.FUND_CATEGORY_STF : ""));
		super.setCategoryDesc(FundCategory.findFundCategoryBy(getCategory()).getDescription());
		super.setCode(fundNav.getFundcode());
		this.sevenDaysIncome = ArithUtil.bigUpScale4(fundNav.getPercentSevenDays());
		this.millionIncome = ArithUtil.bigUpScale4(fundNav.getIncomePerTenThousand());
		BigDecimal purchaseLimitMin = ArithUtil.bigUpScale0(fundNav.getPurchaseLimitMin());
		this.purchasedAmount = purchaseLimitMin == null ? "" : purchaseLimitMin.toString();
		this.purchasedMethod = isMonetary == 1 ? "随买随卖" : (isStf == 1 ? "7天" : "");
		ActivityService activityService = new ActivityServiceImpl();
		List<String> activities = activityService.getActivityTitles(fundNav.getFundcode());
		this.activity = activities.isEmpty() ? "" : activities.get(0);
		this.purchaseState = fundNav.getPurchaseState();
		convertDiscountValue(fundNav);
	}

	private void convertDiscountValue(FundNav fundNav) {
		BigDecimal chargeRateValue = fundNav.getChargeRateValue();
		BigDecimal fundNavDiscount = fundNav.getDiscount();
		if (chargeRateValue == null || BigDecimal.ZERO.compareTo(chargeRateValue) == 0) {
			this.discount = "免手续费";
			this.discountValue = null;
		} else {
			this.discount = fundNavDiscount == null ? "免手续费" : fundNavDiscount.multiply(new BigDecimal("100")) + "折";
			this.discountValue = fundNavDiscount == null ? null : fundNavDiscount.toString();
		}
	}

	public Integer getPeopleOfPurchased() {
		return CommonUtil.format(peopleOfPurchased);
	}

	public void setPeopleOfPurchased(Integer peopleOfPurchased) {
		this.peopleOfPurchased = peopleOfPurchased;
	}

	public String getSevenDaysIncome() {
		return CommonUtil.format(sevenDaysIncome);
	}

	public void setSevenDaysIncome(String sevenDaysIncome) {
		this.sevenDaysIncome = sevenDaysIncome;
	}

	public String getMillionIncome() {
		return CommonUtil.format(millionIncome);
	}

	public void setMillionIncome(String millionIncome) {
		this.millionIncome = millionIncome;
	}

	public String getPurchasedMethod() {
		return CommonUtil.format(purchasedMethod);
	}

	public void setPurchasedMethod(String purchasedMethod) {
		this.purchasedMethod = purchasedMethod;
	}

	public String getPurchasedAmount() {
		return CommonUtil.format(purchasedAmount);
	}

	public void setPurchasedAmount(String purchasedAmount) {
		this.purchasedAmount = purchasedAmount;
	}

	public String getDiscount() {
		return CommonUtil.format(discount);
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getActivity() {
		return CommonUtil.format(activity);
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Integer getPurchaseState() {
		return CommonUtil.format(purchaseState);
	}

	public void setPurchaseState(Integer purchaseState) {
		this.purchaseState = purchaseState;
	}

	public String getDiscountValue () {
		return CommonUtil.format(discountValue);
	}

	public void setDiscountValue (String discountValue) {
		this.discountValue = discountValue;
	}
}
