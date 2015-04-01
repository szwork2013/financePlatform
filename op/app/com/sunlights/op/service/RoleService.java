package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.RoleVo;

import java.util.List;

/**
 * Created by Yuan on 2015/3/5.
 */
public interface RoleService {

	public List<RoleVo> findRolesBy(PageVo pageVo);

	public void save(RoleVo roleVo);


}
