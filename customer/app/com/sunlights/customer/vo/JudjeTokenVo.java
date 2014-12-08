package com.sunlights.customer.vo;

/**
 * Created by Administrator on 2014/12/6.
 */
public class JudjeTokenVo {

    private String token;

    private boolean existToken;

    public boolean isExistToken() {
        return existToken;
    }

    public void setExistToken(boolean existToken) {
        this.existToken = existToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
