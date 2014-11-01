package com.sunlights.common.dal.impl;


import com.sunlights.common.AppConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.ParameterDao;
import com.sunlights.common.models.Parameter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: ParameterDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

public class ParameterDaoImpl extends EntityBaseDao implements ParameterDao {

    public List<Parameter> loadAllParameter(){
        List<Parameter> list = findBy(Parameter.class, AppConst.DELETE_FLAG, AppConst.VERIFY_CODE_STATUS_INVALID);
        return list;
    }

    @Override
    public Parameter addParameter(Parameter parameter) {
        return create(parameter);
    }

    @Override
    public Parameter updateParameter(Parameter parameter) {
        return update(parameter);
    }

}
