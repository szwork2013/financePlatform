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
	private String bankName;
	private String bankCardNo;

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public String getBankName () {
		return bankName;
	}

	public void setBankName (String bankName) {
		this.bankName = bankName;
	}

	public String getBankCardNo () {
		return bankCardNo;
	}

	public void setBankCardNo (String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
}
