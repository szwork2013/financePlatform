package models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * <p>Project: fsp</p>
 * <p>Title: PrdAccountConfig.java</p>
 * <p>Description: 产品-账户关系配置</p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "PRD_ACCOUNT_CONFIG")
public class PrdAccountConfig extends IdEntity {
    @Column(name = "PRD_TYPE_CODE", length = 50)
    private String prdTypeCode;
    @Column(name = "SUB_ACCOUNT", length = 10)
    private String subAccount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETE_TIME")
    private Date deleteTime;

    public PrdAccountConfig() {
    }

    public String getPrdTypeCode() {
        return prdTypeCode;
    }

    public void setPrdTypeCode(String prdTypeCode) {
        this.prdTypeCode = prdTypeCode;
    }

    public String getSubAccount() {
        return subAccount;
    }

    public void setSubAccount(String subAccount) {
        this.subAccount = subAccount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }
}
