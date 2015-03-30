package com.sunlights.op.dto;

import com.sunlights.op.vo.PurchaseStatisticsVo;
import jxl.write.Label;

/**
 * Created by Yuan on 2015/3/26.
 */
public class FirstPurchaseExportXlsDto extends ExportXlsDto<PurchaseStatisticsVo> {

	public FirstPurchaseExportXlsDto() {
		setSheetName("首次购买明细");
	}

	@Override
	public void addTitles() {
		addTitle("注册日期");
		addTitle("注册手机号");
		addTitle("用户名");
		addTitle("首次购买金额");
		addTitle("银行卡信息");
		addTitle("推荐人手机号");
		addTitle("推荐人");
	}

	@Override
	public void addFieldNames() {
		addFieldName("registrationDate");
		addFieldName("mobile");
		addFieldName("name");
		addFieldName("tradeAmount");
		addFieldName("bankName");
		addFieldName("referrerMobile");
		addFieldName("referrerName");
	}

	@Override
	public void setCellStyle(Label label) {

	}
}
