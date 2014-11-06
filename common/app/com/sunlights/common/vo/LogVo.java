package com.sunlights.common.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuan on 9/22/14.
 */
public class LogVo {
  private static final String TYPE_ACTIVITY_NAME = "ACTIVITY";
  public static final LogType TYPE_ACTIVITY = new LogType(TYPE_ACTIVITY_NAME);

  private static final String TYPE_SECURITY_NAME = "SECURITY";
  public static final LogType TYPE_SECURITY = new LogType(TYPE_SECURITY_NAME);

  private LogType type = LogVo.TYPE_ACTIVITY;
  private String userId;
  private String deviceNo;

  private Map<String, String> properties = new HashMap<String, String>();

  public LogVo(LogType type, String userId, String deviceNo) {
    this.type = type;
    this.userId = userId;
    this.deviceNo = deviceNo;
  }

  public void put(String key, String value) {
    properties.put(key, value);
  }

  public void remove(String key) {
    properties.remove(key);
  }

  public void clear() {
    properties.clear();
  }

  public LogType getType() {
    return type;
  }

  public void setType(LogType type) {
    this.type = type;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }

  @Override
  public String toString() {
    return this.type.logTypeName;
  }

  public static class LogType implements Comparable {
    private String logTypeName = null;

    private LogType(String logTypeName) {
      this.logTypeName = logTypeName;
    }


    private final int ordinal = nextOrdinal++;


    public int compareTo(Object other) {
      return this.ordinal - ((LogType) other).ordinal;
    }

    public int getOrdinal() {
      return (this.ordinal);
    }

    public String getLogTypeName() {
      return this.logTypeName;
    }

    public String toString() {
      if (null == logTypeName) {
        return (String.valueOf(this.ordinal));
      }
      return (String.valueOf(this.logTypeName) + ' ' + this.ordinal);
    }

    private static int nextOrdinal = 0;

  }
}
