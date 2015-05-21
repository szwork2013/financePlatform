package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Edward.tian on 2015/5/6 0006.
 */
@Entity
@Table(name="t_sync_income_stat")
public class SyncIncomeStat extends IdEntity{

    @Column(name="customer_id")
    private String customerId;

    @Column(name="fund_code")
    private String fundCode;

    @Column(name="system_day")
    private Date systemDay;

    @Column(name="curr_day")
    private Date currDay;

    @Column(name="TotalHoldGatherNum")
    private String totalHoldGatherNum;

    @Column(name="HadNetValueHol")
    private String hadNetValueHol;

    @Column(name="City_Value")
    private double cityValue;

    @Column(name="Hold_Cost")
    private double holdCost;

    @Column(name="Today_Income")
    private double todayIncome;

    @Column(name="Hold_Income")
    private double holdIncome;

    @Column(name="Hold_Income_Rate")
    private double holdIncomeRate;

    @Column(name="create_time")
    private Date createTime;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public Date getSystemDay() {
        return systemDay;
    }

    public void setSystemDay(Date systemDay) {
        this.systemDay = systemDay;
    }

    public Date getCurrDay() {
        return currDay;
    }

    public void setCurrDay(Date currDay) {
        this.currDay = currDay;
    }

    public String getTotalHoldGatherNum() {
        return totalHoldGatherNum;
    }

    public void setTotalHoldGatherNum(String totalHoldGatherNum) {
        this.totalHoldGatherNum = totalHoldGatherNum;
    }

    public String getHadNetValueHol() {
        return hadNetValueHol;
    }

    public void setHadNetValueHol(String hadNetValueHol) {
        this.hadNetValueHol = hadNetValueHol;
    }

    public double getCityValue() {
        return cityValue;
    }

    public void setCityValue(double cityValue) {
        this.cityValue = cityValue;
    }

    public double getHoldCost() {
        return holdCost;
    }

    public void setHoldCost(double holdCost) {
        this.holdCost = holdCost;
    }

    public double getTodayIncome() {
        return todayIncome;
    }

    public void setTodayIncome(double todayIncome) {
        this.todayIncome = todayIncome;
    }

    public double getHoldIncome() {
        return holdIncome;
    }

    public void setHoldIncome(double holdIncome) {
        this.holdIncome = holdIncome;
    }

    public double getHoldIncomeRate() {
        return holdIncomeRate;
    }

    public void setHoldIncomeRate(double holdIncomeRate) {
        this.holdIncomeRate = holdIncomeRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
