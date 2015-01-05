package com.sunlights.core.vo;

import com.sunlights.common.utils.CommonUtil;
import models.OpenAccountPact;

import java.util.Date;


/**
 * Created by yuan on 10/8/14.
 */
public class AgreementVo {
  // 协议名称
  private String title;
  // 链接
  private String link;
  // 更新时间
  private String updatedAt;
  // 协议编号
  private String code;

  public AgreementVo() {
    super();
  }

  public AgreementVo(OpenAccountPact openAccountPact) {
    inOpenAccountPact(openAccountPact);
  }

  private void inOpenAccountPact(OpenAccountPact openAccountPact) {
    this.code = openAccountPact.getAgreementNo();
    this.title = openAccountPact.getAgreementName();
    this.link = openAccountPact.getFilePath() + openAccountPact.getFileName();
    if (openAccountPact.getUpdateDate() != null) {
      this.updatedAt = CommonUtil.dateToString(new Date(openAccountPact.getUpdateDate().getTime()), CommonUtil.DATE_FORMAT_LONG);
    }
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgreementVo)) return false;

        AgreementVo that = (AgreementVo) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
