package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tangweiqun on 2014/12/3.
 */
@Entity
@Table(name = "F_EXCHANGE_RESULT")
public class ExchangeResult extends IdEntity {
    @Column(name = "CUSTOMER_ID")
    private String custId;
    @Column(name = "EXCHANGE_SCENE")
    private String exchangeScene;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "BANK_CODE")
    private String bankCode;
    @Column(name = "bank_card_no")
    private String bankCardNo;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "BANKNAME")
    private String bankName;

    @Column(name = "carrier_code")
    private String carrierCode;
    @Column(name = "REWARD_FLOW_ID")
    private Long rewardFlowId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;


    @Column(name = "exchanged_amount")
    private BigDecimal exchangedAmount;

    @Column(name = "payment_receipt_no")
    private String paymentReceiptNo;


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public Long getRewardFlowId() {
        return rewardFlowId;
    }

    public void setRewardFlowId(Long rewardFlowId) {
        this.rewardFlowId = rewardFlowId;
    }

    public BigDecimal getExchangedAmount() {
        return exchangedAmount;
    }

    public void setExchangedAmount(BigDecimal exchangedAmount) {
        this.exchangedAmount = exchangedAmount;
    }

    public String getPaymentReceiptNo() {
        return paymentReceiptNo;
    }

    public void setPaymentReceiptNo(String paymentReceiptNo) {
        this.paymentReceiptNo = paymentReceiptNo;
    }
}
