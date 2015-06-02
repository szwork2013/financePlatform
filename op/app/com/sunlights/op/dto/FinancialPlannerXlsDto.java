package com.sunlights.op.dto;

import models.FinancialPlanner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yuan on 2015/6/1.
 */
public class FinancialPlannerXlsDto extends BaseXlsDto {
    private String name;
    private String mobile;

    {
        addName("mobile");
        addName("name");
        setStartRow(1);
    }

    public FinancialPlanner convertToFinancialPlanner() {
        Date date = new Date();
        FinancialPlanner financialPlanner = new FinancialPlanner();
        financialPlanner.setName(this.name);
        BigDecimal mobile = new BigDecimal(this.mobile);
        mobile.setScale(0);
        financialPlanner.setMobilePhone(mobile.intValue() + "");
        financialPlanner.setCreateTime(date);
        financialPlanner.setDeleted(false);
        financialPlanner.setUpdateTime(date);
        return financialPlanner;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
