package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Yuan on 2014/9/1.
 */
@Entity
@Table(name = "P_FUND")
public class Fund extends BaseEntity {
    @Column(name = "FUND_CODE", length = 8, nullable = false)
    private String fundCode;//基金代码
    @Column(name = "MIN_APPLY_AMOUNT", precision = 18, scale = 4, nullable = false)
    private BigDecimal minApplyAmount;//最低申购金额
    @Column(name = "LOWEST_REDEMPTION", nullable = false)
    private long lowestRedemption;//最低赎回份额
    @Column(name = "ONE_YEAR_PROFIT", precision = 18, scale = 4, nullable = false)
    private BigDecimal oneYearProfit;//银行一年期收益
    @Column(name = "MILLION_OF_PROFIT", precision = 18, scale = 4, nullable = false)
    private BigDecimal millionOfProfit;//万份收益
    @Column(name = "ONE_WEEK_PROFIT", precision = 18, scale = 4, nullable = false)
    private BigDecimal oneWeekProfit;//七日年化
    @Column(name = "NAV_DATE", nullable = false)
    private Timestamp navDate;//净值日期
    @Column(name = "IS_APPLY", nullable = false)
    private int isApply;//	0:可申购;1:不可以申购
    @Column(name = "IS_REDEMPTION", nullable = false)
    private int isRedemption;//	0:可赎回;1:不可以赎回
    @Column(name = "PRODUCT_STATUS", nullable = false)
    private int productStatus;//	1:最新，0：产品历史

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public BigDecimal getMinApplyAmount() {
        return minApplyAmount;
    }

    public void setMinApplyAmount(BigDecimal minApplyAmount) {
        this.minApplyAmount = minApplyAmount;
    }

    public long getLowestRedemption() {
        return lowestRedemption;
    }

    public void setLowestRedemption(long lowestRedemption) {
        this.lowestRedemption = lowestRedemption;
    }

    public BigDecimal getOneYearProfit() {
        return oneYearProfit;
    }

    public void setOneYearProfit(BigDecimal oneYearProfit) {
        this.oneYearProfit = oneYearProfit;
    }

    public BigDecimal getMillionOfProfit() {
        return millionOfProfit;
    }

    public void setMillionOfProfit(BigDecimal millionOfProfit) {
        this.millionOfProfit = millionOfProfit;
    }

    public BigDecimal getOneWeekProfit() {
        return oneWeekProfit;
    }

    public void setOneWeekProfit(BigDecimal oneWeekProfit) {
        this.oneWeekProfit = oneWeekProfit;
    }

    public Timestamp getNavDate() {
        return navDate;
    }

    public void setNavDate(Timestamp navDate) {
        this.navDate = navDate;
    }

    public int getIsApply() {
        return isApply;
    }

    public void setIsApply(int isApply) {
        this.isApply = isApply;
    }

    public int getIsRedemption() {
        return isRedemption;
    }

    public void setIsRedemption(int isRedemption) {
        this.isRedemption = isRedemption;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
