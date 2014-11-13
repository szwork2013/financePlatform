package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: Supplier.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
@javax.persistence.Table(name = "c_supplier", schema = "public")
public class Supplier extends IdEntity {
    @javax.persistence.Column(name = "supplier_type")
    private String supplierType;


    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    @javax.persistence.Column(name = "supplier_code")
    private String supplierCode;


    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @javax.persistence.Column(name = "merchant_name")
    private String merchantName;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }


    @javax.persistence.Column(name = "display_name")
    private String displayName;


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @javax.persistence.Column(name = "belong_address")
    private String belongAddress;

    public String getBelongAddress() {
        return belongAddress;
    }

    public void setBelongAddress(String belongAddress) {
        this.belongAddress = belongAddress;
    }

    @javax.persistence.Column(name = "contact_callno")
    private String contactCallno;

    public String getContactCallno() {
        return contactCallno;
    }

    public void setContactCallno(String contactCallno) {
        this.contactCallno = contactCallno;
    }

    @javax.persistence.Column(name = "corporate_property")
    private String corporateProperty;

    public String getCorporateProperty() {
        return corporateProperty;
    }

    public void setCorporateProperty(String corporateProperty) {
        this.corporateProperty = corporateProperty;
    }

    @javax.persistence.Column(name = "website_address")
    private String websiteAddress;

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    @javax.persistence.Column(name = "merchant_introduction")
    private String merchantIntroduction;

    public String getMerchantIntroduction() {
        return merchantIntroduction;
    }

    public void setMerchantIntroduction(String merchantIntroduction) {
        this.merchantIntroduction = merchantIntroduction;
    }

    @javax.persistence.Column(name = "reg_capital")
    private BigDecimal regCapital;

    public BigDecimal getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(BigDecimal regCapital) {
        this.regCapital = regCapital;
    }

    @javax.persistence.Column(name = "paid_up_capital")
    private BigDecimal paidUpCapital;

    public BigDecimal getPaidUpCapital() {
        return paidUpCapital;
    }

    public void setPaidUpCapital(BigDecimal paidUpCapital) {
        this.paidUpCapital = paidUpCapital;
    }

    @javax.persistence.Column(name = "reg_prov")
    private String regProv;

    public String getRegProv() {
        return regProv;
    }

    public void setRegProv(String regProv) {
        this.regProv = regProv;
    }

    @javax.persistence.Column(name = "reg_city")
    private String regCity;

    public String getRegCity() {
        return regCity;
    }

    public void setRegCity(String regCity) {
        this.regCity = regCity;
    }

    @javax.persistence.Column(name = "reg_address")
    private String regAddress;

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "update_time")
    private Date updateTime;

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

        Supplier supplier = (Supplier) o;

        if (getId() != supplier.getId()) return false;
        if (belongAddress != null ? !belongAddress.equals(supplier.belongAddress) : supplier.belongAddress != null)
            return false;
        if (contactCallno != null ? !contactCallno.equals(supplier.contactCallno) : supplier.contactCallno != null)
            return false;
        if (corporateProperty != null ? !corporateProperty.equals(supplier.corporateProperty) : supplier.corporateProperty != null)
            return false;
        if (createTime != null ? !createTime.equals(supplier.createTime) : supplier.createTime != null) return false;
        if (displayName != null ? !displayName.equals(supplier.displayName) : supplier.displayName != null)
            return false;
        if (merchantIntroduction != null ? !merchantIntroduction.equals(supplier.merchantIntroduction) : supplier.merchantIntroduction != null)
            return false;
        if (merchantName != null ? !merchantName.equals(supplier.merchantName) : supplier.merchantName != null)
            return false;
        if (paidUpCapital != null ? !paidUpCapital.equals(supplier.paidUpCapital) : supplier.paidUpCapital != null)
            return false;
        if (regAddress != null ? !regAddress.equals(supplier.regAddress) : supplier.regAddress != null) return false;
        if (regCapital != null ? !regCapital.equals(supplier.regCapital) : supplier.regCapital != null) return false;
        if (regCity != null ? !regCity.equals(supplier.regCity) : supplier.regCity != null) return false;
        if (regProv != null ? !regProv.equals(supplier.regProv) : supplier.regProv != null) return false;
        if (supplierType != null ? !supplierType.equals(supplier.supplierType) : supplier.supplierType != null)
            return false;
        if (updateTime != null ? !updateTime.equals(supplier.updateTime) : supplier.updateTime != null) return false;
        if (websiteAddress != null ? !websiteAddress.equals(supplier.websiteAddress) : supplier.websiteAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (supplierType != null ? supplierType.hashCode() : 0);
        result = 31 * result + (merchantName != null ? merchantName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (belongAddress != null ? belongAddress.hashCode() : 0);
        result = 31 * result + (contactCallno != null ? contactCallno.hashCode() : 0);
        result = 31 * result + (corporateProperty != null ? corporateProperty.hashCode() : 0);
        result = 31 * result + (websiteAddress != null ? websiteAddress.hashCode() : 0);
        result = 31 * result + (merchantIntroduction != null ? merchantIntroduction.hashCode() : 0);
        result = 31 * result + (regCapital != null ? regCapital.hashCode() : 0);
        result = 31 * result + (paidUpCapital != null ? paidUpCapital.hashCode() : 0);
        result = 31 * result + (regProv != null ? regProv.hashCode() : 0);
        result = 31 * result + (regCity != null ? regCity.hashCode() : 0);
        result = 31 * result + (regAddress != null ? regAddress.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
