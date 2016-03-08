/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.entity;

import com.google.common.collect.ImmutableList;
import com.yeepay.g3.app.databank.utils.Collections3;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * <p>Title: 用户组实体</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 2013-4-1 下午14:29:24
 */
public class RoleEntity extends Entity {

    private static final long serialVersionUID = -4082457458619235443L;

    /**
     * 组名或者用户名
     */
    private String name;

    /**
     * 权限
     */
    private String permissions;

    /**
     * 用户Id
     */
    private Long userId;


    public RoleEntity() {
    }

    public RoleEntity(Long id) {
        super.setId(id);
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取权限列表
     *
     * @return 权限列表
     */
    public List<String> getPermissionList() {
        if (null == permissions) return null;
        return ImmutableList.copyOf(StringUtils.split(permissions, ","));
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissions = Collections3.convertToString(permissionList, ",");
    }

    @Override
    public String toString() {
        if (null == super.getId()) {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        } else {
            return super.getId().toString();
        }
    }

}
