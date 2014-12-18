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
    @Column(name="\"DESC\"")
    private String desc;
    @Column(name="on_sale")
    private String onSale;

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

	public String getOnSale () {
		return onSale;
	}

	public void setOnSale (String onSale) {
		this.onSale = onSale;
	}
}
