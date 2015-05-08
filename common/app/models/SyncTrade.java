package models;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Edward.tian on 2015/5/7 0007.
 */

@Table(name="t_sync_trade")
public class SyncTrade extends IdEntity{

    @Column(name="customer_id")
    private String customerId;

    @Column(name="Fund_Code")
    private String fundCode;

    @Column(name="Fund_Name")
    private String fundName;

    @Column(name="Bank_Serial")
    private String bankSerial;

    @Column(name="bank_name")
    private String bankName;

    @Column(name="Bank_Account")
    private String bankAccount;

    @Column(name="Trade_Account")
    private String tradeAccount;

    @Column(name="Share_Type")
    private String shareType;

    @Column(name="Apply_Date_Time")
    private Date applyDateTime;

    @Column(name="Confirm_Date")
    private Date confirmDate;

    @Column(name="Business_Type")
    private String businessType;

    @Column(name="Amount")
    private double amount;

    @Column(name="Shares")
    private double shares;

    @Column(name="STATUS")
    private String status;

    @Column(name="Apply_Serial")
    private String applySerial;

    @Column(name="PayResult")
    private String payResult;

    @Column(name="PoundAge")
    private double poundAge;

    @Column(name="create_time")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getBankSerial() {
        return bankSerial;
    }

    public void setBankSerial(String bankSerial) {
        this.bankSerial = bankSerial;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getTradeAccount() {
        return tradeAccount;
    }

    public void setTradeAccount(String tradeAccount) {
        this.tradeAccount = tradeAccount;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public Date getApplyDateTime() {
        return applyDateTime;
    }

    public void setApplyDateTime(Date applyDateTime) {
        this.applyDateTime = applyDateTime;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getShares() {
        return shares;
    }

    public void setShares(double shares) {
        this.shares = shares;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplySerial() {
        return applySerial;
    }

    public void setApplySerial(String applySerial) {
        this.applySerial = applySerial;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public double getPoundAge() {
        return poundAge;
    }

    public void setPoundAge(double poundAge) {
        this.poundAge = poundAge;
    }
}
