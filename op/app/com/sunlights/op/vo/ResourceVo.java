package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.Resource;

import java.util.Date;

/**
 * Created by Yuan on 2015/3/5.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResourceVo {
	private String name;
	private String code;
	private String seqNo;
	private Long parentId;
	private String type;
	private String uri;
	private Date createTime;
	private Date updateTime;
	private Boolean deleted;

	public ResourceVo () {

	}
	public ResourceVo (Resource resource) {
		inResource(resource);
	}

	public void inResource (Resource resource) {

		this.setName(resource.getName());
		this.setCode(resource.getCode());
		this.setSeqNo(resource.getSeqNo());
		this.setParentId(resource.getParentId());
		this.setType(resource.getType());
		this.setUri(resource.getUri());
		this.setCreateTime(resource.getCreateTime());
		this.setUpdateTime(resource.getUpdateTime());
		this.setDeleted(resource.getDeleted());
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getCode () {
		return code;
	}

	public void setCode (String code) {
		this.code = code;
	}

	public String getSeqNo () {
		return seqNo;
	}

	public void setSeqNo (String seqNo) {
		this.seqNo = seqNo;
	}

	public Long getParentId () {
		return parentId;
	}

	public void setParentId (Long parentId) {
		this.parentId = parentId;
	}

	public String getType () {
		return type;
	}

	public void setType (String type) {
		this.type = type;
	}

	public String getUri () {
		return uri;
	}

	public void setUri (String uri) {
		this.uri = uri;
	}

	public Date getCreateTime () {
		return createTime;
	}

	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime () {
		return updateTime;
	}

	public void setUpdateTime (Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getDeleted () {
		return deleted;
	}

	public void setDeleted (Boolean deleted) {
		this.deleted = deleted;
	}
}
