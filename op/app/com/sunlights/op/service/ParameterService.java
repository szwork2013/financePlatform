package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.ParameterVo;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: ParameterService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface ParameterService {
    public List<ParameterVo> findParametersBy(PageVo pageVo);

    public void create(ParameterVo parameterVo);

    public void update(ParameterVo parameterVo);

    public void delete(ParameterVo parameterVo);
}
