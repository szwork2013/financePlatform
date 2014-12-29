package com.sunlights.customer.vo;

/**
 * Created by Administrator on 2014/12/26.
 */
public class Activity4H5Vo {

    private String imageUrl;

    private String content;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "imageUrl = " + imageUrl + " content = " + content;
    }
}
