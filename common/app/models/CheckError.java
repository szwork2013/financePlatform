package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by guxuelong on 2014/12/2.
 */
@Entity
@Table(name = "t_chk_err")
public class CheckError extends IdEntity {
    @Column(name = "chk_date")
    private Date chkDate;
    @Column(name = "trade_no")
    private String tradeNo;
    @Column(name = "partner_id")
    private String partnerId;
    @Column(name = "trade_type")
    private String tradeType;
    @Column(name = "deal_status")
    private String dealStatus;
    @Column(name = "err_detail")
    private String errDetail;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    public Date getChkDate() {
        return chkDate;
    }
    public void setChkDate(Date chkDate) {
        this.chkDate = chkDate;
    }

    public String getTradeNo() {
        return tradeNo;
    }
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getTradeType() {
        return tradeType;
    }
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getDealStatus() {
        return dealStatus;
    }
    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getErrDetail() {
        return errDetail;
    }
    public void setErrDetail(String errDetail) {
        this.errDetail = errDetail;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckError that = (CheckError) o;

        if (getId() != that.getId()) return false;
        if (chkDate != null ? !chkDate.equals(that.chkDate) : that.chkDate != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (dealStatus != null ? !dealStatus.equals(that.dealStatus) : that.dealStatus != null) return false;
        if (errDetail != null ? !errDetail.equals(that.errDetail) : that.errDetail != null) return false;
        if (partnerId != null ? !partnerId.equals(that.partnerId) : that.partnerId != null) return false;
        if (tradeNo != null ? !tradeNo.equals(that.tradeNo) : that.tradeNo != null) return false;
        if (tradeType != null ? !tradeType.equals(that.tradeType) : that.tradeType != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() != null ? getId() ^ (getId() >>> 32) : 0);
        result = 31 * result + (chkDate != null ? chkDate.hashCode() : 0);
        result = 31 * result + (tradeNo != null ? tradeNo.hashCode() : 0);
        result = 31 * result + (partnerId != null ? partnerId.hashCode() : 0);
        result = 31 * result + (tradeType != null ? tradeType.hashCode() : 0);
        result = 31 * result + (dealStatus != null ? dealStatus.hashCode() : 0);
        result = 31 * result + (errDetail != null ? errDetail.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
