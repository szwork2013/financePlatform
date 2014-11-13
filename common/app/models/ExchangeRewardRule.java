package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by tangweiqun on 2014/11/12.
 */
@Entity
@Table(name = "F_EXCHANGE_REWARD_RULE")
public class ExchangeRewardRule extends BaseEntity{
    @Column(name = "REWARD_TYPE")
    private String rewardType;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "EXCHAGE_TYPE")
    private String exchangeType;
    @Column(name = "RATE")
    private BigDecimal rate;
    @Column(name = "LIMIT_TIME")
    private Integer limitTime;
    @Column(name = "NOTICE_TIME")
    private Integer noticeTime;
    @Column(name = "DELAY_TIME")
    private Integer delayTime;

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Integer noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }
}
