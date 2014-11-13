package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: tradingsystem</p>
 * <p>Title: SmsMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "sms_message")
public class SmsMessage extends IdEntity {
  @Column(length = 40)
  private String smsId;
  @Column(length = 11)
  private String mobile;
  @Column(length = 200)
  private String content;
  @Column(length = 40, name = "rec_status")
  private String recStatus;//回执状态
  @Column(length = 200, name = "return_msg")
  private String returnMsg;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATE_TIME")
  private Date createTime;//创建事件
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UPDATE_TIME")
  private Date updateTime;//修改时间

  public SmsMessage() {
  }

  public String getSmsId() {
    return smsId;
  }

  public void setSmsId(String smsId) {
    this.smsId = smsId;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getRecStatus() {
    return recStatus;
  }

  public void setRecStatus(String recStatus) {
    this.recStatus = recStatus;
  }

  public String getReturnMsg() {
    return returnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
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
}
