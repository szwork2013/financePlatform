package com.sunlights.core.vo;


import models.Fund;
import com.sunlights.core.CodeConst;

import java.math.BigDecimal;

/**
 * Created by Yuan on 2014/9/1.
 */
public class FundVo extends ProductVo {
    private Long peopleOfPurchased;//已申购人数
    private BigDecimal sevenDaysIncome;//七日年化收益率
    private BigDecimal millionIncome;//万分收益
    private String purchasedMethod;//买卖方式，比如:随买随卖
    private BigDecimal purchasedAmount;//起购金额

    public FundVo() {

    }

    public FundVo(Fund fund) {
        inFund(fund);
    }

    public FundVo(Object[] columns) {
        this.setType(columns[0] == null ? "" : columns[0].toString());
        this.setId(columns[1] == null ? null : Long.valueOf(columns[1].toString()));
        this.setName(columns[2] == null ? null : columns[2].toString());
        this.setCode(columns[3] == null ? null : columns[3].toString());
        this.sevenDaysIncome = columns[4] == null ? BigDecimal.ZERO : (BigDecimal) columns[4];//七日年化
        this.millionIncome = columns[5] == null ? BigDecimal.ZERO : (BigDecimal) columns[5];
        this.purchasedAmount = columns[6] == null ? BigDecimal.ZERO : (BigDecimal) columns[6];//起购金额
        this.peopleOfPurchased = columns[7] == null ? 0L : Long.valueOf(columns[7].toString());
        this.purchasedMethod = columns[8] == null ? null : (String) columns[8];
    }

    public void inFund(Fund fund) {
        super.setId(fund.getId());
        super.setName(fund.getChiName());
        super.setType(CodeConst.PRODUCT_FUND);
        super.setCode(fund.getFundCode());
        this.sevenDaysIncome = fund.getOneWeekProfit();
        this.millionIncome = fund.getMillionOfProfit();
        this.purchasedAmount = fund.getMinApplyAmount();
        this.peopleOfPurchased = fund.getInitBuyedCount();
        this.purchasedMethod = fund.getInvestPeriod();
    }

    public Long getPeopleOfPurchased() {
        return peopleOfPurchased;
    }

    public void setPeopleOfPurchased(Long peopleOfPurchased) {
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
}
