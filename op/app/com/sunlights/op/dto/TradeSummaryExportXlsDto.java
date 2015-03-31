package com.sunlights.op.dto;

import jxl.write.Label;

import com.sunlights.op.vo.TradeSummaryVo;

/**
 * Created by Yuan on 2015/3/26.
 */
public class TradeSummaryExportXlsDto extends ExportXlsDto<TradeSummaryVo> {

	public TradeSummaryExportXlsDto() {
		setSheetName("汇总统计");
	}

	@Override
	public void addTitles() {
		addTitle("日期");
		addTitle("累计转入人数");
		addTitle("累计转入金额");
		addTitle("累计转出人数");
		addTitle("累计转出金额");
		addTitle("日新增人数");
		addTitle("日增转入金额");
		addTitle("日增转出金额");
		addTitle("注册人数");
	}

	@Override
	public void addFieldNames() {
		addFieldName("tradeDate");
		addFieldName("totalInCustomerCount");
		addFieldName("inAmountTotal");
		addFieldName("totalOutCustomerCount");
		addFieldName("outAmountTotal");
		addFieldName("inCustomerCount");
		addFieldName("dayInAmount");
		addFieldName("dayOutAmount");
		addFieldName("registrationTotal");
	}

	@Override
	public void setCellStyle(Label label) {

	}
}