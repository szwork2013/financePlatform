package com.sunlights.core.dal;

import com.sunlights.core.models.Parameter;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: ParameterDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface ParameterDao {
    public List<Parameter> loadAllParameter();
    public Parameter addParameter(Parameter parameter);
    public Parameter updateParameter(Parameter parameter);
}
