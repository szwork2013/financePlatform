package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/12/23.
 */
@Entity
@Table(name = "F_ACTIVITY_RETURNCODE_MSG")
public class ActivityReturnMsg extends IdEntity {
    @Column(name = "SCENE")
    private String scene;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "REWARD_TYPE")
    private String rewardType;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "ERROR_CODE")
    private String errorCode;
    @Column(name = "TEMPLATE")
    private String template;

    @Temporal(TemporalType.TIMESTAMP)
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
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
