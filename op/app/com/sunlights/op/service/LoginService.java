package com.sunlights.op.service;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.vo.Message;
import com.sunlights.op.service.impl.EmailServiceImpl;
import com.sunlights.op.vo.EmailVo;
import com.sunlights.op.vo.MenuVo;
import com.sunlights.op.vo.UserVo;
import models.P;
import models.Resource;
import models.Role;
import models.User;
import play.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuan on 2015/1/28.
 */
public class LoginService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();
	private EmailService emailService = new EmailServiceImpl();

	public static String MENU = "MENU";

	public UserVo login (UserVo userVo) {

		StringBuffer jpql = new StringBuffer();
		jpql.append(" select u from User u");
		jpql.append(" where u.deleted = false");
		jpql.append(" and u.username = ?1");

		List<User> users = entityBaseDao.find(jpql.toString(), userVo.getUsername());

		if (users.isEmpty()) {
			// 没有该用户
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_NOT_REGISTER_ERROR));
		}

		if (users.size() > 1) {
			// 该用户账号异常
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_ACCOUNT_UNUSUAL_ERROR));
		} else {
			User user = users.get(0);
			if (!"Y".equals(user.getStatus())) {
				// 该用户账号异常
				throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_ACCOUNT_UNUSUAL_ERROR));
			}
			if (!user.getPassword().equals(userVo.getPassword())) {
				// 密码错误
				throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_PASSWORD_INCORRECT_ERROR));
			}

			return userVo;
		}

	}

	public UserVo getCurrentUser (String username) {
		User user = findUserByName(username);
		if (user == null) {
			return null;
		}
		UserVo userVo = new UserVo();
		userVo.setUsername(user.getUsername());
		P p = user.getP();
		if (p != null) {
			userVo.setZhName(p.getLastName() + p.getFirstName());
		}

		// List<Role> roles = getRoles(user.getUsername());
		// Logger.info("[roles]" + roles.size());

		List<Resource> resources = getResources(user.getUsername());
		Logger.info("[resources]" + resources.size());
		List<String> permissions = new ArrayList<String>();
		for (Resource resource : resources) {
			permissions.add(resource.getUri());
		}
		userVo.setPermissions(permissions);

		if (!resources.isEmpty()) {
			MenuVo menuVo = getMenuVo(resources.get(0), resources);
			userVo.setMenuVo(menuVo);
		}
		return userVo;
	}

	public void reset (UserVo userVo) {
		List<User> users = entityBaseDao.find("select u from User u where u.username = '" + userVo.getUsername().trim() + "'");
		if (users.isEmpty()) {
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_NOT_REGISTER_ERROR));
		} else {
			User user = users.get(0);
			P p = user.getP();
			if (p == null || !p.getEmail().equals(userVo.getEmail().trim())) {
				// 用户名和邮箱不匹配
				throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.NAME_OR_ID_ERROR));
			}
			user.setPassword(userVo.getPassword().trim());
			user.setUpdateTime(new Date());
			entityBaseDao.update(user);
			EmailVo emailVo = new EmailVo();
			emailVo.setSubject("[金豆荚-管理系统]：重置密码");
			List<String> tos = new ArrayList<String>();
			tos.add(userVo.getEmail().trim());
			emailVo.setTo(tos);
			StringBuffer content = new StringBuffer();
			content.append("尊敬的[");
			content.append(userVo.getUsername());
			content.append("]用户,此次重置的密码为[");
			content.append(user.getPassword() + "]");
			emailVo.setBodyHtml(content.toString());
			emailService.sendEmail(emailVo);
		}

	}

	private User findUserByName (String username) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select u from User u");
		jpql.append(" where u.deleted = false");
		jpql.append(" and u.username = ?1");

		List<User> users = entityBaseDao.find(jpql.toString(), username);

		if (users.isEmpty()) {
			// 没有该用户
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_NOT_REGISTER_ERROR));
		}

		if (users.size() > 1) {
			// 该用户账号异常
			throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_ACCOUNT_UNUSUAL_ERROR));
		} else {
			return users.get(0);
		}

	}

	private List<Role> getRoles (String username) {
		if (username == null || username.trim().isEmpty()) {
			return Collections.EMPTY_LIST;
		}
		String jpql = "select ur.role from UserRole ur where ur.deleted = false and ur.role.deleted = false and ur.user.username = ?1";
		List<Role> roles = entityBaseDao.find(jpql, username);

		return roles;
	}

	private List<Resource> getResources (String username) {
		if (username == null || username.trim().isEmpty()) {
			return Collections.EMPTY_LIST;
		}

		StringBuffer jpql = new StringBuffer();
		jpql.append(" select distinct rsr.resource,rsr.resource.parentId,rsr.resource.seqNo");
		jpql.append(" from UserRole ur,RoleResource rsr");
		jpql.append(" where 1=1");
		jpql.append(" and ur.deleted = false");
		jpql.append(" and ur.role= rsr.role");
		jpql.append(" and ur.user.username = ?1");
		jpql.append(" and rsr.deleted = false");
		jpql.append(" and rsr.resource.deleted = false");
		jpql.append(" and rsr.role.deleted = false");
		jpql.append(" order by rsr.resource.parentId asc,rsr.resource.seqNo asc");

		List<Resource> resources = new ArrayList<Resource>();
		List<Object[]> rows = entityBaseDao.find(jpql.toString(), username);

		for (Object[] row : rows) {
			resources.add((Resource) row[0]);
		}
		return resources;
	}

	private MenuVo getMenuVo (Resource resource, List<Resource> resources) {
		MenuVo menuVo = new MenuVo(resource);
		for (Resource r : resources) {
			if (resource.getId() == r.getParentId()) {
				menuVo.getMenuVos().add(getMenuVo(r, resources));
			}
		}
		return menuVo;
	}
}
