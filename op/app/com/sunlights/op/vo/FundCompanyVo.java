package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.FundCompany;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: FundCompanyVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FundCompanyVo {
    private Long id;

    private String companyName;

    private String fundCompanyId;

    private String pinYinCode;

    private String abbrName;

    private String background;

    private String contactAddr;

    private String directSellUrl;

    private String email;

    private Date establishmentDate;

    private String fax;

    private String generalManager;

    private String legalRepr;

    private String linkMan;

    private String officeAddr;

    private String regAddr;

    private BigDecimal regCapital;

    private String serviceLine;

    private String tel;

    private String webSite;

    private String zipCode;

    private String isFundCompany;

    public FundCompanyVo() {
    }

    public FundCompanyVo(FundCompany fundCompany) {
        inFundCompany(fundCompany);
    }

    public FundCompany convertToFundCompany() {
        FundCompany fundCompany = new FundCompany();
        fundCompany.setId(this.getId());
        fundCompany.setCompanyName(this.getCompanyName());
        fundCompany.setFundCompanyId(this.getFundCompanyId());
        fundCompany.setPinYinCode(this.getPinYinCode());
        fundCompany.setAbbrName(this.getAbbrName());
        fundCompany.setBackground(this.getBackground());
        fundCompany.setContactAddr(this.getContactAddr());
        fundCompany.setDirectSellUrl(this.getDirectSellUrl());
        fundCompany.setEmail(this.getEmail());
        fundCompany.setEstablishmentDate(this.getEstablishmentDate());
        fundCompany.setFax(this.getFax());
        fundCompany.setGeneralManager(this.getGeneralManager());
        fundCompany.setLegalRepr(this.getLegalRepr());
        fundCompany.setLinkMan(this.getLinkMan());
        fundCompany.setOfficeAddr(this.getOfficeAddr());
        fundCompany.setRegAddr(this.getRegAddr());
        fundCompany.setRegCapital(this.getRegCapital());
        fundCompany.setServiceLine(this.getServiceLine());
        fundCompany.setTel(this.getTel());
        fundCompany.setWebSite(this.getWebSite());
        fundCompany.setZipCode(this.getZipCode());
        fundCompany.setIsFundCompany(this.getIsFundCompany());
        return fundCompany;
    }

    public void inFundCompany(FundCompany fundCompany) {
        if (fundCompany == null) {
            return;
        }
        this.setCompanyName(fundCompany.getCompanyName());
        this.setFundCompanyId(fundCompany.getFundCompanyId());
        this.setPinYinCode(fundCompany.getPinYinCode());
        this.setAbbrName(fundCompany.getAbbrName());
        this.setBackground(fundCompany.getBackground());
        this.setContactAddr(fundCompany.getContactAddr());
        this.setDirectSellUrl(fundCompany.getDirectSellUrl());
        this.setEmail(fundCompany.getEmail());
        this.setEstablishmentDate(fundCompany.getEstablishmentDate());
        this.setFax(fundCompany.getFax());
        this.setGeneralManager(fundCompany.getGeneralManager());
        this.setLegalRepr(fundCompany.getLegalRepr());
        this.setLinkMan(fundCompany.getLinkMan());
        this.setOfficeAddr(fundCompany.getOfficeAddr());
        this.setRegAddr(fundCompany.getRegAddr());
        this.setRegCapital(fundCompany.getRegCapital());
        this.setServiceLine(fundCompany.getServiceLine());
        this.setTel(fundCompany.getTel());
        this.setWebSite(fundCompany.getWebSite());
        this.setZipCode(fundCompany.getZipCode());
        this.setIsFundCompany(fundCompany.getIsFundCompany());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
