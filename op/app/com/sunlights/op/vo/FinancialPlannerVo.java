package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.FinancialPlanner;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FinancialPlannerVo {
	private Long id;
	
    private String name;

    private String mobilePhone;

    private Long managerId;

    private Date createTime;

    private Date updateTime;

    public FinancialPlannerVo() {

    }

    public FinancialPlannerVo(FinancialPlanner financialPlanner) {
        inFinancialPlannerVo(financialPlanner);
    }

    private void inFinancialPlannerVo(FinancialPlanner financialPlanner) {
        this.id = financialPlanner.getId();
        this.name = financialPlanner.getName();
        this.mobilePhone = financialPlanner.getMobilePhone();
        this.managerId = financialPlanner.getManagerId();
        this.createTime = financialPlanner.getCreateTime();
        this.updateTime = financialPlanner.getUpdateTime();
    }

    public FinancialPlanner convertToFinancialPlanner() {
        Date date = new Date();
        FinancialPlanner financialPlanner = new FinancialPlanner();
        financialPlanner.setId(this.id);
        financialPlanner.setName(this.name);
        financialPlanner.setMobilePhone(this.mobilePhone);
        financialPlanner.setManagerId(this.managerId);
        financialPlanner.setCreateTime(this.id == null ? date : this.createTime);
        financialPlanner.setUpdateTime(date);
        return financialPlanner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
