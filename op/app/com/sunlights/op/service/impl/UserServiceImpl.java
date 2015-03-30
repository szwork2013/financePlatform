package com.sunlights.op.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.UserService;
import com.sunlights.op.vo.UserVo;
import models.Role;
import models.User;
import models.UserRole;
import play.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/3/6.
 */
public class UserServiceImpl implements UserService {
	private EntityBaseDao entityBaseDao = new EntityBaseDao();

	private PageService pageService = new PageService();

	@Override
	public List<UserVo> findUsersBy(PageVo pageVo) {
		StringBuilder xsql = new StringBuilder();

		xsql.append(" select new com.sunlights.op.vo.UserVo(u) from User u");
		xsql.append(" where 1=1");
		xsql.append(" /~ and u.username like {username} ~/ ");
		xsql.append(" /~ and u.status = {status} ~/ ");
		xsql.append(" /~ and u.deleted = {deleted} ~/ ");
		xsql.append(" /~ and u.p.email like {email} ~/ ");
		xsql.append(" /~ and u.p.telephone like {telephone} ~/ ");
		xsql.append(" order by u.createTime desc");

		List<UserVo> userVos = pageService.findXsqlBy(xsql.toString(), pageVo);
		return userVos;
	}

	@Override
	public void save(UserVo userVo) {

		hasUser(userVo);
		User user = userVo.convertToUser();

		List<Long> roleIds = userVo.getRoleIds();

		deleteUserRoles(userVo.getId());
		if (roleIds != null && !roleIds.isEmpty()) {

			List<UserRole> userRoles = new ArrayList<UserRole>();
			for (Long roleId : roleIds) {
				userRoles.add(findUserRoleByRoleId(user, roleId));
			}
			user.setUserRoles(userRoles);
		}

		entityBaseDao.update(user);

	}

	private boolean hasUser(UserVo userVo) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select u from User u");
		jpql.append(" where u.username = '" + userVo.getUsername().trim() + "'");
		if (userVo.getId() != null) {
			jpql.append(" and u.id <> ").append(userVo.getId());
		}
		List<User> users = entityBaseDao.find(jpql.toString());
		if(!users.isEmpty()) {
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.USER_NAME_EXIST_ERROR));
		}

		jpql = new StringBuffer();
		jpql.append(" select u from User u");
		jpql.append(" where u.p.email = '" + userVo.getEmail().trim() + "'");
		if (userVo.getId() != null) {
			jpql.append(" and u.id <> ").append(userVo.getId());
		}
		users = entityBaseDao.find(jpql.toString());
		if(!users.isEmpty()) {
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.USER_EMAIL_EXIST_ERROR));
		}
		return false;
	}

	private int deleteUserRoles(Long userId) {
		if (userId == null)
			return 0;
		StringBuffer jpql = new StringBuffer();
		jpql.append(" update UserRole ur set ur.deleted = true");
		jpql.append(" where ur.user.id = ?1");
		jpql.append(" and ur.deleted = false");
		int i = entityBaseDao.batchExecute(jpql.toString(), userId);
		Logger.info("updated UserRole size is " + i);
		return i;
	}

	private UserRole findUserRoleByRoleId(User user, Long roleId) {
		Date date = new Date();
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		Role role = entityBaseDao.find(Role.class, roleId);
		userRole.setRole(role);
		userRole.setDeleted(false);
		userRole.setCreateTime(date);
		userRole.setUpdateTime(date);

		if (user.getId() == null) {
			return userRole;
		}

		StringBuffer jpql = new StringBuffer();
		jpql.append(" select ur from UserRole ur");
		jpql.append(" where ur.user.id = ?1");
		jpql.append(" and ur.role.id = ?2");
		List<UserRole> userRoles = entityBaseDao.find(jpql.toString(), user.getId(), roleId);
		if (!userRoles.isEmpty()) {
			userRole = userRoles.get(0);
			userRole.setDeleted(false);
			userRole.setUpdateTime(date);
		}
		return userRole;
	}

	private List<UserRole> findUserRoles(User user) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select ur from UserRole ur");
		jpql.append(" where ur.user.id = ?1");
		List<UserRole> userRoles = entityBaseDao.find(jpql.toString(), user.getId());
		return userRoles;
	}
}
