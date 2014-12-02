package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by guxuelong on 2014/12/2.
 */
@Entity
@Table(name = "t_chk_result")
public class TChkResultEntity extends  IdEntity{
    @Column(name = "chk_date")
    private Timestamp chkDate;
    @Column(name = "trade_no")
    private String tradeNo;
    @Column(name = "partner_id")
    private String partnerId;
    @Column(name = "trade_date")
    private Timestamp tradeDate;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "trade_type")
    private String tradeType;
    @Column(name = "trade_amount")
    private BigDecimal tradeAmount;
    @Column(name = "chk_status")
    private String chkStatus;
    @Column(name = "stl_status")
    private String stlStatus;
    @Column(name = "err_detail")
    private String errDetail;
    @Column(name = "create_time")
    private Timestamp createTime;
    @Column(name = "update_time")
    private Timestamp updateTime;


    public Timestamp getChkDate() {
        return chkDate;
    }

    public void setChkDate(Timestamp chkDate) {
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


    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }


    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }


    public String getChkStatus() {
        return chkStatus;
    }

    public void setChkStatus(String chkStatus) {
        this.chkStatus = chkStatus;
    }


    public String getStlStatus() {
        return stlStatus;
    }

    public void setStlStatus(String stlStatus) {
        this.stlStatus = stlStatus;
    }


    public String getErrDetail() {
        return errDetail;
    }

    public void setErrDetail(String errDetail) {
        this.errDetail = errDetail;
    }


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TChkResultEntity that = (TChkResultEntity) o;

        if (getId() != that.getId()) return false;
        if (chkDate != null ? !chkDate.equals(that.chkDate) : that.chkDate != null) return false;
        if (chkStatus != null ? !chkStatus.equals(that.chkStatus) : that.chkStatus != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (errDetail != null ? !errDetail.equals(that.errDetail) : that.errDetail != null) return false;
        if (partnerId != null ? !partnerId.equals(that.partnerId) : that.partnerId != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productType != null ? !productType.equals(that.productType) : that.productType != null) return false;
        if (stlStatus != null ? !stlStatus.equals(that.stlStatus) : that.stlStatus != null) return false;
        if (tradeAmount != null ? !tradeAmount.equals(that.tradeAmount) : that.tradeAmount != null) return false;
        if (tradeDate != null ? !tradeDate.equals(that.tradeDate) : that.tradeDate != null) return false;
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
        result = 31 * result + (tradeDate != null ? tradeDate.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (tradeType != null ? tradeType.hashCode() : 0);
        result = 31 * result + (tradeAmount != null ? tradeAmount.hashCode() : 0);
        result = 31 * result + (chkStatus != null ? chkStatus.hashCode() : 0);
        result = 31 * result + (stlStatus != null ? stlStatus.hashCode() : 0);
        result = 31 * result + (errDetail != null ? errDetail.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
