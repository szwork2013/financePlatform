package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.Code;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: CodeVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodeVo {

    private String id;
    private String category;
    private String code;
    private String value;
    private String desc;
    private Integer onSale;

    public CodeVo() {
        super();
    }

    public CodeVo(Code c) {
        inCodeVo(c);
    }

    public void inCodeVo(Code c) {
        this.setCategory(c.getCategory());
        this.setCode(c.getCode());
        this.setValue(c.getValue());
        this.setDesc(c.getDesc());
		this.setOnSale(c.getOnSale());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

	public Integer getOnSale () {
		return onSale;
	}

	public void setOnSale (Integer onSale) {
		this.onSale = onSale;
	}
}
