package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/12/15.
 */
@Entity
@Table(name = "c_share_info")
public class ShareInfo extends IdEntity {
    @Column(name = "TYPE")
    private String shareType;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "URL")
    private String baseUrl;
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @Column(name = "IS_REF_ID")
    private Integer relateRefId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
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

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRelateRefId() {
        return relateRefId;
    }

    public void setRelateRefId(Integer relateRefId) {
        this.relateRefId = relateRefId;
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
