package com.sunlights.core.dal.impl;

import com.sunlights.common.db.EntityBaseDao;
import com.sunlights.common.util.IAppConst;
import com.sunlights.core.dal.ParameterDao;
import com.sunlights.core.models.Parameter;
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

@Service
public class ParameterDaoImpl extends EntityBaseDao implements ParameterDao {

    public List<Parameter> loadAllParameter(){
        List<Parameter> list = findBy(Parameter.class, IAppConst.DEFUNCT_IND, IAppConst.IND_NO);
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
