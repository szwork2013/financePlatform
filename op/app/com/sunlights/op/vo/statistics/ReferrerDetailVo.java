package com.sunlights.op.vo.statistics;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yuan on 2015/3/25.
 */
public class ReferrerDetailVo {
	private String mobile;
	private String realName;
	private Date createTime;
	private BigDecimal purchaseAmount;

	public String getMobile () {
		return mobile;
	}

	public void setMobile (String mobile) {
		this.mobile = mobile;
	}

	public String getRealName () {
		return realName;
	}

	public void setRealName (String realName) {
		this.realName = realName;
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPurchaseAmount () {
		return purchaseAmount;
	}

	public void setPurchaseAmount (BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
}
