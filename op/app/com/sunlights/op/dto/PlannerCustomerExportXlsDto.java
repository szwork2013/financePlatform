package com.sunlights.op.dto;

import com.sunlights.op.vo.FinancialPlannerCustomerVo;
import jxl.write.Label;

/**
 * Created by Yuan on 2015/6/1.
 */
public class PlannerCustomerExportXlsDto extends ExportXlsDto<FinancialPlannerCustomerVo> {

	public PlannerCustomerExportXlsDto() {
		setSheetName("理财师客户统计");
	}

	@Override
	public void addTitles() {
		addTitle("理财师手机号");
		addTitle("理财师姓名");
		addTitle("被推荐人手机号");
		addTitle("被推荐人姓名");
		addTitle("被推荐人购买总数");
	}

	@Override
	public void addFieldNames() {
		addFieldName("mobilePhone");
		addFieldName("name");
		addFieldName("mobile");
		addFieldName("realName");
		addFieldName("totalAmount");
	}

	@Override
	public void setCellStyle(Label label) {

	}
}
