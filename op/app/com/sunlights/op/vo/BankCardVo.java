package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunlights.common.AppConst;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.ConverterUtil;
import models.BankCard;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yuan on 2015/3/30.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BankCardVo {
	private Long id;
	private String customerId;
	private String bankCardNo;// 银行卡号
	private String TradeAccount;// 基金交易账号
	private String SubTradeAccount;
	private boolean IsVaild;// 卡是否已经通过验证
	private String Balance;// 每日额度限制，如果为0则说明没有限制
	private String Status;// 卡的状态
	private String StatusToCN;
	private String IsFreeze;// 是否被冻结
	private String BankSerial;
	private String BankName;
	private String CapitalMode;// 绑卡渠道
	private String BindWay;// 绑卡方式
	private boolean SupportAutoPay;
	private BigDecimal DiscountRate;// 购买时折扣
	private String LimitDescribe;// 单笔50万元，日累计50万元
	private String ContentDescribe;// 必须开通网上银行
	private String Priority;
	private boolean IsP2p;
	private boolean deleted;
	private String loadAllInd;// 导入标志 Y全部加载 N单个绑卡
	private Date createTime;
	private Date updateTime;

	public BankCardVo() {
		super();
	}

	public BankCardVo(BankCard card) {
		inBankCard(card);
	}

	public void inBankCard(BankCard card) {
		try {
			ConverterUtil.fromEntity(this, card);
		} catch (ConverterException e) {
			e.printStackTrace();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getTradeAccount() {
		return TradeAccount;
	}

	public void setTradeAccount(String tradeAccount) {
		TradeAccount = tradeAccount;
	}

	public String getSubTradeAccount() {
		return SubTradeAccount;
	}

	public void setSubTradeAccount(String subTradeAccount) {
		SubTradeAccount = subTradeAccount;
	}

	public boolean isVaild() {
		return IsVaild;
	}

	public void setVaild(boolean isVaild) {
		IsVaild = isVaild;
	}

	public String getBalance() {
		return Balance;
	}

	public void setBalance(String balance) {
		Balance = balance;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getStatusToCN() {
		return StatusToCN;
	}

	public void setStatusToCN(String statusToCN) {
		StatusToCN = statusToCN;
	}

	public String getIsFreeze() {
		return IsFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		IsFreeze = isFreeze;
	}

	public String getBankSerial() {
		return BankSerial;
	}

	public void setBankSerial(String bankSerial) {
		BankSerial = bankSerial;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public String getCapitalMode() {
		return CapitalMode;
	}

	public void setCapitalMode(String capitalMode) {
		CapitalMode = capitalMode;
	}

	public String getBindWay() {
		return BindWay;
	}

	public void setBindWay(String bindWay) {
		BindWay = bindWay;
	}

	public boolean isSupportAutoPay() {
		return SupportAutoPay;
	}

	public void setSupportAutoPay(boolean supportAutoPay) {
		SupportAutoPay = supportAutoPay;
	}

	public BigDecimal getDiscountRate() {
		return DiscountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		DiscountRate = discountRate;
	}

	public String getLimitDescribe() {
		return LimitDescribe;
	}

	public void setLimitDescribe(String limitDescribe) {
		LimitDescribe = limitDescribe;
	}

	public String getContentDescribe() {
		return ContentDescribe;
	}

	public void setContentDescribe(String contentDescribe) {
		ContentDescribe = contentDescribe;
	}

	public String getPriority() {
		return Priority;
	}

	public void setPriority(String priority) {
		Priority = priority;
	}

	public boolean isP2p() {
		return IsP2p;
	}

	public void setP2p(boolean isP2p) {
		IsP2p = isP2p;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getLoadAllInd() {
		return loadAllInd;
	}

	public void setLoadAllInd(String loadAllInd) {
		this.loadAllInd = loadAllInd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
