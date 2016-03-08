/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.service.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.dao.RoleDao;
import com.yeepay.g3.app.databank.dao.UserDao;
import com.yeepay.g3.app.databank.entity.RoleEntity;
import com.yeepay.g3.app.databank.enumtype.CacheKeyEnum;
import com.yeepay.g3.app.databank.service.DefaultEhcacheManager;
import com.yeepay.g3.app.databank.service.RoleManager;
import com.yeepay.g3.app.databank.utils.Collections3;
import com.yeepay.g3.utils.common.CheckUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>Title: 角色业务逻辑层实现类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 2013-4-11 下午14:49:24
 */
@Component
@Transactional(readOnly = false)
public class RoleManagerImpl implements RoleManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleManager.class);

    private static final String FLAG_ONE = "1";

    private RoleDao roleMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DefaultEhcacheManager ehcacheManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<RoleEntity> getAll() {
        // 已缓存
        String key = CacheKeyEnum.ROLE_ALL.toString();
        List<RoleEntity> roleList = (List<RoleEntity>) ehcacheManager.get(key);
        if (null == roleList) {
            roleList = roleMapper.getAll();
            ehcacheManager.put(key, roleList);
        }
        return roleList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> getDsIdsByUserId(Long userId) {
        // 已缓存
        String key = CacheKeyEnum.DSID_BY_USERID.toString() + ":" + userId;
        List<Long> list = (List<Long>) ehcacheManager.get(key);
        if (null == list) {
            list = new ArrayList<Long>();
            List<String> lists = new ArrayList<String>();
            List<RoleEntity> roleList = roleMapper.getRoleByUserId(userId);
           /* for (RoleEntity role : roleList) {
                if (null != role.getPermissions()) {
                    for (String item : role.getPermissionList()) {
                        list.add(Long.parseLong(item));
                    }
                }
            }*/
            for (RoleEntity role : roleList) {
                List<String> temp = roleMapper.getRoleDsR(role.getId());
                lists.addAll(temp);
            }

            for (String id : lists) {
                list.add(Long.parseLong(id));
            }
            ehcacheManager.put(key, list);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Map<String, String>> getTablePermission(List<String> dsIds, Long roleId) {
        // 已缓存
        String key = CacheKeyEnum.ROLE_TABLE_PERMISSION_STR.toString() + ":" + roleId;
        Map<String, Map<String, String>> permission = (Map<String, Map<String, String>>) ehcacheManager.get(key);
        if (null == permission) {
            permission = new HashMap<String, Map<String, String>>();
            // 异常处理
            if (null == dsIds || null == roleId) {
                return permission;
            }
            for (String dsId : dsIds) {
                List<String> allow = roleMapper.getAllowRoleIdAndDsId(roleId, Long.parseLong(dsId));
                List<String> notAllow = roleMapper.getNotAllowRoleIdAndDsId(roleId, Long.parseLong(dsId));
                Map<String, String> item = Maps.newHashMap();
                item.put("allow", Collections3.convertToString(allow, "     ,     "));
                item.put("notAllow", Collections3.convertToString(notAllow, "    ,    "));
                permission.put(dsId, item);
            }
            ehcacheManager.put(key, permission);
        }
        return permission;
    }

    /**
     * 获取数据源中表总的列
     *
     * @param dsIds  数据源编号
     * @param roleId 角色编号
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Map<String, String>> getTableColumnPermission(List<String> dsIds, Long roleId) {
        // 已缓存
        String key = CacheKeyEnum.ROLE_TABLE_COLUMN_PERMISSION_STR.toString() + ":" + roleId;
        Map<String, Map<String, String>> permissionColumn = (Map<String, Map<String, String>>) ehcacheManager.get(key);
        if (null == permissionColumn) {
            permissionColumn = new HashMap<String, Map<String, String>>();
            // 异常处理
            if (null == dsIds || null == roleId) {
                return permissionColumn;
            }
            for (String dsId : dsIds) {
                List<String> allowColumn = roleMapper.getAllowColumnRoleIdAndDsId(roleId, Long.parseLong(dsId));
                List<String> notAllowColumn = roleMapper.getNotAllowColumnRoleIdAndDsId(roleId, Long.parseLong(dsId));
                Map<String, String> item = Maps.newHashMap();
                item.put("allowColumn", Collections3.convertToString(allowColumn, "    ,    "));
                item.put("notAllowColumn", Collections3.convertToString(notAllowColumn, "    ,    "));
                permissionColumn.put(dsId, item);
            }
            ehcacheManager.put(key, permissionColumn);
        }
        return permissionColumn;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Map<String, List<String>> getTablePermission(Long dsId, Long userId, Set<String> schemas) {

        Map<String, List<String>> permission = new HashMap<String, List<String>>();
        permission = new HashMap<String, List<String>>();
        //判断dsID是否在申请角色中拥有
        List<Long> allpyRoles = roleMapper.getApplyRoleIdsByUserId(userId);
        if (!CheckUtils.isEmpty(allpyRoles)) {
            Long applyRoleId = allpyRoles.get(0);
            //根据roleid 和dsId 判断数据源是否还有效
            List<String> roleIds = roleMapper.getApplyRole(dsId, applyRoleId);
            if (!CheckUtils.isEmpty(roleIds)) {
                //直接读取申请的权限
                permission.put("allow", roleMapper.getAllowTableByRoleIdAndDsIdAndSchemas(applyRoleId, dsId, schemas));
                permission.put("notAllow", roleMapper.getNotAllowTableByRoleIdAndDsIdAndSchemas(applyRoleId, dsId, schemas));
                return permission;
            }
        }
        //获取分配的角色
        List<Long> roleList = roleMapper.getRoleIdsByUserId(userId);
        for (Long roleId : roleList) {
            permission.put("allow", roleMapper.getAllowTableByRoleIdAndDsIdAndSchemas(roleId, dsId, schemas));
            permission.put("notAllow", roleMapper.getNotAllowTableByRoleIdAndDsIdAndSchemas(roleId, dsId, schemas));
        }
        return permission;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, List<String>> getTableColumnPermision(Long dsId, Long userId, Set<String> schemaAndTable) {
        Map<String, List<String>> permission = new HashMap<String, List<String>>();
        List<Long> roleList = roleMapper.getRoleIdsByUserId(userId);
        //判断dsID是否在申请角色中拥有
        List<Long> allpyRoles = roleMapper.getApplyRoleIdsByUserId(userId);
        if (!CheckUtils.isEmpty(allpyRoles)) {
            Long applyRoleId = allpyRoles.get(0);
            //根据roleid 和dsId 判断数据源是否还有效
            List<String> roleIds = roleMapper.getApplyRole(dsId, applyRoleId);
            if (!CheckUtils.isEmpty(roleIds)) {
                //直接读取申请的权限
                permission.put("allowColumn", roleMapper.getAllowColumnRoleIdAndDsIdAndSchemaTable(applyRoleId, dsId, schemaAndTable));
                permission.put("notAllowColumn", roleMapper.getNotAllowColumnRoleIdAndDsIdAndSchemaTable(applyRoleId, dsId, schemaAndTable));
                return permission;
            }
        }
        for (Long roleId : roleList) {
            permission.put("allowColumn", roleMapper.getAllowColumnRoleIdAndDsIdAndSchemaTable(roleId, dsId, schemaAndTable));
            permission.put("notAllowColumn", roleMapper.getNotAllowColumnRoleIdAndDsIdAndSchemaTable(roleId, dsId, schemaAndTable));
        }
        return permission;
    }


    @Override
    public RoleEntity get(Long id) {
        return roleMapper.get(id);
    }

    @SuppressWarnings("all")
    @Override
    public RoleEntity create(RoleEntity object, Map params) {
        roleMapper.add(object);
        if (!CheckUtils.isEmpty(object.getPermissionList())) {
            for (String dsId : object.getPermissionList()) {
                String[] allowArray = (String[]) params.get("allow-" + dsId);
                if (!CheckUtils.isEmpty(allowArray)) {
                    List<String> allow = Arrays.asList(allowArray);
                    roleMapper.addAllow(object.getId(), Long.parseLong(dsId), allow, null);
                }
                String[] notAllowArray = (String[]) params.get("notAllow-" + dsId);
                if (!CheckUtils.isEmpty(notAllowArray)) {
                    List<String> notAllow = Arrays.asList(notAllowArray);
                    roleMapper.addNotAllow(object.getId(), Long.parseLong(dsId), notAllow, null);
                }
                String[] allowColumnArray = (String[]) params.get("allowColumn-" + dsId);
                if (!CheckUtils.isEmpty(allowColumnArray)) {
                    List<String> allallowColumn = Arrays.asList(allowColumnArray);
                    roleMapper.addAllowColumn(object.getId(), Long.parseLong(dsId), allallowColumn, null);
                }
                String[] notAllowColumnArray = (String[]) params.get("notAllowColumn-" + dsId);
                if (!CheckUtils.isEmpty(notAllowColumnArray)) {
                    List<String> notAllowColumn = Arrays.asList(notAllowColumnArray);
                    roleMapper.addNotAllowColumn(object.getId(), Long.parseLong(dsId), notAllowColumn, null);
                }
            }
            roleMapper.addRoleDsR(object.getId(), object.getPermissionList(), null);
        }
        //清除获取所有的role缓存
        String key = CacheKeyEnum.ROLE_ALL.toString();
        ehcacheManager.put(key, null);
        return object;
    }

    @Override
    public RoleEntity getByUserId(Long userId) {
        return roleMapper.getByUserId(userId);
    }

    @Override
    public void deleteRoleDsR(Long roleId, List<String> dsIds) {
        roleMapper.deleteRoleDsR(roleId, dsIds);
    }

    @Override
    public void addApplyRole(RoleEntity object, Map params, Date endDateTime, String dsId) {
        List<String> pemission = new ArrayList<String>();
        pemission.add(dsId);
        object.setPermissions("");
        //为用户添加申请的角色组
        roleMapper.add(object);
        Long roleId = roleMapper.getByUserId(object.getUserId()).getId();
        List<Long> roleList = new ArrayList<Long>();
        roleList.add(roleId);
        //将角色组自动分配给用户
        userDao.saveRelation(object.getUserId(), roleList, null);
        //建立角色和数据源的关系
        roleMapper.addRoleDsR(roleId, pemission, endDateTime);
        updatePemission(roleId, Long.parseLong(dsId), endDateTime, params);
        //清除缓存
        String key = CacheKeyEnum.ROLE_ALL.toString();
        ehcacheManager.put(key, roleList);
        key = CacheKeyEnum.DS_BY_USERID + ":" + object.getUserId().toString();
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.DSID_BY_USERID + ":" + object.getUserId().toString();
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.ROLE_TABLE_COLUMN_PERMISSION_STR.toString() + ":" + roleId;
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.ROLE_TABLE_PERMISSION_STR.toString() + ":" + roleId;
        ehcacheManager.put(key, null);
    }

    @Override
    public void updateApplyRole(RoleEntity object, Map params, Date endDateTime, Long dsId, Long roleId) {
        List<String> dsIds = new ArrayList<String>();
        dsIds.add(dsId.toString());
        //判断是否已经存在ds
        List<String> obj = roleMapper.getDsByRoleIdAndDsId(roleId, dsId, endDateTime);
        if (!CheckUtils.isEmpty(obj)) {
            roleMapper.deleteRoleDsRAndTime(roleId, obj);
        }
        //添加角色和数据源的关系
        roleMapper.addRoleDsR(roleId, dsIds, endDateTime);
        updatePemission(roleId, dsId, endDateTime, params);
        //清除缓存
        String key = CacheKeyEnum.DS_BY_USERID + ":" + object.getUserId().toString();
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.DSID_BY_USERID + ":" + object.getUserId().toString();
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.ROLE_TABLE_COLUMN_PERMISSION_STR.toString() + ":" + roleId;
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.ROLE_TABLE_PERMISSION_STR.toString() + ":" + roleId;
        ehcacheManager.put(key, null);
    }

    /**
     * 更新角色－数据源 中的表和列
     *
     * @param roleId
     * @param dsId
     * @param endDateTime
     * @param params
     */
    public void updatePemission(Long roleId, Long dsId, Date endDateTime, Map params) {
        //添加数据源的表和列的权限
        String[] allowArray = (String[]) params.get("allow-" + dsId);
        if (!CheckUtils.isEmpty(allowArray)) {
            List<String> allow = Arrays.asList(allowArray);
            for (String allowTable : allowArray) {
                List<String> obj = roleMapper.getAllowTableByTableName(roleId, dsId, allowTable);
                if (!CheckUtils.isEmpty(obj)) {
                    roleMapper.deleteAllow(roleId, dsId, obj);
                }
            }
            roleMapper.addAllow(roleId, dsId, allow, endDateTime);
        }

        String[] notAllowArray = (String[]) params.get("notAllow-" + dsId);
        if (!CheckUtils.isEmpty(notAllowArray)) {
            List<String> notAllow = Arrays.asList(notAllowArray);
            for (String notAllowTable : notAllowArray) {
                List<String> obj = roleMapper.getNotAllowTableByTableName(roleId, dsId, notAllowTable);
                if (!CheckUtils.isEmpty(obj)) {
                    roleMapper.deleteNotAllow(roleId, dsId, obj);
                }
            }
            roleMapper.addNotAllow(roleId, dsId, notAllow, endDateTime);
        }

        String[] allowColumnArray = (String[]) params.get("allowColumn-" + dsId);
        if (!CheckUtils.isEmpty(allowColumnArray)) {
            for (String AllowColumn : allowColumnArray) {
                List<String> obj = roleMapper.getAllowColumnByTableName(roleId, dsId, AllowColumn);
                if (!CheckUtils.isEmpty(obj)) {
                    roleMapper.deleteAllowColumn(roleId, dsId, obj);
                }
            }
            List<String> allallowColumn = Arrays.asList(allowColumnArray);
            roleMapper.addAllowColumn(roleId, dsId, allallowColumn, endDateTime);
        }

        String[] notAllowColumnArray = (String[]) params.get("notAllowColumn-" + dsId);
        if (!CheckUtils.isEmpty(notAllowColumnArray)) {
            for (String notAllowColumn : notAllowColumnArray) {
                List<String> obj = roleMapper.getNotAllowColumnByTableName(roleId, dsId, notAllowColumn);
                if (!CheckUtils.isEmpty(obj)) {
                    roleMapper.deleteNotAllowColumn(roleId, dsId, obj);
                }
            }
            List<String> notAllowColumn = Arrays.asList(notAllowColumnArray);
            roleMapper.addNotAllowColumn(roleId, dsId, notAllowColumn, endDateTime);
        }
    }

    @Override
    public void deleteAllTableAndColumn(Long roleId, Long dsId) {
        roleMapper.deleteAllow(roleId, dsId, null);
        roleMapper.deleteNotAllow(roleId, dsId, null);
        roleMapper.deleteAllowColumn(roleId, dsId, null);
        roleMapper.deleteNotAllowColumn(roleId, dsId, null);
        // 重置缓存
        String key = CacheKeyEnum.ROLE_TABLE_PERMISSION_STR.toString() + ":" + roleId;
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.ROLE_TABLE_COLUMN_PERMISSION_STR.toString() + ":" + roleId;
        ehcacheManager.put(key, null);
    }


    @SuppressWarnings("all")
    @Override
    public void update(RoleEntity role, Map params) {
        LOGGER.info("更新用户 user={}, params={}.", role.toString(), params);
        //所有的数据源都没有选择
        if (CheckUtils.isEmpty(role.getPermissions())) {
            roleMapper.deleteAllowColumn(role.getId(), null, null);
            roleMapper.deleteNotAllowColumn(role.getId(), null, null);
            roleMapper.deleteAllow(role.getId(), null, null);
            roleMapper.deleteNotAllow(role.getId(), null, null);
        } else {
            for (String dsId : role.getPermissionList()) {
                addTables(dsId, role, params);
                addColumn(dsId, role, params);
            }
        }
        roleMapper.update(role);
        // 更新 Role-DataSource 关系
        List<String> newRDList = role.getPermissionList();
        //先全部删除
        roleMapper.deleteRoleDsR(role.getId(), null);
        //再添加新选择的
        if (!CheckUtils.isEmpty(newRDList)) {
            roleMapper.addRoleDsR(role.getId(), newRDList, null);
        }

        // 重置缓存
        String key = CacheKeyEnum.ROLE_TABLE_PERMISSION_STR.toString() + ":" + role.getId();
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.ROLE_TABLE_COLUMN_PERMISSION_STR.toString() + ":" + role.getId();
        ehcacheManager.put(key, null);
    }


    /**
     * 增加允许和禁止的列权限
     *
     * @param dsId
     * @param role
     * @param params
     */
    public void addColumn(String dsId, RoleEntity role, Map params) {
        roleMapper.deleteAllowColumn(role.getId(), Long.parseLong(dsId), null);
        String[] allowColumnArray = (String[]) params.get("allowColumn-" + dsId);
        if (!CheckUtils.isEmpty(allowColumnArray)) {
            List<String> allowColumn = Arrays.asList(allowColumnArray);
            roleMapper.addAllowColumn(role.getId(), Long.parseLong(dsId), allowColumn, null);
        }
        roleMapper.deleteNotAllowColumn(role.getId(), Long.parseLong(dsId), null);
        String[] notAllowColumnArray = (String[]) params.get("notAllowColumn-" + dsId);
        if (!CheckUtils.isEmpty(notAllowColumnArray)) {
            List<String> notAllowColumn = Arrays.asList(notAllowColumnArray);
            roleMapper.addNotAllowColumn(role.getId(), Long.parseLong(dsId), notAllowColumn, null);
        }
    }

    /**
     * 增加允许和禁止的表权限
     *
     * @param dsId
     * @param role
     * @param params
     */
    public void addTables(String dsId, RoleEntity role, Map params) {
        //表级
        String[] allowArray = (String[]) params.get("allow-" + dsId);
        roleMapper.deleteAllow(role.getId(), Long.parseLong(dsId), null);
        if (!CheckUtils.isEmpty(allowArray)) {
            List<String> allowTable = Arrays.asList(allowArray);
            roleMapper.addAllow(role.getId(), Long.parseLong(dsId), allowTable, null);
        }
        String[] notAllowArray = (String[]) params.get("notAllow-" + dsId);
        // 清空
        roleMapper.deleteNotAllow(role.getId(), Long.parseLong(dsId), null);
        if (!CheckUtils.isEmpty(notAllowArray)) {
            List<String> notAllowTable = Arrays.asList(notAllowArray);
            roleMapper.addNotAllow(role.getId(), Long.parseLong(dsId), notAllowTable, null);
        }

    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteRoleDsR(id, null);
        roleMapper.delete(id);
        //删除允许禁止的表和列
        roleMapper.deleteAllowColumn(id, null, null);
        roleMapper.deleteNotAllowColumn(id, null, null);
        roleMapper.deleteAllow(id, null, null);
        roleMapper.deleteNotAllow(id, null, null);

        // 重置缓存
        String key = CacheKeyEnum.ROLE_TABLE_PERMISSION_STR.toString() + ":" + id;
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.ROLE_TABLE_COLUMN_PERMISSION_STR.toString() + ":" + id;
        ehcacheManager.put(key, null);

    }

    @Autowired
    public void setRoleMapper(RoleDao roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<String> getDsByRoleId(Long roleId) {
        return roleMapper.getDsByRoleId(roleId);
    }


}
