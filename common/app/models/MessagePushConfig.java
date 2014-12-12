package models;

import java.util.Date;

import javax.persistence.*;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: MessagePushConfig.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "c_message_push_config")
public class MessagePushConfig extends IdEntity {
    private String platform;
    @Column(name = "push_type", length = 50)
    private String pushType;
    private String remarks;
    @Column(name = "group_id")
    private Long groupId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "plan_begin_time")
    private Date planBeginTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "plan_end_time")
    private Date planEndTime;
    @Column(name = "push_expiry", length = 50)
    private String pushExpiry;
    @Column(length = 1)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Date getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(Date planBeginTime) {
        this.planBeginTime = planBeginTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getPushExpiry() {
        return pushExpiry;
    }

    public void setPushExpiry(String pushExpiry) {
        this.pushExpiry = pushExpiry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
