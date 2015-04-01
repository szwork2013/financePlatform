package com.sunlights.op.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.RoleService;
import com.sunlights.op.vo.RoleVo;
import models.Resource;
import models.Role;
import models.RoleResource;
import play.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuan on 2015/3/5.
 */
public class RoleServiceImpl implements RoleService {
	private  EntityBaseDao entityBaseDao = new EntityBaseDao();

	private PageService pageService = new PageService();

	@Override
	public List<RoleVo> findRolesBy(PageVo pageVo) {
		StringBuilder xsql = new StringBuilder();

		xsql.append(" select new com.sunlights.op.vo.RoleVo(r) from Role r");
		xsql.append(" where 1=1");
		xsql.append(" /~ and r.name like {name} ~/ ");
		xsql.append(" /~ and r.code like {code} ~/ ");
		xsql.append(" order by r.createTime desc");

		List<RoleVo> roleVos = pageService.findXsqlBy(xsql.toString(), pageVo);
		return roleVos;
	}

	@Override
	public void save(RoleVo roleVo) {
		if (hasRole(roleVo)) {
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.ROLE_CODE_EXIST_ERROR));
		}
		Role role = roleVo.convertToRole();

		List<Long> resourceIds = roleVo.getResourceIds();

		deleteRoleResourcs(roleVo.getId());
		if (resourceIds != null && !resourceIds.isEmpty()) {

			List<RoleResource> roleResources = new ArrayList<RoleResource>();
			for (Long roleId : resourceIds) {
				roleResources.add(findRoleResourceByResourceId(role, roleId));
			}
			role.setRoleResources(roleResources);
		}
		entityBaseDao.update(role);
	}

	private boolean hasRole(RoleVo roleVo) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select r from Role r");
		jpql.append(" where r.code = '" + roleVo.getCode().trim() + "'");
		if (roleVo.getId() != null) {
			jpql.append(" and r.id <> ").append(roleVo.getId());
		}
		List<Role> roles = entityBaseDao.find(jpql.toString());
		return !roles.isEmpty();
	}


	private int deleteRoleResourcs(Long roleId) {
		if (roleId == null)
			return 0;
		StringBuffer jpql = new StringBuffer();
		jpql.append(" update RoleResource rr set rr.deleted = true");
		jpql.append(" where rr.role.id = ?1");
		jpql.append(" and rr.deleted = false");
		int i = entityBaseDao.batchExecute(jpql.toString(), roleId);
		Logger.info("updated RoleResource size is " + i);
		return i;
	}


	private RoleResource findRoleResourceByResourceId(Role role, Long resourceId) {
		Date date = new Date();
		RoleResource roleResource = new RoleResource();
		roleResource.setRole(role);
		Resource resource = entityBaseDao.find(Resource.class, resourceId);
		roleResource.setResource(resource);
		roleResource.setDeleted(false);
		roleResource.setCreateTime(date);
		roleResource.setUpdateTime(date);

		if (role.getId() == null) {
			return roleResource;
		}

		StringBuffer jpql = new StringBuffer();
		jpql.append(" select rr from RoleResource rr");
		jpql.append(" where rr.role.id = ?1");
		jpql.append(" and rr.resource.id = ?2");
		List<RoleResource> roleResources = entityBaseDao.find(jpql.toString(), role.getId(), resourceId);
		if (!roleResources.isEmpty()) {
			roleResource = roleResources.get(0);
			roleResource.setDeleted(false);
			roleResource.setUpdateTime(date);
		}
		return roleResource;
	}
}
