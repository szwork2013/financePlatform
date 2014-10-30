package com.sunlights.core.models;

import com.sunlights.common.dal.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: fsp</p>
 * <p>Title: FundCommon.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "P_PRODUCT")
public class Product extends BaseEntity {
    @Column(name = "COMPANY_CODE", length = 10)
    private int companyCode;//公司代码
    @Column(name = "INNER_CODE", length = 10)
    private String innerCode;//内部编号
    @Column(name = "SECU_CODE", length = 10)
    private String secuCode;//证券代码
    @Column(name = "CHI_NAME", length = 200)
    private String chiName;//中文名称
    @Column(name = "CHI_NAME_ABBR", length = 100)
    private String chiNameAbbr;//中文名称缩写
    @Column(name = "ENG_NAME", length = 200)
    private String engName;//英文名称
    @Column(name = "ENG_NAME_ABBR", length = 50)
    private String engNameAbbr;//英文名称缩写
    @Column(name = "SECU_ABBR", length = 20)
    private String secuAbbr;//证券简称
    @Column(name = "CHI_SPELLING", length = 10)
    private String chiSpelling;//拼音证券简称
    @Column(name = "SECU_MARKET", length = 10)
    private int secuMarket;//证券市场
    @Column(name = "SECU_CATEGORY", length = 10)
    private int secuCategory;//证券类别
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LISTED_DATE")
    private Date listedDate;//上市日期
    @Column(name = "LISTED_SECTOR")
    private int listedSector;//上市板块
    @Column(name = "LISTED_STATE")
    private int listedState;//上市状态
    @Column(name = "ISIN", length = 20)
    private String ISIN;//ISIN代码
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "XGRQ")
    private Date XGRQ;//更新时间

    public int getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public String getSecuCode() {
        return secuCode;
    }

    public void setSecuCode(String secuCode) {
        this.secuCode = secuCode;
    }

    public String getChiName() {
        return chiName;
    }

    public void setChiName(String chiName) {
        this.chiName = chiName;
    }

    public String getChiNameAbbr() {
        return chiNameAbbr;
    }

    public void setChiNameAbbr(String chiNameAbbr) {
        this.chiNameAbbr = chiNameAbbr;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getEngNameAbbr() {
        return engNameAbbr;
    }

    public void setEngNameAbbr(String engNameAbbr) {
        this.engNameAbbr = engNameAbbr;
    }

    public String getSecuAbbr() {
        return secuAbbr;
    }

    public void setSecuAbbr(String secuAbbr) {
        this.secuAbbr = secuAbbr;
    }

    public String getChiSpelling() {
        return chiSpelling;
    }

    public void setChiSpelling(String chiSpelling) {
        this.chiSpelling = chiSpelling;
    }

    public int getSecuMarket() {
        return secuMarket;
    }

    public void setSecuMarket(int secuMarket) {
        this.secuMarket = secuMarket;
    }

    public int getSecuCategory() {
        return secuCategory;
    }

    public void setSecuCategory(int secuCategory) {
        this.secuCategory = secuCategory;
    }

    public Date getListedDate() {
        return listedDate;
    }

    public void setListedDate(Date listedDate) {
        this.listedDate = listedDate;
    }

    public int getListedSector() {
        return listedSector;
    }

    public void setListedSector(int listedSector) {
        this.listedSector = listedSector;
    }

    public int getListedState() {
        return listedState;
    }

    public void setListedState(int listedState) {
        this.listedState = listedState;
    }

    public String getISIN() {
        return ISIN;
    }

    public void setISIN(String ISIN) {
        this.ISIN = ISIN;
    }

    public Date getXGRQ() {
        return XGRQ;
    }

    public void setXGRQ(Date XGRQ) {
        this.XGRQ = XGRQ;
    }
}
