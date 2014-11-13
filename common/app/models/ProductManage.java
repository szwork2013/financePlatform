package models;


import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: fsp</p>
 * <p>Title: ProductManage.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Entity
@Table(name = "P_PRODUCT_MANAGE")
public class ProductManage extends IdEntity {

    public ProductManage() {
    }

    public ProductManage(String productName, Timestamp beginTime, Timestamp endDate,
                         Timestamp createTime, Timestamp updateTime, String productDesc,
                         String productStatus, String url, String productCode, String productType) {
        this.productName = productName;
        this.beginTime = beginTime;
        this.endDate = endDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.productDesc = productDesc;
        this.productStatus = productStatus;
        this.url = url;
        this.productCode = productCode;
        this.productType = productType;
    }

    public ProductManage(String productName, Timestamp beginTime, Timestamp endDate,
                         String productDesc,String productStatus, String url,
                         String productCode, String productType) {
        this.productName = productName;
        this.beginTime = beginTime;
        this.endDate = endDate;
        this.productDesc = productDesc;
        this.productStatus = productStatus;
        this.url = url;
        this.productCode = productCode;
        this.productType = productType;
    }

    @Column(name = "PRODUCT_NAME" ,length=10 )
    private String productName;
    @Column(name = "BEGIN_TIME")
    private Timestamp beginTime;
    @Column(name = "END_DATE")
    private Timestamp endDate;
    @Column(name = "CREATE_TIME")
    private Timestamp createTime;
    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;
    @Column(name = "PRODUCT_DESC" ,length=100 )
    private String productDesc;
    @Column(name = "PRODUCT_STATUS" ,length=50 )
    private String productStatus;
    @Column(name = "URL" ,length=300 )
    private String url;
    @Column(name = "PRODUCT_CODE" ,length=10 )
    private String productCode;
    @Column(name = "PRODUCT_TYPE" ,length=50 )
    private String productType;

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getProductDesc() {
    return productDesc;
  }

  public void setProductDesc(String productDesc) {
    this.productDesc = productDesc;
  }

  public String getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(String productStatus) {
    this.productStatus = productStatus;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

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
}
