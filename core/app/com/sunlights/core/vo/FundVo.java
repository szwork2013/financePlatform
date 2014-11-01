package com.sunlights.core.vo;


import com.sunlights.common.utils.ArithUtil;
import com.sunlights.core.models.Fund;
import com.sunlights.core.models.Product;

/**
 * Created by Yuan on 2014/9/1.
 */
public class FundVo extends ProductVo {
    public String fundCode;//代码
    public String peopleOfPurchased;//已申购人数
    public String sevenDaysIncome;//七日年化收益率
    public String sevenDaysIncomeNum;//七日年化
    public String millionIncome;//万分收益
    public String purchasedMethod;//买卖方式，比如:随买随卖
    public String purchasedAmmount;//起购金额

    public FundVo() {

    }

    public FundVo(Fund fund) {
        inFund(fund);
    }

    public void inFund(Fund fund) {
        Product product = fund.getProduct();
        this.id = fund.getId();
        this.name = product.getChiName();
        this.type = "0";
        this.fundCode = fund.getFundCode();
        this.sevenDaysIncome = ArithUtil.mul(fund.getOneWeekProfit().doubleValue(), 100) + "%";//七日年化
        this.sevenDaysIncomeNum = fund.getOneWeekProfit().toString();
        this.millionIncome = fund.getMillionOfProfit().doubleValue() + "元";
        //万分收益
        this.purchasedAmmount = ArithUtil.convertsToLong(fund.getMinApplyAmount().doubleValue()) + "元起购";//起购金额
        this.peopleOfPurchased = "3529000人";
    }
}
