package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Column(name = "PRD_CODE")
    private String prdCode;
    @Column(name = "PRD_TYPE")
    private String prdType;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public static enum ActivitySceneConstant {
        SIGN("ASC001","签到"),
        INVITE("ASC002","邀请好友"),
        PICTURE("ASC003","图片活动"),//没用到
        REGISTER("ASC004","注册"),
        FIRST_PURCHASE("ASC005","首次购买"),
        PURCHASE_RECOMMEND("ASC006","推荐场景"),//没用到
        PURCHASE("ASC007","购买"),//没用到
        EACHANGE_REDPACKET("EXC001","红包取现"),//没用到
        EXCHANGE_HUAFEI("EXC002","金豆兑换话费");//没用到

        private String scene;
        private String desc;

        private ActivitySceneConstant(String scene, String desc) {
            this.scene = scene;
            this.desc = desc;
        }

        public String getScene() {
            return scene;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByScene(String scene) {
            if(scene == null) {
                return null;
            }
            for(ActivitySceneConstant temp : ActivitySceneConstant.values()) {
                if(scene.equals(temp.getScene())) {
                    return temp.getDesc();
                }
            }
            return null;
        }
    }

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
