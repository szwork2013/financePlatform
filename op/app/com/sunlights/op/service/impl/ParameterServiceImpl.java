package com.sunlights.op.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.ParameterService;
import com.sunlights.op.vo.ParameterVo;
import models.Parameter;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: ParameterServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ParameterServiceImpl implements ParameterService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();

    private PageService pageService = new PageService();
    private com.sunlights.common.service.ParameterService parameterService = new com.sunlights.common.service.ParameterService();

    @Override
    public List<ParameterVo> findParametersBy(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.op.vo.ParameterVo(p) from Parameter p");
        xsql.append(" where 1=1");
        xsql.append(" /~ and p.name like {name} ~/ ");
        xsql.append(" /~ and p.value = {value} ~/ ");
        xsql.append(" /~ and p.description = {description} ~/ ");
        xsql.append(" /~ and p.status like {status} ~/ ");
        List<ParameterVo> parameterVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        return parameterVos;
    }

    @Override
    public void create(ParameterVo parameterVo) {
        Parameter parameter = parameterVo.convertToParameter();
        parameter.setStatus(AppConst.STATUS_VALID);
		entityBaseDao.create(parameter);

        parameterService.refresh();
    }

    @Override
    public void update(ParameterVo parameterVo) {
        Parameter parameter = parameterVo.convertToParameter();
		entityBaseDao.update(parameter);

        parameterService.refresh();
    }

    @Override
    public void delete(ParameterVo parameterVo) {
        Parameter parameter = entityBaseDao.find(Parameter.class, parameterVo.getId());
		entityBaseDao.delete(parameter);

        parameterService.refresh();
    }
}
