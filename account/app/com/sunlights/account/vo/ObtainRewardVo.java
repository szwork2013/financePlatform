package com.sunlights.account.vo;

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
     * 可获得的奖励
     */
    private Long obtainReward;

    /**
     * 触发点击事件是否可以获取到奖励  true表示可以  false表示不可以
     */
    private boolean isCanNotObtain;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public Long getObtainReward() {
        return obtainReward;
    }

    public void setObtainReward(Long obtainReward) {
        this.obtainReward = obtainReward;
    }

    public boolean isCanNotObtain() {
        return isCanNotObtain;
    }

    public void setCanNotObtain(boolean isCanNotObtain) {
        this.isCanNotObtain = isCanNotObtain;
    }
}
