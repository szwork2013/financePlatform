package com.sunlights.op.dto;

import com.sunlights.op.common.constants.ActivityConstant;
import com.sunlights.op.vo.activity.ExchangeBeanResultVo;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;


/**
 * Created by Administrator on 2014/12/23.
 */
public class ExchangeBeanResultExportXlsDto extends ExportXlsDto<ExchangeBeanResultVo> {
    public ExchangeBeanResultExportXlsDto() {
        setSheetName("金豆兑换话费");
    }

    @Override
    public void addTitles() {
        addTitle("ID");
        addTitle("兑换日期");
        addTitle("注册手机号");
        addTitle("兑换手机号");
        addTitle("兑换金额（元）");
        addTitle("兑换金豆");
        addTitle("运营商");
        addTitle("状态描述");
        addTitle("已兑换金额（元）");
    }

    @Override
    public void addFieldNames() {
        addFieldName("id");
        addFieldName("exchangeTime");
        addFieldName("registerMobile");
        addFieldName("exchangeMobile");
        addFieldName("exchangeAmount");
        addFieldName("exchangeBean");
        addFieldName("carrierName");
        addFieldName("statusDesc");
        addFieldName("hasExchangeAmount");
    }

    @Override
    public void setCellStyle (Label label) {
        int column = label.getColumn();
        String value = label.getString();
        if (column == 7) {
            try {
                Colour colour = getBackColorByValue(value);
                WritableCellFormat colourFormat = new WritableCellFormat();
                colourFormat.setBackground(colour);
                label.setCellFormat(colourFormat);
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }

    private Colour getBackColorByValue (String value) {
        Colour colour = null;
        switch (value) {
            case ActivityConstant.AUDIT_PASS_DESC:
                colour = Colour.YELLOW;
                break;
            case ActivityConstant.EXCHANGEING_DESC:
                colour = Colour.BLACK;
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
