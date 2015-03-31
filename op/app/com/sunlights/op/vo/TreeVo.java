package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuan on 2015/3/9.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TreeVo {
	private Long id;
	private String name;
	private String uri;
	private String code;
	private String seqNo;
	private Long parentId;
	private String type;


	private boolean checked;
	private boolean folded = true;
	private boolean intermediate;


	private List<TreeVo> items = new ArrayList<TreeVo>();

	public TreeVo() {
	}

	public TreeVo(Resource resource) {
		inResource(resource);
	}

	public void inResource (Resource resource) {
		this.setId(resource.getId());
		this.setName(resource.getName());
		this.setCode(resource.getCode());
		this.setSeqNo(resource.getSeqNo());
		this.setParentId(resource.getParentId());
		this.setType(resource.getType());
		this.setUri(resource.getUri());
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getUri () {
		return uri;
	}

	public void setUri (String uri) {
		this.uri = uri;
	}

	public List<TreeVo> getItems () {
		return items;
	}

	public void setItems (List<TreeVo> items) {
		this.items = items;
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
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

	public boolean isChecked () {
		return checked;
	}

	public void setChecked (boolean checked) {
		this.checked = checked;
	}

	public boolean isFolded () {
		return folded;
	}

	public void setFolded (boolean folded) {
		this.folded = folded;
	}

	public boolean isIntermediate () {
		return intermediate;
	}

	public void setIntermediate (boolean intermediate) {
		this.intermediate = intermediate;
	}
}
