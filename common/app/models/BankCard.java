package models;


import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: fsp</p>
 * <p>Title: Bank.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "C_BANK_CARD")
public class BankCard extends IdEntity {
    @Column(name = "CUSTOMER_ID", length = 30)
    private String customerId;
    @Column(length = 40, name = "BANK_CODE")
    private String bankCode;
    @Column(length = 40, name = "BANK_CARD_NO")
    private String bankCardNo;
    @Column(length = 1, name = "BANK_TYPE")
    private String bankType;
    @Column(length = 40, name = "BANK_ID")
    private Long bankId;
    @Column(length = 1)
    private String status;
    @Column(length = 100, name = "bank_serial")
    private String bankSerial;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public BankCard() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getBankSerial() {
        return bankSerial;
    }

    public void setBankSerial(String bankSerial) {
        this.bankSerial = bankSerial;
    }
}