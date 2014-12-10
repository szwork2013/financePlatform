package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by guxuelong on 2014/11/27.
 */
@Entity
@Table(name = "fund_profit_history")
public class FundProfitHistory extends IdEntity{
    @Column(name = "fund_code")
    private String fundCode;
    @Column(name = "date_time")
    private Timestamp dateTime;
    @Column(name = "percent_seven_days")
    private BigDecimal percentSevenDays;
    @Column(name = "income_per_ten_thousand")
    private BigDecimal incomePerTenThousand;
    @Column(name = "create_time")
    private Timestamp createTime;
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Transient
    private String fundname;
    @Transient
    private String delFlag;
    @Transient
    private String smUpdateTime;

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSmUpdateTime() {
        return smUpdateTime;
    }

    public void setSmUpdateTime(String smUpdateTime) {
        this.smUpdateTime = smUpdateTime;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundcode) {
        this.fundCode = fundcode;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPercentSevenDays() {
        return percentSevenDays;
    }

    public void setPercentSevenDays(BigDecimal percentSevenDays) {
        this.percentSevenDays = percentSevenDays;
    }

    public BigDecimal getIncomePerTenThousand() {
        return incomePerTenThousand;
    }

    public void setIncomePerTenThousand(BigDecimal incomePerTenThousand) {
        this.incomePerTenThousand = incomePerTenThousand;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FundProfitHistory that = (FundProfitHistory) o;
        if (getId() != that.getId()) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null) return false;
        if (incomePerTenThousand != null ? !incomePerTenThousand.equals(that.incomePerTenThousand) : that.incomePerTenThousand != null)
            return false;
        if (percentSevenDays != null ? !percentSevenDays.equals(that.percentSevenDays) : that.percentSevenDays != null)
            return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() != null ? getId() ^ (getId() >>> 32) : 0);
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (percentSevenDays != null ? percentSevenDays.hashCode() : 0);
        result = 31 * result + (incomePerTenThousand != null ? incomePerTenThousand.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
