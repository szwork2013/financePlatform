package com.sunlights.core.vo;


import com.sunlights.common.FundCategory;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.utils.ArithUtil;
import models.FundNav;
import models.ProductManage;

import java.math.BigDecimal;

/**
 * Created by Yuan on 2014/9/1.
 */
public class FundVo extends ProductVo {
    private Integer peopleOfPurchased;//已申购人数
    private BigDecimal sevenDaysIncome;//七日年化收益率
    private BigDecimal millionIncome;//万分收益
    private String purchasedMethod;//买卖方式，比如:随买随卖
    private BigDecimal purchasedAmount;//起购金额
    private String discount;

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
        super.setName(fundNav.getFundname());
        super.setCategory(fundNav.getFundType() + "");
        super.setCode(fundNav.getFundcode());
        this.sevenDaysIncome = ArithUtil.bigUpScale4(fundNav.getPercentSevenDays());
        this.millionIncome = ArithUtil.bigUpScale4(fundNav.getIncomePerTenThousand());
        this.purchasedAmount = ArithUtil.bigUpScale4(fundNav.getPurchaseLimitMin());
        this.purchasedMethod = FundCategory.MONETARY.getFundType() == fundNav.getFundType() ? "随买随卖" : "";
        this.discount = getDiscountValueByfund(fundNav);
    }

    private String getDiscountValueByfund(FundNav fundNav) {
        String value = "";
        BigDecimal chargeRateValue = fundNav.getChargeRateValue();
        BigDecimal fundNavDiscount = fundNav.getDiscount();
        if (chargeRateValue == null || BigDecimal.ZERO.compareTo(chargeRateValue) == 0) {
            value = "免手续费";
        } else {
            value = fundNavDiscount == null ? "免手续费" : fundNavDiscount.multiply(new BigDecimal("100")) + "折";
        }
        return value;
    }

    public Integer getPeopleOfPurchased() {
        return peopleOfPurchased;
    }

    public void setPeopleOfPurchased(Integer peopleOfPurchased) {
        this.peopleOfPurchased = peopleOfPurchased;
    }

    public BigDecimal getSevenDaysIncome() {
        return sevenDaysIncome;
    }

    public void setSevenDaysIncome(BigDecimal sevenDaysIncome) {
        this.sevenDaysIncome = sevenDaysIncome;
    }


    public BigDecimal getMillionIncome() {
        return millionIncome;
    }

    public void setMillionIncome(BigDecimal millionIncome) {
        this.millionIncome = millionIncome;
    }

    public String getPurchasedMethod() {
        return purchasedMethod;
    }

    public void setPurchasedMethod(String purchasedMethod) {
        this.purchasedMethod = purchasedMethod;
    }

    public BigDecimal getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(BigDecimal purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
