package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: Code.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
public class Code extends IdEntity {
    @Column(name = "category")
    private String category;
    @Column(name = "code")
    private String code;
    @Column(name = "value")
    private String value;
    @Column(name = "DESC")
    private String desc;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Code code1 = (Code) o;

        if (getId() != code1.getId()) return false;
        if (category != null ? !category.equals(code1.category) : code1.category != null) return false;
        if (code != null ? !code.equals(code1.code) : code1.code != null) return false;
        if (desc != null ? !desc.equals(code1.desc) : code1.desc != null) return false;
        if (value != null ? !value.equals(code1.value) : code1.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() != null ? getId() ^ (getId() >>> 32) : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        return result;
    }
}
