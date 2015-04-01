package com.sunlights.op.dto;

import com.sunlights.op.vo.statistics.ReferrerVo;
import jxl.write.Label;

/**
 * Created by Administrator on 2015/3/26.
 */
public class ReferrerExportXlsDto extends ExportXlsDto<ReferrerVo> {

	public ReferrerExportXlsDto () {
		setSheetName("推荐人统计");
	}

	@Override
	public void addTitles() {
		addTitle("推荐人手机号");
		addTitle("推荐人");
		addTitle("推荐注册人数");
		addTitle("推荐成功购买人数");
		addTitle("注册未购买人数");
		addTitle("推荐购买总金额");
	}

	@Override
	public void addFieldNames() {
		addFieldName("mobile");
		addFieldName("name");
		addFieldName("referrerTotality");
		addFieldName("purchaserCount");
		addFieldName("unPurchaserCount");
		addFieldName("recommendedPurchaseAmount");
	}

	@Override
	public void setCellStyle(Label label) {

	}
}
