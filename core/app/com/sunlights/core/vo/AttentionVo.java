package com.sunlights.core.vo;

import models.ProductAttention;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: AttentionVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class AttentionVo {
    private Long id;
    private String code;
    private String productType;
    private String customerId;

    private List<String> codes = new ArrayList<String>();

    public AttentionVo() {
        super();
    }

    public AttentionVo(ProductAttention attention) {
        inAttention(attention);
    }

    public void inAttention(ProductAttention attention) {
        this.setCode(attention.getProductCode());
        this.setProductType(attention.getProductType());
        this.setCustomerId(attention.getCustomerId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }
}
