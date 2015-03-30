package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.DictManageVo;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: DictService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface DictService {
    public List<DictManageVo> findDictsBy(PageVo pageVo);

    public void create(DictManageVo dictManageVo);

    public void update(DictManageVo dictManageVo);

    public void delete(DictManageVo dictManageVo);
}
