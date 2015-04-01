package com.sunlights.op.vo.statistics;

import java.math.BigDecimal;

/**
 * Created by Yuan on 2015/1/13.
 */
public class ReferrerVo {

	private String name;

	private String mobile;

	private Integer referrerTotality;

	private Integer purchaserCount;

	private Integer unPurchaserCount;

	private BigDecimal recommendedPurchaseAmount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPurchaserCount() {
		return purchaserCount;
	}

	public void setPurchaserCount(Integer purchaserCount) {
		this.purchaserCount = purchaserCount;
	}

	public BigDecimal getRecommendedPurchaseAmount() {
		return recommendedPurchaseAmount;
	}

	public void setRecommendedPurchaseAmount(BigDecimal recommendedPurchaseAmount) {
		this.recommendedPurchaseAmount = recommendedPurchaseAmount;
	}

	public Integer getReferrerTotality() {
		return referrerTotality;
	}

	public void setReferrerTotality(Integer referrerTotality) {
		this.referrerTotality = referrerTotality;
	}

	public Integer getUnPurchaserCount() {
		if(referrerTotality == null || purchaserCount == null) {
			return 0;
		}
		return referrerTotality - purchaserCount;
	}

	public void setUnPurchaserCount(Integer unPurchaserCount) {
		this.unPurchaserCount = unPurchaserCount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
