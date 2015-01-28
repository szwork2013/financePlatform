package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by Administrator on 2015/1/28.
 */
@Entity
public class Resource extends IdEntity {

	@javax.persistence.Column(name = "name")
	private String name;

	@javax.persistence.Column(name = "code")
	private String code;

	@javax.persistence.Column(name = "seq_no")
	private String seqNo;

	@javax.persistence.Column(name = "parent_id")
	private Long parentId;

	@javax.persistence.Column(name = "type")
	private String type;

	@javax.persistence.Column(name = "uri")
	private String uri;

	@Temporal(TemporalType.TIMESTAMP)
	@javax.persistence.Column(name = "create_time")
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@javax.persistence.Column(name = "update_time")
	private Date updateTime;

	@javax.persistence.Column(name = "deleted")
	private Boolean deleted;

	@OneToMany(mappedBy="resource")
	private List<RoleResource> roleResources;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
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

	public List<RoleResource> getRoleResources () {
		return roleResources;
	}

	public void setRoleResources (List<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}
}
