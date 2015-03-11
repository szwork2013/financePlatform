package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2014/12/2.
 */
@Entity
@Table(name = "F_ACTIVITY_STEP")
public class ActivityStep extends IdEntity {
    @Column(name = "OBTAIN_RULE_ID")
    private Long obtainRuleId;
    @Column(name = "MIN_VALUE")
    private BigDecimal minValue;
    @Column(name = "MAX_VALUE")
    private BigDecimal maxValue;
    @Column(name = "SHOULD_REWARD")
    private Long shouldReward;

    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public Long getObtainRuleId() {
        return obtainRuleId;
    }

    public void setObtainRuleId(Long obtainRuleId) {
        this.obtainRuleId = obtainRuleId;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public Long getShouldReward() {
        return shouldReward;
    }

    public void setShouldReward(Long shouldReward) {
        this.shouldReward = shouldReward;
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
