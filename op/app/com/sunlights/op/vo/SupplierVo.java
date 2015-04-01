package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.Supplier;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: SupplierVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SupplierVo {
    private Long id;
    private String supplierType;
    private String supplierCode;
    private String merchantName;
    private String displayName;
    private String belongAddress;
    private String contactCallno;
    private String corporateProperty;
    private String websiteAddress;
    private String merchantIntroduction;
    private BigDecimal regCapital;
    private BigDecimal paidUpCapital;
    private String regProv;
    private String regCity;
    private String regAddress;
    private Date createTime;
    private Date updateTime;

    public SupplierVo() {
        super();
    }

    public SupplierVo(Supplier supplier) {
        inSupplier(supplier);
    }

    public void inSupplier(Supplier supplier) {
        this.id = supplier.getId();
        this.supplierType = supplier.getSupplierType();
        this.supplierCode = supplier.getSupplierCode();
        this.merchantName = supplier.getMerchantName();
        this.displayName = supplier.getDisplayName();
        this.belongAddress = supplier.getBelongAddress();
        this.contactCallno = supplier.getContactCallno();
        this.corporateProperty = supplier.getCorporateProperty();
        this.websiteAddress = supplier.getWebsiteAddress();
        this.merchantIntroduction = supplier.getMerchantIntroduction();
        this.regCapital = supplier.getRegCapital();
        this.paidUpCapital = supplier.getPaidUpCapital();
        this.regProv = supplier.getRegProv();
        this.regCity = supplier.getRegCity();
        this.regAddress = supplier.getRegAddress();
        this.createTime = supplier.getCreateTime();
        this.updateTime = supplier.getUpdateTime();
    }

    public Supplier convertToSupplier() {
        Supplier supplier = new Supplier();
        supplier.setId(this.id);
        supplier.setSupplierType(this.supplierType);
        supplier.setSupplierCode(this.supplierCode);
        supplier.setMerchantName(this.merchantName);
        supplier.setDisplayName(this.displayName);
        supplier.setBelongAddress(this.belongAddress);
        supplier.setContactCallno(this.contactCallno);
        supplier.setCorporateProperty(this.corporateProperty);
        supplier.setWebsiteAddress(this.websiteAddress);
        supplier.setMerchantIntroduction(this.merchantIntroduction);
        supplier.setRegCapital(this.regCapital);
        supplier.setPaidUpCapital(this.paidUpCapital);
        supplier.setRegProv(this.regProv);
        supplier.setRegCity(this.regCity);
        supplier.setRegAddress(this.regAddress);
        supplier.setCreateTime(this.createTime);
        supplier.setUpdateTime(this.updateTime);
        return supplier;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBelongAddress() {
        return belongAddress;
    }

    public void setBelongAddress(String belongAddress) {
        this.belongAddress = belongAddress;
    }

    public String getContactCallno() {
        return contactCallno;
    }

    public void setContactCallno(String contactCallno) {
        this.contactCallno = contactCallno;
    }

    public String getCorporateProperty() {
        return corporateProperty;
    }

    public void setCorporateProperty(String corporateProperty) {
        this.corporateProperty = corporateProperty;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public String getMerchantIntroduction() {
        return merchantIntroduction;
    }

    public void setMerchantIntroduction(String merchantIntroduction) {
        this.merchantIntroduction = merchantIntroduction;
    }

    public BigDecimal getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(BigDecimal regCapital) {
        this.regCapital = regCapital;
    }

    public BigDecimal getPaidUpCapital() {
        return paidUpCapital;
    }

    public void setPaidUpCapital(BigDecimal paidUpCapital) {
        this.paidUpCapital = paidUpCapital;
    }

    public String getRegProv() {
        return regProv;
    }

    public void setRegProv(String regProv) {
        this.regProv = regProv;
    }

    public String getRegCity() {
        return regCity;
    }

    public void setRegCity(String regCity) {
        this.regCity = regCity;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
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
