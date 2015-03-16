package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/1/28.
 */
@Entity
@Table(name = "o_role")
public class Role extends IdEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "\"DESC\"")
    private String desc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "deleted")
    private Boolean deleted;


    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<RoleResource> roleResources;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;

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

    public List<RoleResource> getRoleResources() {
        return roleResources;
    }

    public void setRoleResources(List<RoleResource> roleResources) {
        this.roleResources = roleResources;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
