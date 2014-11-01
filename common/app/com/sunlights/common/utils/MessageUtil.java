package com.sunlights.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageVo;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by yuan on 9/22/14.
 */
public class MessageUtil {

    private Map<String, Object> messageMap = new HashMap<String, Object>();

    private MessageUtil() {
        super();
    }

    private static ThreadLocal<MessageUtil> instance = new ThreadLocal<MessageUtil>() {
        protected MessageUtil initialValue() {
            return (new MessageUtil());
        }
    };

    public static MessageUtil getInstance() {
        return instance.get();
    }

    public void addMessage(Message message) {
        messageMap.put("message", new MessageVo(message));
        messageMap.put("value", null);
    }

    public void addMessage(Message message, Object value) {
        messageMap.put("message", new MessageVo(message));
        messageMap.put("value", value);
    }

    public void clear() {
        messageMap.clear();
    }

    public JsonNode toJson() {
        return Json.toJson(messageMap);
    }

    public JsonNode msgToJson(Message message, Object value) {
        messageMap.put("message", new MessageVo(message));
        messageMap.put("value", value);
        return Json.toJson(messageMap);
    }

    public JsonNode msgToJson(Message message) {
        messageMap.put("message", new MessageVo(message));
        messageMap.put("value", null);
        return Json.toJson(messageMap);
    }

}
