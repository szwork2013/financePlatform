package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/12/3.
 */
@Entity
@Table(name = "F_EXCAHNGE_SCENE")
public class ExchangeResult extends IdEntity {
    @Column(name = "CUSTOMER_ID")
    private String custId;
    @Column(name = "EXCHANGE_SCENE")
    private String exchangeScene;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "BANK_CODE")
    private String bankCode;
    @Column(name = "BANK_CARD_NO")
    private String bankCardNo;
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getExchangeScene() {
        return exchangeScene;
    }

    public void setExchangeScene(String exchangeScene) {
        this.exchangeScene = exchangeScene;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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