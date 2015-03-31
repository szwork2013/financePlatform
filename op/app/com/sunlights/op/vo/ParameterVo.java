package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.Parameter;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: ParameterVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParameterVo {
    private Long id;
    private String name;
    private String value;
    private String description;
    private String status;

    public ParameterVo() {
        super();
    }

    public ParameterVo(Parameter parameter) {
        inParameter(parameter);
    }

    public void inParameter(Parameter parameter) {
        this.id = parameter.getId();
        this.name = parameter.getName();
        this.value = parameter.getValue();
        this.description = parameter.getDescription();
        this.status = parameter.getStatus();
    }

    public Parameter convertToParameter() {
        Parameter parameter = new Parameter();
        parameter.setId(this.id);
        parameter.setName(this.name);
        parameter.setValue(this.value);
        parameter.setDescription(this.description);
        parameter.setStatus(this.status);
        return parameter;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
