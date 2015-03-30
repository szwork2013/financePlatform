package com.sunlights.op.dto;

import com.sunlights.op.common.constants.ActivityConstant;
import com.sunlights.op.vo.activity.ExchangeResultVo;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;

/**
 * Created by Administrator on 2015/2/9.
 */
public class ExchangeResultExportXlsDto extends ExportXlsDto<ExchangeResultVo> {
	// String sheetNames =
	// "ID,兑换日期,客户姓名,注册手机号,兑换场景,状态,银行名称,银行卡号,待发金额（元）,已发金额（元）,差额（元）,支付回单号";
	// String filedNames =
	// "id,createTimeStr,realName,registerMobile,exchangeSceneStr,statusStr,bankName,bankCardNo,amount,exchangedAmount,balance,paymentReceiptNo";

	public ExchangeResultExportXlsDto () {
		setSheetName("清算统计");
	}

	@Override
	public void addTitles () {
		addTitle("编号");
		addTitle("兑换日期");
		addTitle("客户姓名");
		addTitle("注册手机号");
		addTitle("兑换场景");
		addTitle("状态");
		addTitle("银行名称");
		addTitle("银行卡号");
		addTitle("待发金额（元）");
		addTitle("已发金额（元）");
		addTitle("差额（元）");
		addTitle("支付回单号");
	}

	@Override
	public void addFieldNames () {
		addFieldName("id");
		addFieldName("createTimeStr");
		addFieldName("realName");
		addFieldName("registerMobile");
		addFieldName("exchangeSceneStr");
		addFieldName("statusStr");
		addFieldName("bankName");
		addFieldName("bankCardNo");
		addFieldName("amount");
		addFieldName("exchangedAmount");
		addFieldName("balance");
		addFieldName("paymentReceiptNo");
	}

	@Override
	public void setCellStyle (Label label) {
		int column = label.getColumn();
		String value = label.getString();
		WritableCellFormat colourFormat = new WritableCellFormat(NumberFormats.TEXT);
		if (column == 5) {
			try {
				Colour colour = getBackColorByValue(value);
				colourFormat.setBackground(colour);
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		label.setCellFormat(colourFormat);
	}

	private Colour getBackColorByValue (String value) {
		Colour colour = null;
		switch (value) {
		case ActivityConstant.AUDIT_PASS_DESC:
			colour = Colour.YELLOW;
			break;
		case ActivityConstant.EXCHANGEING_DESC:
			colour = Colour.BLUE;
			break;
		case ActivityConstant.EXCHANGE_SUCC_DESC:
			colour = Colour.GREEN;
			break;
		case ActivityConstant.EXCHANGE_FAIL_DESC:
			colour = Colour.RED;
			break;
		}
		return colour;
	}

}
