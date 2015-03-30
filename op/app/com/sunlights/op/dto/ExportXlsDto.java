package com.sunlights.op.dto;

import com.sunlights.common.utils.CommonUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.beanutils.ConvertUtils;
import play.mvc.Results;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuan on 2015/2/9.
 */
public abstract class ExportXlsDto<O> {

	private String sheetName;

	private List<String> titles = new ArrayList<String>();
	private List<String> fieldNames = new ArrayList<String>();

	private List<O> data = new ArrayList<O>();

	public abstract void addTitles();

	public abstract void addFieldNames();

	public abstract void setCellStyle(Label label);

	public Results.Chunks<byte[]> export() {
		addTitles();
		addFieldNames();

		return new Results.ByteChunks() {

			public void onReady(Out<byte[]> out) {
				ByteArrayOutputStream bao = new ByteArrayOutputStream();

				WritableWorkbook workbook = null;
				try {
					workbook = Workbook.createWorkbook(bao);

					WritableSheet sheet = workbook.createSheet(sheetName, 0);
					writeExcelContent(sheet, titles, fieldNames, data);

					workbook.write();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (workbook != null) {
							workbook.close();
						}
						bao.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				out.write(bao.toByteArray());
				out.close();
			}
		};
	}

	private <O> void writeExcelContent(WritableSheet sheet, List<String> titles, List<String> fieldNames, List<O> data) throws Exception {
		for (int i = 0; i < titles.size(); i++) {
			Label label = new Label(i, 0, titles.get(i));
			sheet.addCell(label);
		}

		for (int i = 1; i <= data.size(); i++) {
			O o = data.get(i - 1);
			for (int j = 0; j < fieldNames.size(); j++) {
				String fieldName = fieldNames.get(j);
				String fieldGetName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method method = o.getClass().getMethod(fieldGetName);
				Object obj = method.invoke(o);
				String value = null;
				if (obj instanceof Date) {
					value = CommonUtil.dateToString((Date) obj, CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM);
				} else if (obj == null) {
					value = "";
				} else {
					value = (String) ConvertUtils.convert(obj.toString(), String.class);
				}
				Label label = new Label(j, i, value);
				setCellStyle(label);
				sheet.addCell(label);
			}
		}
	}

	public void addTitle(String title) {
		this.titles.add(title);
	}

	public void addFieldName(String fieldName) {
		this.fieldNames.add(fieldName);
	}

	public List<O> getData() {
		return data;
	}

	public void setData(List<O> data) {
		this.data = data;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
