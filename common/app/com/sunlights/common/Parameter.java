package com.sunlights.common;

import com.sunlights.common.dal.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2014/9/15.
 */
@Entity
public class Parameter extends BaseEntity{
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String value;
    private String description;

    public Parameter() {
    }

    public Parameter(String name, String value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
