package com.sunlights.common.models;


import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: fsp</p>
 * <p>Title: BaseEntity.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@MappedSuperclass
public abstract class BaseEntity extends IdEntity {
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATETIME")
    private Date createdDatetime;
    @Column(name = "CREATED_BY", length = 30)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATETIME")
    private Date updatedDatetime;

    @Column(name = "UPDATED_BY", length = 30)
    private String updatedBy;

    @Column(name = "DEFUNCT_IND", length = 1)//"CHAR(1)"?
    private String defunctInd;//String "Y" or "N" ?

    public Date getCreatedDatetime() {
        return createdDatetime;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }


    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getDefunctInd() {
        return defunctInd;
    }

    public void setDefunctInd(String defunctInd) {
        this.defunctInd = defunctInd;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @PrePersist
    public void initTimeStamps() {
        // we do this for the purpose of the demo, this lets us create our own
        // creation dates. Typically we would just set the createdOn field.
        if (createdDatetime == null) {
            createdDatetime = new Date();
        }
        updatedDatetime = createdDatetime;
        defunctInd = "N";
    }

    @PreUpdate
    public void updateTimeStamp() {
        updatedDatetime = new Date();
    }

}
