/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.dao;

import com.yeepay.g3.app.databank.entity.RoleEntity;

import java.util.List;

/**
 * <p>Title: 角色业务逻辑层实现类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 2013-5-5 下午15:12:24
 */
public interface RoleDao extends GenericDao<RoleEntity> {

    /**
     * 获取角色-数据源关系
     *
     * @param roleId 角色编号
     */
    List<String> getRoleDsR(Long roleId);

    /**
     * 创建角色-数据源关系
     *
     * @param roleId 角色编号
     * @param dsIds  数据源编号
     */
    void addRoleDsR(Long roleId, List<String> dsIds);

    /**
     * 删除角色-数据源关系
     *
     * @param roleId 角色编号
     * @param dsIds  数据源编号
     */
    void deleteRoleDsR(Long roleId, List<String> dsIds);

    int addAllow(Long roleId, Long dsId, List<String> allow);

    /**
     * 删除允许权限
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     * @param allow  为 null 时删除全部
     * @return
     */
    int deleteAllow(Long roleId, Long dsId, List<String> allow);

    /**
     * 获取指定角色的权限肯定
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     */
    List<String> getAllowRoleIdAndDsId(Long roleId, Long dsId);

    /**
     * 获取指定角色的权限否定
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     */
    List<String> getNotAllowRoleIdAndDsId(Long roleId, Long dsId);

    int addNotAllow(Long roleId, Long dsId, List<String> notAllow);

    /**
     * 删除否定权限
     *
     * @param roleId
     * @param dsId
     * @param notAllow 为 null 时删除全部
     * @return
     */
    int deleteNotAllow(Long roleId, Long dsId, List<String> notAllow);

    List<Long> getRoleIdsByUserId(Long userId);

    List<RoleEntity> getRoleByUserId(Long userId);

}
