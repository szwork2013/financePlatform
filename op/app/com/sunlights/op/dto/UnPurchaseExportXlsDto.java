package com.sunlights.op.dto;

import com.sunlights.op.vo.PurchaseStatisticsVo;
import jxl.write.Label;

/**
 * Created by Yuan on 2015/3/26.
 */
public class UnPurchaseExportXlsDto extends ExportXlsDto<PurchaseStatisticsVo> {

	public UnPurchaseExportXlsDto() {
		setSheetName("注册未购买");
	}

	@Override
	public void addTitles() {
		addTitle("注册日期");
		addTitle("注册未购买人手机号");
		addTitle("用户名");
		addTitle("推荐人手机号");
		addTitle("推荐人");
	}

	@Override
	public void addFieldNames() {
		addFieldName("registrationDate");
		addFieldName("mobile");
		addFieldName("name");
		addFieldName("referrerMobile");
		addFieldName("referrerName");
	}

	@Override
	public void setCellStyle(Label label) {

	}
}
