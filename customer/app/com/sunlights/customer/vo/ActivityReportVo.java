package com.sunlights.customer.vo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by tangweiqun on 2015/3/30.
 */
public class ActivityReportVo {

    private List<String> registerList = Lists.newArrayList();

    private List<String> rewardList = Lists.newArrayList();

    public List<String> getRegisterList() {
        return registerList;
    }

    public void setRegisterList(List<String> registerList) {
        this.registerList = registerList;
    }

    public List<String> getRewardList() {
        return rewardList;
    }

    public void setRewardList(List<String> rewardList) {
        this.rewardList = rewardList;
    }

    public void addRegisterStr(String desc) {
        registerList.add(desc);
    }

    public void addRewardStr(String desc) {
        rewardList.add(desc);
    }
}
