package com.sunlights.core.vo;


import com.sunlights.common.AppConst;
import com.sunlights.common.FundCategory;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import models.FundNav;
import models.ProductManage;

import java.math.BigDecimal;
import java.util.List;

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
        return peopleOfPurchased;
    }

    public void setPeopleOfPurchased(Integer peopleOfPurchased) {
        this.peopleOfPurchased = peopleOfPurchased;
    }

    public String getSevenDaysIncome() {
        return sevenDaysIncome;
    }

    public void setSevenDaysIncome(String sevenDaysIncome) {
        this.sevenDaysIncome = sevenDaysIncome;
    }

    public String getMillionIncome() {
        return millionIncome;
    }

    public void setMillionIncome(String millionIncome) {
        this.millionIncome = millionIncome;
    }

    public String getPurchasedMethod() {
        return purchasedMethod;
    }

    public void setPurchasedMethod(String purchasedMethod) {
        this.purchasedMethod = purchasedMethod;
    }

    public String getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(String purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Integer getPurchaseState() {
        return purchaseState;
    }

    public void setPurchaseState(Integer purchaseState) {
        this.purchaseState = purchaseState;
    }

	public String getDiscountValue () {
		return discountValue;
	}

	public void setDiscountValue (String discountValue) {
		this.discountValue = discountValue;
	}
}
