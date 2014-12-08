package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/12/8.
 */
@Entity
@Table(name = "f_activity_share_info")
public class ActivityShareInfo extends IdEntity {
    @Column(name = "ACTIVITY_ID")
    private Long activityId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "SHARE_URL")
    private String shortUrl;
    @Column(name = "ICON_URL")
    private String iocnUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getIocnUrl() {
        return iocnUrl;
    }

    public void setIocnUrl(String iocnUrl) {
        this.iocnUrl = iocnUrl;
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
