package com.sunlights.op.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/11/16.
 */
public class ProductTypeVo implements Serializable{
    private String prdTypeCode;

    private String prdTypeName;

    private List<ProductVo> productVos = new ArrayList<>();

    public String getPrdTypeCode() {
        return prdTypeCode;
    }

    public void setPrdTypeCode(String prdTypeCode) {
        this.prdTypeCode = prdTypeCode;
    }

    public String getPrdTypeName() {
        return prdTypeName;
    }

    public void setPrdTypeName(String prdTypeName) {
        this.prdTypeName = prdTypeName;
    }

    public List<ProductVo> getProductVos() {
        return productVos;
    }

    public void setProductVos(List<ProductVo> productVos) {
        this.productVos = productVos;
    }
}
