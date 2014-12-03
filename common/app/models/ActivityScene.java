package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/12/2.
 */
@Entity
@Table(name = "F_ACTIVITY_SCENE")
public class ActivityScene extends IdEntity {
    @Column(name = "CODE")
    private String scene;
    @Column(name = "NAME")
    private String title;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ACTIVITY_TYPE")
    private String activityType;
    @Column(name = "PRD_TYPE")
    private String prdCode;
    @Column(name = "PRD_CODE")
    private String prdType;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public String getPrdType() {
        return prdType;
    }

    public void setPrdType(String prdType) {
        this.prdType = prdType;
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
