package com.sunlights.common.utils.msg;

import play.i18n.Messages;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuan on 9/22/14.
 */
public class MessageVo {
    public int severity = 0;
    public String code = null;
    public String summary = null;
    public String detail = null;
    public Map<String, String> fields = new HashMap<String, String>();

    public MessageVo() {
        super();
    }

    public MessageVo(Message message) {
        inMessageVo(message);
    }

    private void inMessageVo(Message message) {
        this.severity = message.getSeverity().getOrdinal();
        this.code = message.getCode();
        this.summary = Messages.get(message.getSummary());
        this.detail = Messages.get(message.getDetail());
        Map<String, String> filedMap = message.getFields();
        for (String key : filedMap.keySet()) {
            fields.put(key, Messages.get(filedMap.get(key)));
        }
    }
}
