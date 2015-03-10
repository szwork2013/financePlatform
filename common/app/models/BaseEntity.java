package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: op</p>
 * <p>Title: BaseEntity.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:xiaobin.sheng@sunlights.cc">shenxiaobin</a>
 */
@MappedSuperclass
public abstract class BaseEntity extends IdEntity {
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

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


    @PrePersist
    public void initTimeStamps() {
        // we do this for the purpose of the demo, this lets us create our own
        // creation dates. Typically we would just set the createdOn field.
        if (createTime == null) {
            createTime = new Date();
        }
        updateTime = createTime;
    }

    @PreUpdate
    public void updateTimeStamp() {
        updateTime = new Date();
    }

}