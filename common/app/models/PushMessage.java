package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: PushMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "push_message")
public class PushMessage extends IdEntity {
    @Column(name = "push_config_id")
    private Long pushConfigId;
    private String title;
    private String content;
    @Column(name = "push_type", length = 50)
    private String pushType;
    @Column(name = "push_status", length = 50)
    private String pushStatus;
    private String remarks;
    @Column(name = "send_no")
    private String sendNo;
    @Column(name = "msg_id")
    private String msgId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "push_plan_time")
    private Date pushPlanTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "push_time")
    private Date pushTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    public Long getPushConfigId() {
        return pushConfigId;
    }

    public void setPushConfigId(Long pushConfigId) {
        this.pushConfigId = pushConfigId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
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

    public String getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(String pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getPushPlanTime() {
        return pushPlanTime;
    }

    public void setPushPlanTime(Date pushPlanTime) {
        this.pushPlanTime = pushPlanTime;
    }
}
