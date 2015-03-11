package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShuMiAccount.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "f_shumi_account")
@NamedQuery(name = "findShuMiAccount", query = "select sm from ShuMiAccount sm where sm.customerId = ?1")
public class ShuMiAccount extends IdEntity {
    @Column(name = "customer_id", length = 30)
    private String customerId;
    @Column(name = "shumi_tokenKey", length = 500)
    private String shumi_tokenKey;
    @Column(name = "shumi_tokenSecret", length = 500)
    private String shumi_tokenSecret;
    @Column(name = "shumi_userName", length = 50)
    private String shumi_userName;
    @Column(name = "shumi_realName", length = 50)
    private String shumi_realName;//
    @Column(name = "shumi_idNumber", length = 100)
    private String shumi_idNumber;//
    @Column(name = "shumi_bankName", length = 100)
    private String shumi_bankName;
    @Column(name = "shumi_bankCardNo", length = 100)
    private String shumi_bankCardNo;
    @Column(name = "shumi_bankSerial", length = 100)
    private String shumi_bankSerial;
    @Column(name = "shumi_phoneNum", length = 20)
    private String shumi_phoneNum;//
    @Column(name = "shumi_email", length = 50)
    private String shumi_email;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date create_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date update_time;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getShumi_tokenKey() {
        return shumi_tokenKey;
    }

    public void setShumi_tokenKey(String shumi_tokenKey) {
        this.shumi_tokenKey = shumi_tokenKey;
    }

    public String getShumi_tokenSecret() {
        return shumi_tokenSecret;
    }

    public void setShumi_tokenSecret(String shumi_tokenSecret) {
        this.shumi_tokenSecret = shumi_tokenSecret;
    }

    public String getShumi_userName() {
        return shumi_userName;
    }

    public void setShumi_userName(String shumi_userName) {
        this.shumi_userName = shumi_userName;
    }

    public String getShumi_realName() {
        return shumi_realName;
    }

    public void setShumi_realName(String shumi_realName) {
        this.shumi_realName = shumi_realName;
    }

    public String getShumi_idNumber() {
        return shumi_idNumber;
    }

    public void setShumi_idNumber(String shumi_idNumber) {
        this.shumi_idNumber = shumi_idNumber;
    }

    public String getShumi_bankName() {
        return shumi_bankName;
    }

    public void setShumi_bankName(String shumi_bankName) {
        this.shumi_bankName = shumi_bankName;
    }

    public String getShumi_bankCardNo() {
        return shumi_bankCardNo;
    }

    public void setShumi_bankCardNo(String shumi_bankCardNo) {
        this.shumi_bankCardNo = shumi_bankCardNo;
    }

    public String getShumi_bankSerial() {
        return shumi_bankSerial;
    }

    public void setShumi_bankSerial(String shumi_bankSerial) {
        this.shumi_bankSerial = shumi_bankSerial;
    }

    public String getShumi_phoneNum() {
        return shumi_phoneNum;
    }

    public void setShumi_phoneNum(String shumi_phoneNum) {
        this.shumi_phoneNum = shumi_phoneNum;
    }

    public String getShumi_email() {
        return shumi_email;
    }

    public void setShumi_email(String shumi_email) {
        this.shumi_email = shumi_email;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
