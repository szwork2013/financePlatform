package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/12/2.
 */
@Entity
@Table(name = "F_CUST_ACTIVITY_JOIN")
public class CustJoinActivity extends IdEntity {

    @Column(name = "CUSTOMER_ID")
    private String custId;
    @Column(name = "ACTIVITY_ID")
    private Long activityId;
    @Column(name = "SCENE")
    private String scene;
    @Column(name = "ISJOINED")
    private int joined;
    @Column(name = "ISCONTINUED")
    private int continued;
    @Column(name = "ISTIME_RELATE")
    private int relateTime;
    @Column(name = "JOIN_TIME")
    private Date joinTime;
    @Column(name = "SHORT_URL")
    private String shortUrl;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getJoined() {
        return joined;
    }

    public void setJoined(int joined) {
        this.joined = joined;
    }

    public int getContinued() {
        return continued;
    }

    public void setContinued(int continued) {
        this.continued = continued;
    }

    public int getRelateTime() {
        return relateTime;
    }

    public void setRelateTime(int relateTime) {
        this.relateTime = relateTime;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
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
