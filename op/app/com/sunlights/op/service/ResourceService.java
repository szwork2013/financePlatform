package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.ResourceVo;
import com.sunlights.op.vo.TreeVo;
import models.Resource;

import java.util.List;

/**
 * Created by Yuan on 2015/3/5.
 */
public interface ResourceService {
	public List<ResourceVo> findResourcesBy (PageVo pageVo);

	public void create (Resource resource);

	public void update (Resource resource);

	public void delete (Resource resource);

	public TreeVo findTree ();

	public List<Resource> findResourcesByRoleId (Long roleId);

	public TreeVo findTree (Long roleId);
}
