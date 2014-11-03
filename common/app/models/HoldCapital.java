package models;

import models.IdEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * 持有资产model
 * @author tangweiqun 2014/10/22
 *
 */
@Entity
@Table(name = "F_HOLDCAPITAL")
public class HoldCapital extends IdEntity {
	private static final long serialVersionUID = 3408103055932941065L;
	
	@Column(name="CUST_ID")
	private String custId;//客户号
    @Column(name="PRODUCT_CODE",length = 8)
    private String productCode;
    @Column(name="PRODUCT_NAME", length = 100)
    private String productName;
    @Column(name="PRODUCT_TYPE", length = 50)
    private String productType;
    @Column(name="TRADE_AMOUNT", length = 100)
    private BigDecimal tradeAmount;//申赎资产
	@Column(name="HOLD_CAPITAL")
	private BigDecimal holdCapital;//持有资产
	@Column(name="YESTERDAY_PROFIT", precision = 18,scale = 4)
	private BigDecimal yesterdayProfit;//昨天收益
	@Column(name="TOTAL_PROFIT", precision = 18,scale = 4)
	private BigDecimal totalProfit;//累计收益
    @Column(name="STATUS", length = 1)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="SETTLE_DATE")
	private Date settleDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATE_TIME")
	private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DELETE_TIME")
	private Date deleteTime;


	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}


	public BigDecimal getHoldCapital() {
		return holdCapital;
	}

	public void setHoldCapital(BigDecimal holdCapital) {
		this.holdCapital = holdCapital;
	}

	public BigDecimal getYesterdayProfit() {
		return yesterdayProfit;
	}

	public void setYesterdayProfit(BigDecimal yesterdayProfit) {
		this.yesterdayProfit = yesterdayProfit;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Timestamp settleDate) {
        this.settleDate = settleDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
