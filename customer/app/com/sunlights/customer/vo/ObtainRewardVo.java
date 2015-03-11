package com.sunlights.customer.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by tangweiqu on 2014/11/19.
 */

public class ObtainRewardVo implements Serializable {

    /**
     * 获取奖励的场景
     */
    private String scene;

    /**
     * 状态， N表示开放   F表示关闭
     */
    private String status;

    /**
     * 客户在这个活动已经获取的金豆数
     */
    private Long alreadyGet;

    /**
     * 客户在这个活动还未领取的金豆数
     */
    private Long notGet;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAlreadyGet() {
        return alreadyGet;
    }

    public void setAlreadyGet(Long alreadyGet) {
        this.alreadyGet = alreadyGet;
    }

    public Long getNotGet() {
        return notGet;
    }

    public void setNotGet(Long notGet) {
        this.notGet = notGet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObtainRewardVo)) return false;

        ObtainRewardVo that = (ObtainRewardVo) o;

        if (alreadyGet != null ? !alreadyGet.equals(that.alreadyGet) : that.alreadyGet != null) return false;
        if (notGet != null ? !notGet.equals(that.notGet) : that.notGet != null) return false;
        if (scene != null ? !scene.equals(that.scene) : that.scene != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = scene != null ? scene.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (alreadyGet != null ? alreadyGet.hashCode() : 0);
        result = 31 * result + (notGet != null ? notGet.hashCode() : 0);
        return result;
    }
}
