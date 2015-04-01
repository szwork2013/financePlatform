package com.sunlights.op.common.util;

import com.google.common.base.Splitter;
import com.sunlights.common.utils.CommonUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.beanutils.ConvertUtils;
import play.mvc.Results;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: Data2ExcelUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class Data2ExcelUtil {
    public static <O> Results.Chunks<byte[]> getChunks(String sheetName, final String titles, final String fieldNames, final List<O> list) {
        final String _sheetName = sheetName;
        
        return new Results.ByteChunks() {

            public void onReady(Out<byte[]> out) {

                ByteArrayOutputStream bao = new ByteArrayOutputStream();

                WritableWorkbook workbook = null;
                try {
                    workbook = Workbook.createWorkbook(bao);

                    WritableSheet sheet = workbook.createSheet(_sheetName, 0);

                    writeExcelContent(sheet, titles, fieldNames, list);

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

    private static <O> void writeExcelContent(WritableSheet sheet, String titles, String fieldNames, List<O> list) throws Exception{
        final List<String> _titleList = Splitter.on(",").trimResults().splitToList(titles);
        final List<String> fieldNameList = Splitter.on(",").trimResults().splitToList(fieldNames);
        final List<O> _list = list;

        for (int i = 0; i < _titleList.size(); i++) {
            Label label = new Label(i, 0, _titleList.get(i));
            sheet.addCell(label);
        }

        for (int i = 1; i <= _list.size(); i++) {
            O o = _list.get(i - 1);
            for (int j = 0; j < fieldNameList.size(); j++) {
                String fieldName = fieldNameList.get(j);
                String fieldGetName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = o.getClass().getMethod(fieldGetName);
                Object obj = method.invoke(o);
                String value = null;
                if (obj instanceof Date) {
                    value = CommonUtil.dateToString((Date) obj, CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM);
                }else if(obj == null){
                    value = "";
                } else {
                    value = (String) ConvertUtils.convert(obj.toString(), String.class);
                }
                Label label = new Label(j, i, value);
                sheet.addCell(label);
            }
        }
    }
}
