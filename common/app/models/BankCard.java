package models;


import com.sunlights.common.AppConst;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @Column(name = "customer_id", length = 30)
    private String customerId;
    @Column(name = "bank_card_no")
    private String bankCardNo;//银行卡号
    @Column(name = "trade_account")
    private String TradeAccount;//基金交易账号
    @Column(name = "sub_trade_account")
    private String SubTradeAccount;
    @Column(name = "is_valid")
    private boolean IsVaild;//卡是否已经通过验证
    private String Balance;//每日额度限制，如果为0则说明没有限制
    private String Status;//卡的状态
    @Column(name = "status_to_cn")
    private String StatusToCN;
    @Column(name = "is_freeze")
    private String IsFreeze;//是否被冻结
    @Column(name = "bank_serial")
    private String BankSerial;
    @Column(name = "bank_name")
    private String BankName;
    @Column(name = "capital_mode")
    private String CapitalMode;//绑卡渠道
    @Column(name = "bind_way")
    private String BindWay;//绑卡方式
    @Column(name = "support_auto_pay")
    private boolean SupportAutoPay;
    @Column(name = "discount_rate")
    private BigDecimal DiscountRate;//购买时折扣
    @Column(name = "limit_describe")
    private String LimitDescribe;//单笔50万元，日累计50万元
    @Column(name = "content_describe")
    private String ContentDescribe;//必须开通网上银行
    private String Priority;
    @Column(name = "is_p2p")
    private boolean IsP2p;

    private boolean deleted = false;
    @Column(name = "load_all_ind")
    private String loadAllInd = AppConst.STATUS_INVALID;//导入标志 Y全部加载 N单个绑卡

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    public BankCard() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTradeAccount() {
        return TradeAccount;
    }

    public void setTradeAccount(String tradeAccount) {
        this.TradeAccount = tradeAccount;
    }

    public boolean isVaild() {
        return IsVaild;
    }

    public void setVaild(boolean isValid) {
        this.IsVaild = isValid;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        this.Balance = balance;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getStatusToCN() {
        return StatusToCN;
    }

    public void setStatusToCN(String statusToCN) {
        this.StatusToCN = statusToCN;
    }

    public String getIsFreeze() {
        return IsFreeze;
    }

    public void setIsFreeze(String isFreeze) {
        this.IsFreeze = isFreeze;
    }

    public String getBankSerial() {
        return BankSerial;
    }

    public void setBankSerial(String bankSerial) {
        this.BankSerial = bankSerial;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        this.BankName = bankName;
    }

    public String getCapitalMode() {
        return CapitalMode;
    }

    public void setCapitalMode(String capitalMode) {
        this.CapitalMode = capitalMode;
    }

    public String getBindWay() {
        return BindWay;
    }

    public void setBindWay(String bindWay) {
        this.BindWay = bindWay;
    }

    public boolean isSupportAutoPay() {
        return SupportAutoPay;
    }

    public void setSupportAutoPay(boolean supportAutoPay) {
        this.SupportAutoPay = supportAutoPay;
    }

    public BigDecimal getDiscountRate() {
        return DiscountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.DiscountRate = discountRate;
    }

    public String getLimitDescribe() {
        return LimitDescribe;
    }

    public void setLimitDescribe(String limitDescribe) {
        this.LimitDescribe = limitDescribe;
    }

    public String getContentDescribe() {
        return ContentDescribe;
    }

    public void setContentDescribe(String contentDescribe) {
        this.ContentDescribe = contentDescribe;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        this.Priority = priority;
    }

    public boolean isP2p() {
        return IsP2p;
    }

    public void setP2p(boolean isP2p) {
        this.IsP2p = isP2p;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getLoadAllInd() {
        return loadAllInd;
    }

    public void setLoadAllInd(String loadAllInd) {
        this.loadAllInd = loadAllInd;
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

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSubTradeAccount() {
        return SubTradeAccount;
    }

    public void setSubTradeAccount(String subTradeAccount) {
        SubTradeAccount = subTradeAccount;
    }
}
