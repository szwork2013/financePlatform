package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <p>Project: fsp</p>
 * <p>Title: CodeMstr.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "CODE_MSTR")
public class CodeMstr extends IdEntity {
  @Column(name = "CODE_CAT", length = 50)
  private String codeCat;
  @Column(name = "CODE_ABBR", length = 50)
  private String codeAbbr;
  @Column(name = "CODE_VAL", length = 100)
  private String codeVal;
  @Column(name = "PARENT_CODE_CAT")
  private String parentCodeCat;
  @Column(name = "CODE_SEQ")
  private int codeSeq;
  @Column(name = "STATUS", length = 1)
  private String status;
  @Column(name = "REMARKS")
  private String remarks;
  @Column(name = "MAGIC")
  private String magic;
  @Column(name = "create_by")
  private String createBy;
  @Column(name = "create_time")
  private Timestamp createTime;

  public String getCodeCat() {
    return codeCat;
  }

  public void setCodeCat(String codeCat) {
    this.codeCat = codeCat;
  }

  public String getCodeAbbr() {
    return codeAbbr;
  }

  public void setCodeAbbr(String codeAbbr) {
    this.codeAbbr = codeAbbr;
  }

  public int getCodeSeq() {
    return codeSeq;
  }

  public void setCodeSeq(int codeSeq) {
    this.codeSeq = codeSeq;
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

  public String getParentCodeCat() {
    return parentCodeCat;
  }

  public void setParentCodeCat(String parentCodeCat) {
    this.parentCodeCat = parentCodeCat;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

    public String getCodeVal() {
        return codeVal;
    }

    public void setCodeVal(String codeVal) {
        this.codeVal = codeVal;
    }
}
