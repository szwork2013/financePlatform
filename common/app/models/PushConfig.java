package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: PushConfig.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "push_config")
public class PushConfig extends IdEntity {
    @Column(name = "platform", length = 50)
    private String platform;//安卓 ios
    @Column(name = "push_type", length = 50)
    private String pushType;//推送类型 系统推送/活动推送
    @Column(name = "time_to_live")
    private Long timeToLive;//离线保留时间（min），不填默认为1天  0不保留离线消息
    @Column(name = "push_group_id")
    private String pushGroupId;
    @Column(name = "apns_producation")
    private boolean apnsProducation;//true生产环境 false开发环境
    @Column(name = "big_push_duration")
    private Long bigPushDuration;//定速推送，在min分内均匀推送，最大1440。不设置则非定速推送
    @Column(name = "status", length = 1)
    private String status;//有效标志
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
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

    public Long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getPushGroupId() {
        return pushGroupId;
    }

    public void setPushGroupId(String pushGroupId) {
        this.pushGroupId = pushGroupId;
    }

    public boolean getApnsProducation() {
        return apnsProducation;
    }

    public void setApnsProducation(boolean apnsProducation) {
        this.apnsProducation = apnsProducation;
    }

    public Long getBigPushDuration() {
        return bigPushDuration;
    }

    public void setBigPushDuration(Long bigPushDuration) {
        this.bigPushDuration = bigPushDuration;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
