package com.sunlights.op.vo;

import models.Dict;

import java.util.Date;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: DictVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class DictManageVo {

    private Long id;

    private String codeCat;

    private String codeKey;

    private String codeVal;

    private String createBy;

    private Date createTime;

    private String status;

    private String remarks;
    private String magic;

    private long seqNo;

    private String sysInd;

    public DictManageVo() {
        super();
    }

    public DictManageVo(Dict dict) {
        inDict(dict);
    }

    public void inDict(Dict dict) {
        this.id = dict.getId();
        this.codeCat = dict.getCodeCat();

        this.codeKey = dict.getCodeKey();

        this.codeVal = dict.getCodeVal();

        this.createBy = dict.getCreateBy();

        this.createTime = dict.getCreateTime();

        this.status = dict.getStatus();

        this.remarks = dict.getRemarks();
        this.magic = dict.getMagic();

        this.seqNo = dict.getSeqNo();

        this.sysInd = dict.getSysInd();

    }

    public Dict convertToDict() {
        Dict dict = new Dict();
        dict.setId(this.id);
        dict.setCodeCat(this.codeCat);

        dict.setCodeKey(this.codeKey);

        dict.setCodeVal(this.codeVal);

        dict.setCreateBy(this.createBy);

        dict.setCreateTime(this.createTime);

        dict.setStatus(this.status);

        dict.setRemarks(this.remarks);
        dict.setMagic(this.magic);

        dict.setSeqNo(this.seqNo);

        dict.setSysInd(this.sysInd);
        return dict;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeCat() {
        return codeCat;
    }

    public void setCodeCat(String codeCat) {
        this.codeCat = codeCat;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    public String getCodeVal() {
        return codeVal;
    }

    public void setCodeVal(String codeVal) {
        this.codeVal = codeVal;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(long seqNo) {
        this.seqNo = seqNo;
    }

    public String getSysInd() {
        return sysInd;
    }

    public void setSysInd(String sysInd) {
        this.sysInd = sysInd;
    }
}
