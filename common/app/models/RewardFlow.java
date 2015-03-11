package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/11/19.
 */
@Entity
@Table(name = "F_REWARD_FLOW")
public class RewardFlow extends IdEntity {
    @Column(name = "customer_Id")
    private String custId;
    @Column(name = "ACTIVITY_ID")
    private Long activityId;
    @Column(name = "Activity_title")
    private String activityTitle;
    @Column(name = "SCENE")
    private String scene;
    @Column(name = "REWARD_TYPE")
    private String rewardType;
    @Column(name = "ACTIVITY_TYPE")
    private String activityType;
    //1表示获取  2表示兑换
    @Column(name = "OPERATOR_TYPE")
    private Integer operatorType;
    @Column(name = "REWARD_AMT")
    private Long rewardAmt;
    @Column(name = "MONEY")
    private BigDecimal money;

    //状态 0-未发放，1-已发放，2-发放失败，4-撤回成功，5-撤回失败 6-已兑换
    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATE_TIME")
    private Date createTime;

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

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public Long getRewardAmt() {
        return rewardAmt;
    }

    public void setRewardAmt(Long rewardAmt) {
        this.rewardAmt = rewardAmt;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
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
}
