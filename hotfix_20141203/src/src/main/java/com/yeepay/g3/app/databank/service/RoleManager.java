/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.service;

import com.yeepay.g3.app.databank.entity.RoleEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: 角色业务逻辑层接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 2013-4-11 下午14:49:24
 */
public interface RoleManager {

    RoleEntity get(Long id);

    /**
     * 创建
     *
     * @param object 角色
     * @param params 表级权限
     * @return
     */
    RoleEntity create(RoleEntity object, Map params);

    void update(RoleEntity object, Map params);

    void delete(Long id);

    /**
     * 获取所有用户组
     *
     * @return
     */
    List<RoleEntity> getAll();

    /**
     * 数据源编号
     *
     * @param userId 用户编号
     * @return
     */
    List<Long> getDsIdsByUserId(Long userId);

    /**
     * 获取指定数据源和角色的表级权限(用于权限控制页面)
     *
     * @param dsIds  数据源编号
     * @param roleId 角色编号
     * @return
     */
    Map<String, Map<String, String>> getTablePermission(List<String> dsIds, Long roleId);

    /**
     * 获取指定数据源和角色的表级权限(用于查询时的权限控制)
     *
     * @param dsId   数据源编号
     * @param userId 用户编号
     * @return
     */
    Map<String, List<String>> getTablePermission(Long dsId, Long userId);

}
