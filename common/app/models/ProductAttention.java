package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: ProductAttention.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
@Table(name = "p_product_attention")
public class ProductAttention extends IdEntity {
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "customer_id")
    private String customerId;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
