package com.sunlights.account.vo;

import models.Activity;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/11/13.
 */
public class ActivityVo  implements Serializable {

    private Long id;

    private String name;

    private String image;

    private String url;

    public ActivityVo() {

    }

    public ActivityVo(Activity activity) {
        if(activity != null) {
            this.id = activity.getId();
            this.name = activity.getTitle();
            this.image = activity.getImage();
            this.url = activity.getUrl();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
