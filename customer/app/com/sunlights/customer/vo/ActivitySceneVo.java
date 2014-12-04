package com.sunlights.customer.vo;

import models.ActivityScene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/12/2.
 */
public class ActivitySceneVo extends ActivityScene {

    private List<String> prdTypes = new ArrayList<String>();

    private List<String> prdCodes = new ArrayList<String>();

    public List<String> getPrdTypes() {
        return prdTypes;
    }

    public void setPrdTypes(List<String> prdTypes) {
        this.prdTypes = prdTypes;
    }

    public List<String> getPrdCodes() {
        return prdCodes;
    }

    public void setPrdCodes(List<String> prdCodes) {
        this.prdCodes = prdCodes;
    }
}
