package com.sunlights.common.vo;

import com.fasterxml.jackson.databind.JsonNode;
import play.i18n.Messages;
import play.libs.Json;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuan on 9/22/14.
 */
public class MessageVo<V> {

  private Message message;
  private V value;

  public MessageVo() {
  }

  public MessageVo(Message message) {
    this.message = message;
  }

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }

  public void setValue(V value) {
    this.value = value;
  }

  public V getValue() {
    return this.value;
  }

  public JsonNode toJson() {
    return Json.toJson(this);
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageVo)) return false;

        MessageVo messageVo = (MessageVo) o;

        if (message != null ? !message.equals(messageVo.message) : messageVo.message != null) return false;
        if (value != null ? !value.equals(messageVo.value) : messageVo.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
