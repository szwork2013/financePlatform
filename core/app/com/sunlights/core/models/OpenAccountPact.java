package com.sunlights.core.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <p>Project: fsp</p>
 * <p>Title: OpenAccountPact.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
@Table(name = "c_open_account_pact")
public class OpenAccountPact {
    @Id
    @Column(length = 20, name = "agreement_no")
    private String agreementNo;
    @Column(length = 10, name = "sign_channel")
    private String signChannel;
    @Column(name = "agreement_start_date")
    private Timestamp agreementStartDate;
    @Column(name = "agreement_end_date")
    private Timestamp agreementEndDate;
    @Column(name = "agreement_effect_date")
    private Timestamp agreementEffectDate;
    @Column(name = "agreement_expiry_date")
    private Timestamp agreementExpiryDate;
    @Column(length = 1, name = "agreement_status")
    private String agreementStatus;
    @Column(length = 1, name = "sale_property")
    private String saleProperty;
    @Column(length = 1, name = "user_attribution")
    private String userAttribution;
    @Column(length = 40, name = "agreement_name")
    private String agreementName;
    @Column(length = 10, name = "agreement_file_type")
    private String agreementFileType;
    @Column(length = 100, name = "file_name")
    private String fileName;
    @Column(length = 200, name = "file_path")
    private String filePath;
    @Column(length = 1, name = "status")
    private String status;
    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "update_date")
    private Timestamp updateDate;

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public String getSignChannel() {
        return signChannel;
    }

    public void setSignChannel(String signChannel) {
        this.signChannel = signChannel;
    }

    public Timestamp getAgreementStartDate() {
        return agreementStartDate;
    }

    public void setAgreementStartDate(Timestamp agreementStartDate) {
        this.agreementStartDate = agreementStartDate;
    }

    public Timestamp getAgreementEndDate() {
        return agreementEndDate;
    }

    public void setAgreementEndDate(Timestamp agreementEndDate) {
        this.agreementEndDate = agreementEndDate;
    }

    public Timestamp getAgreementEffectDate() {
        return agreementEffectDate;
    }

    public void setAgreementEffectDate(Timestamp agreementEffectDate) {
        this.agreementEffectDate = agreementEffectDate;
    }

    public Timestamp getAgreementExpiryDate() {
        return agreementExpiryDate;
    }

    public void setAgreementExpiryDate(Timestamp agreementExpiryDate) {
        this.agreementExpiryDate = agreementExpiryDate;
    }

    public String getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(String agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    public String getSaleProperty() {
        return saleProperty;
    }

    public void setSaleProperty(String saleProperty) {
        this.saleProperty = saleProperty;
    }

    public String getUserAttribution() {
        return userAttribution;
    }

    public void setUserAttribution(String userAttribution) {
        this.userAttribution = userAttribution;
    }

    public String getAgreementName() {
        return agreementName;
    }

    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }

    public String getAgreementFileType() {
        return agreementFileType;
    }

    public void setAgreementFileType(String agreementFileType) {
        this.agreementFileType = agreementFileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
