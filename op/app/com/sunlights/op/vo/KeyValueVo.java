package com.sunlights.op.vo;

/**
 * Created by Administrator on 2014/11/16.
 */
public class KeyValueVo {
    private String key;

    private Long keys;

    private String value;



    public KeyValueVo (String key, String value) {

        this.key = key;
        this.value = value;
    }
    public KeyValueVo(Long keys,String value){
        this.keys = keys;
        this.value = value;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getKeys() {
        return keys;
    }

    public void setKeys(Long keys) {
        this.keys = keys;
    }
}
