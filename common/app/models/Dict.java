package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


/**
 * The persistent class for the DICT database table.
 *
 */

/**
 * <p>Project: tih</p>
 * <p>Description: 字典表</p>
 * 例如：
 * CODE_CAT: 'TIH.CODE.ZZDW'
 * CODE_KEY: '1'
 * CODE_VAL:'集团'
 * LANG:'zh_CN'或'zh_SG'或'en_US'
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
public class Dict extends IdEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "CODE_CAT")
    private String codeCat;

    @Column(name = "CODE_KEY")
    private String codeKey;

    @Column(name = "CODE_VAL")
    private String codeVal;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "STATUS")
    private String status;

    private String remarks;
    private String magic;

    @Column(name = "SEQ_NO")
    private long seqNo;

    @Column(name = "SYS_IND")
    private String sysInd;

    public Dict() {
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

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }
}