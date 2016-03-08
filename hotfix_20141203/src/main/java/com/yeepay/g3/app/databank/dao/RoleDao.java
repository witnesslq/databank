/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.dao;

import com.yeepay.g3.app.databank.entity.RoleEntity;
import com.yeepay.g3.utils.management.core.annotation.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
     * @param Id 角色编号
     */
    List<String> getRoleDsR(Long Id);

    /**
     * 获取某一角色下的所有数据源
     *
     * @param roleId
     * @return
     */
    List<String> getDsByRoleId(Long roleId);


    /**
     * @param roleId
     * @param dsId
     * @return
     */
    List<String> getDsByRoleIdAndDsId(Long roleId, Long dsId,Date endDateTime);


    /**
     * 获取数据源关系
     *
     * @param roleId 角色编号
     */
    List<String> getApplyRole(Long dsId, Long roleId);

    /**
     * 创建角色-数据源关系
     *
     * @param roleId 角色编号
     * @param dsIds  数据源编号
     */
    void addRoleDsR(@Param("roleId") Long roleId, @Param("dsIds") List<String> dsIds, @Param("endDate") Date endDate);

    /**
     * 删除角色-数据源关系
     *
     * @param roleId 角色编号
     * @param dsIds  数据源编号
     */
    void deleteRoleDsR(Long roleId, List<String> dsIds);


    /**
     * 删除角色－数据源关系 （TIME）
     *
     * @param roleId
     * @param dsIds
     */
    void deleteRoleDsRAndTime(Long roleId, List<String> dsIds);


    /**
     * 添加允许的表
     *
     * @param roleId
     * @param dsId
     * @param allow
     * @param endDateTime
     */
    void addAllow(Long roleId, Long dsId, List<String> allow, Date endDateTime);

    /**
     * 增加允许的列
     *
     * @param roleId
     * @param dsId
     * @param allow
     * @return
     */
    void addAllowColumn(Long roleId, Long dsId, List<String> allow, Date endDateTime);

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
     * 删除允许列权限
     *
     * @param roleId
     * @param dsId
     * @param AllowColumn 为 null 时删除全部的列
     * @return
     */
    int deleteAllowColumn(Long roleId, Long dsId, List<String> AllowColumn);


    /**
     * 获取指定角色的权限肯定
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     */
    List<String> getAllowRoleIdAndDsId(Long roleId, Long dsId);

    /**
     * 获取指定角色的权限肯定
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     */
    List<String> getAllowTableByRoleIdAndDsIdAndSchemas(Long roleId, Long dsId, Set<String> schemas);


    /**
     * 获取指定角色列的的权限肯定
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     */
    List<String> getAllowColumnRoleIdAndDsId(Long roleId, Long dsId);


    /**
     * 获取指定角色的权限否定
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     */
    List<String> getNotAllowRoleIdAndDsId(Long roleId, Long dsId);

    /**
     * 获取指定角色列的的权限肯定
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     */
    List<String> getAllowColumnRoleIdAndDsIdAndSchemaTable(Long roleId, Long dsId, Set<String> schemaAndTables);

    /**
     * 获取指定角色列的的权限肯定
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     */
    List<String> getNotAllowColumnRoleIdAndDsIdAndSchemaTable(Long roleId, Long dsId, Set<String> schemaAndTables);

    /**
     * 获取指定角色的权限否定
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     */
    List<String> getNotAllowTableByRoleIdAndDsIdAndSchemas(Long roleId, Long dsId, Set<String> schemas);


    /**
     * 获取指定角色的权限否定
     *
     * @param roleId 角色编号
     * @param dsId   数据源编号
     */
    List<String> getNotAllowColumnRoleIdAndDsId(Long roleId, Long dsId);


    void addNotAllow(Long roleId, Long dsId, List<String> notAllow, Date endDateTime);

    /**
     * 增加否定的列
     *
     * @param roleId
     * @param dsId
     * @param notAllowColumn
     * @return
     */
    void addNotAllowColumn(Long roleId, Long dsId, List<String> notAllowColumn, Date endDateTime);


    /**
     * 删除否定权限
     *
     * @param roleId
     * @param dsId
     * @param notAllow 为 null 时删除全部
     * @return
     */
    int deleteNotAllow(Long roleId, Long dsId, List<String> notAllow);

    /**
     * 删除所有否定列
     *
     * @param roleId
     * @param dsId
     * @param notAllowColumn
     * @return
     */
    int deleteNotAllowColumn(Long roleId, Long dsId, List<String> notAllowColumn);


    /**
     * 获取分配的角色
     *
     * @param userId
     * @return
     */
    List<Long> getRoleIdsByUserId(Long userId);

    /**
     * 获取申请的角色
     *
     * @param userId
     * @return
     */
    List<Long> getApplyRoleIdsByUserId(Long userId);


    /**
     * 获取roleList
     *
     * @param userId
     * @return
     */
    List<RoleEntity> getRoleByUserId(@Param("userId") Long userId);

    /**
     * 获取RolEntity
     *
     * @param userId
     * @return
     */
    RoleEntity getByUserId(Long userId);


    /**
     * 根据userId更新
     *
     * @param
     */
    void updateByUserId(RoleEntity role);

    /**
     * 获取申请的表权限
     *
     * @param roleId
     * @param dsId
     * @param tableName
     * @return
     */
    List<String> getAllowTableByTableName(Long roleId, Long dsId, String tableName);

    /**
     * 获取申请的禁止表权限
     *
     * @param roleId
     * @param dsId
     * @param tableName
     * @return
     */
    List<String> getNotAllowTableByTableName(Long roleId, Long dsId, String tableName);

    /**
     * 获取允许的列权限
     *
     * @param roleId
     * @param dsId
     * @param column
     * @return
     */
    List<String> getAllowColumnByTableName(Long roleId, Long dsId, String column);

    /**
     * 获取禁止的列权限
     *
     * @param roleId
     * @param dsId
     * @param column
     * @return
     */
    List<String> getNotAllowColumnByTableName(Long roleId, Long dsId, String column);

}
