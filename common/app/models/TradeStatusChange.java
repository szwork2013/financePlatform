package models;

import java.util.Date;

import javax.persistence.*;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: TradeStatusChange.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

@Entity
    @Table(name = "t_trade_status_change_info")
public class TradeStatusChange extends BaseEntity {
    @Column(length = 20, name = "trade_no")
    private String tradeNo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "trade_time")
    private Date tradeTime;
    @Column(name = "status_change_time", length = 200)
    private String statusChangeTime;

    @Column(length = 20, name = "trade_type")
    private String tradeType;
    @Column(length = 20, name = "status_desc")
    private String statusDesc;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(String statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
