package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "p_fund_company", schema = "public")
public class FundCompany extends IdEntity {

    @Column(name = "company_name", length = 50)
    private String companyName;//公司名称

    @Column(name = "fund_company_id")
    private String fundCompanyId;

    @Column(name = "pin_yin_code")
    private String pinYinCode;

    @Column(name = "abbr_name")
    private String abbrName;

    @Column(name = "background")
    private String background;

    @Column(name = "contact_addr")
    private String contactAddr;

    @Column(name = "direct_sell_url")
    private String directSellUrl;

    @Column(name = "email")
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "establishment_date")
    private Date establishmentDate;

    @Column(name = "fax")
    private String fax;

    @Column(name = "general_manager")
    private String generalManager;

    @Column(name = "legal_repr")
    private String legalRepr;

    @Column(name = "link_man")
    private String linkMan;

    @Column(name = "office_addr")
    private String officeAddr;

    @Column(name = "reg_addr")
    private String regAddr;

    @Column(name = "reg_capital")
    private BigDecimal regCapital;

    @Column(name = "service_line")
    private String serviceLine;

    @Column(name = "tel")
    private String tel;

    @Column(name = "web_site")
    private String webSite;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "is_fund_company")
    private String isFundCompany;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFundCompanyId() {
        return fundCompanyId;
    }

    public void setFundCompanyId(String fundCompanyId) {
        this.fundCompanyId = fundCompanyId;
    }

    public String getPinYinCode() {
        return pinYinCode;
    }

    public void setPinYinCode(String pinYinCode) {
        this.pinYinCode = pinYinCode;
    }

    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    public String getDirectSellUrl() {
        return directSellUrl;
    }

    public void setDirectSellUrl(String directSellUrl) {
        this.directSellUrl = directSellUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getGeneralManager() {
        return generalManager;
    }

    public void setGeneralManager(String generalManager) {
        this.generalManager = generalManager;
    }

    public String getLegalRepr() {
        return legalRepr;
    }

    public void setLegalRepr(String legalRepr) {
        this.legalRepr = legalRepr;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr;
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    public BigDecimal getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(BigDecimal regCapital) {
        this.regCapital = regCapital;
    }

    public String getServiceLine() {
        return serviceLine;
    }

    public void setServiceLine(String serviceLine) {
        this.serviceLine = serviceLine;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getIsFundCompany() {
        return isFundCompany;
    }

    public void setIsFundCompany(String isFundCompany) {
        this.isFundCompany = isFundCompany;
    }


}
