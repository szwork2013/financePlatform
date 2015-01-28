package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/1/28.
 */
@Entity
@Table(name = "role_resource")
public class RoleResource extends IdEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "deleted")
	private Boolean deleted;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	@ManyToOne
	@JoinColumn(name = "resource_id", referencedColumnName = "id")
	private Resource resource;

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

	public Role getRole () {
		return role;
	}

	public void setRole (Role role) {
		this.role = role;
	}

	public Resource getResource () {
		return resource;
	}

	public void setResource (Resource resource) {
		this.resource = resource;
	}
}
