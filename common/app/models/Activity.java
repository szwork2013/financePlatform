package models;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by tangweiqun on 2014/11/12.
 */
@Entity
@Table(name = "F_ACTIVITY")
public class Activity extends IdEntity {
    @Column(name = "SCENE")
    private String scene;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TITLE")
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BEGIN_TIME")
    private Date beginTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME")
    private Date endTime;
    @Column(name = "APP_ID")
    private Long appId;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "URL")
    private String url;

    @Column(name = "CLICK_TIME")
    private Long clickTime;
    @Column(name = "STYLE")
    private String style;
    @Column(name = "CLICK_EVENT")
    private String clickEvent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "CREATE_BY", length = 30)
    private String createBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Column(name = "UPDATE_BY", length = 30)
    private String updateBy;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getAppId() {
        return appId;
    }



    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getClickTime() {
        return clickTime;
    }

    public void setClickTime(Long clickTime) {
        this.clickTime = clickTime;
    }



    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getClickEvent() {
        return clickEvent;
    }

    public void setClickEvent(String clickEvent) {
        this.clickEvent = clickEvent;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }


}
