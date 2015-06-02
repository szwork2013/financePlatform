package com.sunlights.op.dto;

import com.sunlights.op.vo.FinancialPlannerVo;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;

/**
 * Created by Yuan on 2015/6/1.
 */
public class FinancialPlannerExportXlsDto extends ExportXlsDto<FinancialPlannerVo> {

	public FinancialPlannerExportXlsDto() {
		setSheetName("理财师上传模板");
	}

	@Override
	public void addTitles() {
		addTitle("理财师手机号");
		addTitle("理财师姓名");
	}

	@Override
	public void addFieldNames() {
		addFieldName("mobilePhone");
		addFieldName("name");
	}

	@Override
	public void setCellStyle(Label label) {
	}
}
