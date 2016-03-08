/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.yeepay.g3.app.databank.utils.Collections3;
import com.yeepay.g3.utils.common.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: 用户实体</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 2013-4-1 下午14:29:24
 */
public class UserEntity extends Entity {

    private static final long serialVersionUID = -4082457458619235442L;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 最后一次使用过的数据源编号
     */
    private Long lastDs;

    /**
     * 最后一次使用过的 Schema
     */
//	private String lastSchema;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 最后修改时间
     */
    private Date lastModifiedDate;

    /**
     * 所属用户组
     */
    private List<RoleEntity> roleList = Lists.newLinkedList();

    public UserEntity() {
    }

    public UserEntity(Long id) {
        this.setId(id);
    }

    @NotBlank
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getLastDs() {
        return lastDs;
    }

    public void setLastDs(Long lastDs) {
        this.lastDs = lastDs;
    }

//	public String getLastSchema() {
//		return lastSchema;
//	}
//
//	public void setLastSchema(String lastSchema) {
//		this.lastSchema = lastSchema;
//	}

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<RoleEntity> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleEntity> roleList) {
        this.roleList = roleList;
    }

    //  页面绑定权限时使用
    public List<String> getPermissionList() {
        return ImmutableList.copyOf(StringUtils.split(this.getPermissions(), ","));
    }

    public String getPermissions() {
        StringBuilder permissions = new StringBuilder();
        for (RoleEntity role : roleList) {
            permissions.append(",").append(role.getPermissions());
        }
        return permissions.toString();
    }

    /**
     * 用户拥有的权限组名称字符串, 多个权限组名称用','分隔.
     */
    public String getRoleNames() {
        return Collections3.extractToString(roleList, "name", ",");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
