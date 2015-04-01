package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuan on 2015/3/5.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoleVo {

	private Long id;
	private String code;
	private String name;
	private String desc;
	private Date createTime;
	private Date updateTime;
	private Boolean deleted;

	private List<Long> resourceIds = new ArrayList<Long>();

	public RoleVo() {
	}

	public RoleVo(Role role) {
		inRole(role);
	}

	public void inRole(Role role) {
		this.setId(role.getId());
		this.setCode(role.getCode());
		this.setName(role.getName());
		this.setDesc(role.getDesc());
		this.setCreateTime(role.getCreateTime());
		this.setUpdateTime(role.getUpdateTime());
		this.setDeleted(role.getDeleted());
//		List<RoleResource> rrs = role.getRoleResources();
//		List<Long> resourceIds = new ArrayList<Long>();
//		for (RoleResource rr : rrs) {
//			Resource r = rr.getResource();
//			if (!rr.getDeleted() && r != null && !r.getDeleted() && !resourceIds.contains(r.getId())) {
//				resourceIds.add(r.getId());
//			}
//		}
//		this.setResourceIds(resourceIds);

	}

	public Role convertToRole() {
		Date date = new Date();
		Role role = new Role();
		role.setId(this.getId());

		role.setCode(this.getCode());
		role.setName(this.getName());
		role.setDesc(this.getDesc());
		role.setDeleted(this.getDeleted());
		if (role.getId() == null) {
			role.setCreateTime(date);
		} else {
			role.setCreateTime(this.createTime);
		}
		role.setUpdateTime(date);
		return role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<Long> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<Long> resourceIds) {
		this.resourceIds = resourceIds;
	}
}
