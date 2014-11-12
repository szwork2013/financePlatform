package models;

import com.sunlights.common.AppConst;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2014/9/15.
 */
@Entity
public class Parameter extends IdEntity {
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String value;
    private String description;
    @Column(length = 1)
    private String status = AppConst.STATUS_VALID;

   public Parameter() {
   }

  public Parameter(String name, String value, String description) {
    this.name = name;
    this.value = value;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
      return status;
  }

  public void setStatus(String status) {
      this.status = status;
  }
}
