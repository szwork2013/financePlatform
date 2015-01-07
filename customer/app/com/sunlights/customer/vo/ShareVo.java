package com.sunlights.customer.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/12/3.
 */
public class ShareVo implements Serializable {

    private String title;

    private String shorturl;

    private String content;

    private String imageurl;

    private String type;

    private String id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShareVo)) return false;

        ShareVo shareVo = (ShareVo) o;

        if (content != null ? !content.equals(shareVo.content) : shareVo.content != null) return false;
        if (id != null ? !id.equals(shareVo.id) : shareVo.id != null) return false;
        if (imageurl != null ? !imageurl.equals(shareVo.imageurl) : shareVo.imageurl != null) return false;
        if (shorturl != null ? !shorturl.equals(shareVo.shorturl) : shareVo.shorturl != null) return false;
        if (title != null ? !title.equals(shareVo.title) : shareVo.title != null) return false;
        if (type != null ? !type.equals(shareVo.type) : shareVo.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (shorturl != null ? shorturl.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (imageurl != null ? imageurl.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
