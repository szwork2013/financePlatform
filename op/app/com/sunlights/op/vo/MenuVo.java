package com.sunlights.op.vo;

import models.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/28.
 */
public class MenuVo {
	private Long id;
	private String name;
	private String uri;
	private List<MenuVo> menuVos = new ArrayList<MenuVo>();

	public MenuVo() {
	}

	public MenuVo(Resource resource) {
		this.id = resource.getId();
		this.name = resource.getName();
		this.uri = resource.getUri();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<MenuVo> getMenuVos() {
		return menuVos;
	}

	public void setMenuVos(List<MenuVo> menuVos) {
		this.menuVos = menuVos;
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}
}
