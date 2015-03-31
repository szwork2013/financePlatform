package com.sunlights.op.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Yuan on 2015/3/30.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FundTradeVo {
	private Long id;
	private String fundType;
	private String fundCode;
	private String fundName;
	private BigDecimal holdQuantity;
	private BigDecimal availableQuantity;
	private BigDecimal netValue;
	private BigDecimal marketValue;
	private String tradeType;

	public String getTradeType () {
		return tradeType;
	}

	public void setTradeType (String tradeType) {
		this.tradeType = tradeType;
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public String getFundType () {
		return fundType;
	}

	public void setFundType (String fundType) {
		this.fundType = fundType;
	}

	public String getFundCode () {
		return fundCode;
	}

	public void setFundCode (String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundName () {
		return fundName;
	}

	public void setFundName (String fundName) {
		this.fundName = fundName;
	}

	public BigDecimal getHoldQuantity () {
		return holdQuantity;
	}

	public void setHoldQuantity (BigDecimal holdQuantity) {
		this.holdQuantity = holdQuantity;
	}

	public BigDecimal getAvailableQuantity () {
		return availableQuantity;
	}

	public void setAvailableQuantity (BigDecimal availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public BigDecimal getNetValue () {
		return netValue;
	}

	public void setNetValue (BigDecimal netValue) {
		this.netValue = netValue;
	}

	public BigDecimal getMarketValue () {
		return marketValue;
	}

	public void setMarketValue (BigDecimal marketValue) {
		this.marketValue = marketValue;
	}
}
