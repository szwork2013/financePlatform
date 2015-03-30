package com.sunlights.op.service.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.ResourceService;
import com.sunlights.op.vo.ResourceVo;
import com.sunlights.op.vo.TreeVo;
import models.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/3/5.
 */
public class ResourceServiceImpl implements ResourceService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();

	private PageService pageService = new PageService();

	@Override
	public List<ResourceVo> findResourcesBy(PageVo pageVo) {
		StringBuilder xsql = new StringBuilder();

		xsql.append(" select new com.sunlights.op.vo.ResourceVo(r) from Resource r");
		xsql.append(" where 1=1");
		xsql.append(" /~ and r.name like {name} ~/ ");
		xsql.append(" /~ and r.code like {code} ~/ ");
		xsql.append(" /~ and r.uri like {uri} ~/ ");
		xsql.append(" order by r.id");

		List<ResourceVo> resources = pageService.findXsqlBy(xsql.toString(), pageVo);
		return resources;
	}

	@Override
	public void create(Resource resource) {
		Date date = new Date();
		resource.setCreateTime(date);
		resource.setUpdateTime(date);
		resource.setDeleted(false);
		entityBaseDao.create(resource);
	}

	@Override
	public void update(Resource resource) {
		resource.setUpdateTime(new Date());
		entityBaseDao.update(resource);
	}

	@Override
	public void delete(Resource resource) {
		resource.setDeleted(true);
		resource.setUpdateTime(new Date());
		entityBaseDao.update(resource);
	}

	@Override
	public TreeVo findTree() {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select r,r.parentId,r.seqNo from Resource r");
		jpql.append(" where r.deleted = false");
		jpql.append(" order by r.parentId asc,r.seqNo asc");
		List<Resource> resources = new ArrayList<Resource>();
		List<Object[]> rows = entityBaseDao.find(jpql.toString());

		for (Object[] row : rows) {
			resources.add((Resource) row[0]);
		}

		return resources.isEmpty() ? new TreeVo() : getTreeVo(resources.get(0), resources);
	}

	@Override
	public List<Resource> findResourcesByRoleId(Long roleId) {

		if (roleId == null) {
			return Collections.EMPTY_LIST;
		}

		StringBuffer jpql = new StringBuffer();
		jpql.append(" select distinct rsr.resource,rsr.resource.parentId,rsr.resource.seqNo");
		jpql.append(" from RoleResource rsr");
		jpql.append(" where 1=1");
		jpql.append(" and rsr.deleted = false");
		jpql.append(" and rsr.resource.deleted = false");
		jpql.append(" and rsr.role.deleted = false");
		jpql.append(" and rsr.role.id = ?1");
		jpql.append(" order by rsr.resource.parentId asc,rsr.resource.seqNo asc");

		List<Resource> resources = new ArrayList<Resource>();
		List<Object[]> rows = entityBaseDao.find(jpql.toString(), roleId);

		for (Object[] row : rows) {
			resources.add((Resource) row[0]);
		}
		return resources;
	}

	@Override
	public TreeVo findTree(Long roleId) {
		List<Resource> resources = findResourcesByRoleId(roleId);
		List<Long> resourceIds = new ArrayList<Long>();
		for (Resource r : resources) {
			resourceIds.add(r.getId());
		}
		TreeVo tree = findTree();

		checkedUserTree(tree, resourceIds);
		return tree;
	}

	private TreeVo getTreeVo(Resource resource, List<Resource> resources) {
		TreeVo treeVo = new TreeVo(resource);
		for (Resource r : resources) {
			if (resource.getId() == r.getParentId()) {
				treeVo.getItems().add(getTreeVo(r, resources));
			}
		}
		return treeVo;
	}

	private void checkedUserTree(TreeVo tree, List<Long> resourceIds) {

		if (resourceIds.contains(tree.getId())) {
			tree.setChecked(true);
		}
		for (TreeVo treeVo : tree.getItems()) {
			checkedUserTree(treeVo, resourceIds);
		}

	}
}
