package com.sunlights.core.models.vo;

import com.sunlights.common.util.DateUtils;
import com.sunlights.core.models.OpenAccountPact;

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
            this.updatedAt = DateUtils.dateToString(new Date(openAccountPact.getUpdateDate().getTime()), DateUtils.PATTEN_DATE_FORMAT_DATETIME);
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
}
