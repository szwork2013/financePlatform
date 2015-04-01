package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.P;
import models.Role;
import models.User;
import models.UserRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: UserVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserVo {
	private Long id;
	private String username;
	private String password;
	private String passwordConfirm;
	private String status;
	private Boolean deleted;
	private Date createTime;
	private Date updateTime;

	private String zhName;

	private Long pid;
	private String lastName;
	private String firstName;
	private String email;
	private String telephone;

	private MenuVo menuVo;

	private List<Long> roleIds = new ArrayList<Long>();

	public UserVo() {
		super();
	}

	public UserVo(User user) {
		inUser(user);
	}

	public void inUser(User user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setStatus(user.getStatus());
		this.setDeleted(user.getDeleted());
		this.setCreateTime(user.getCreateTime());
		this.setUpdateTime(user.getUpdateTime());

		P p = user.getP();

		if (p != null) {
			this.setPid(p.getId());
			this.setLastName(p.getLastName());
			this.setFirstName(p.getFirstName());
			this.setEmail(p.getEmail());
			this.setTelephone(p.getTelephone());
			this.setDeleted(p.getDeleted());
			this.setZhName((this.lastName == null ? "" : this.lastName) + (this.firstName == null ? "" : this.firstName));
		}

		List<UserRole> userRoles = user.getUserRoles();
		if (!userRoles.isEmpty()) {
			List<Long> roleIds = new ArrayList<Long>();
			for (UserRole ur : userRoles) {
				Role role = ur.getRole();
				if (role != null && !ur.getDeleted() && !role.getDeleted() && !roleIds.contains(role.getId())) {
					roleIds.add(role.getId());
				}
			}
			this.setRoleIds(roleIds);
		}

	}

	public User convertToUser() {
		Date date = new Date();
		User user = new User();
		user.setId(this.getId());
		user.setUsername(this.getUsername());
		user.setPassword(this.getPassword());
		user.setStatus(this.getStatus());
		user.setDeleted(this.getDeleted());
		if (this.getId() == null) {
			user.setCreateTime(date);
		} else {
			user.setCreateTime(this.getCreateTime());
		}
		user.setUpdateTime(date);

		P p = new P();
		p.setId(this.getPid());
		p.setLastName(this.getLastName());
		p.setFirstName(this.getFirstName());
		p.setEmail(this.getEmail());
		p.setTelephone(this.getTelephone());
		p.setDeleted(this.getDeleted());

		if (this.getPid() == null) {
			p.setCreateTime(date);
		} else {
			p.setCreateTime(this.getCreateTime());
		}
		p.setUpdateTime(date);
		p.setUser(user);
		user.setP(p);
		return user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public MenuVo getMenuVo() {
		return menuVo;
	}

	public void setMenuVo(MenuVo menuVo) {
		this.menuVo = menuVo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public String getPasswordConfirm () {
		return passwordConfirm;
	}

	public void setPasswordConfirm (String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
