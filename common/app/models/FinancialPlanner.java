package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Yuan on 2015/1/28.
 */
@Entity
@Table(name = "c_financial_planner")
public class FinancialPlanner extends IdEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "manager_id")
    private Long managerId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "deleted")
    private Boolean deleted;

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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
