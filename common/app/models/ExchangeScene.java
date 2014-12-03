package models;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeScene extends IdEntity {

    private String scene;

    private String title;

    private String status;

    private String rewardType;

    private String exchangeType;

    private Long requireAmt;

    private Long exchangedAmt;

    private Long exchangeLimit;

    private Integer timeLimit;
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

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Long getRequireAmt() {
        return requireAmt;
    }

    public void setRequireAmt(Long requireAmt) {
        this.requireAmt = requireAmt;
    }

    public Long getExchangedAmt() {
        return exchangedAmt;
    }

    public void setExchangedAmt(Long exchangedAmt) {
        this.exchangedAmt = exchangedAmt;
    }

    public Long getExchangeLimit() {
        return exchangeLimit;
    }

    public void setExchangeLimit(Long exchangeLimit) {
        this.exchangeLimit = exchangeLimit;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
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
