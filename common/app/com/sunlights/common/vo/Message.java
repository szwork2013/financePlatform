package com.sunlights.common.vo;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuan on 9/22/14.
 */
public class Message {
  private Severity severity = Severity.INFO;
  private String code = null;
  private String summary = null;
  private String detail = null;
  private Map<String, String> fields = new HashMap<String, String>();

  public Message() {
  }

  public Message(MsgCode msgCode, Object... params) {
    this.code = msgCode.getCode();
    this.summary = msgCode.getMessage();
    String detailMsg = msgCode.getDetail();
    if (params != null) {
      detailMsg = MessageFormat.format(msgCode.getDetail(), params);
    }
    this.detail = detailMsg;
  }

  public Message(Severity severity, MsgCode msgCode, Object... params) {
    this(msgCode, params);
    this.severity = severity;
  }

  public Message(Severity severity, String errorCode, String errorMessage, String detailMsg) {
    this.severity = severity;
    this.code = errorCode;
    this.summary = errorMessage;
    this.detail = detailMsg;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getDetail() {
    if (this.detail == null) {
      return (this.summary);
    } else {
      return (this.detail);
    }
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  public Severity currentSeverity() {
    return severity;
  }

  public int getSeverity() {
    return severity.getLevel();
  }

  public Map<String, String> getFields() {
    return fields;
  }

  public void put(String key, String value) {
    fields.put(key, value);
  }

  public void remove(String key) {
    fields.remove(key);
  }

  public void clear() {
    fields.clear();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setSeverity(Severity severity) {
    this.severity = severity;
  }

  public void setFields(Map<String, String> fields) {
    this.fields = fields;
  }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[code = ").append(code)
                .append("; summary = ").append(summary)
                .append("; detail = ").append(detail)
                .append("]");

        return sb.toString();
    }
}
