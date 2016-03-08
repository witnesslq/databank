/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.service.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.dao.RoleDao;
import com.yeepay.g3.app.databank.entity.RoleEntity;
import com.yeepay.g3.app.databank.enumtype.CacheKeyEnum;
import com.yeepay.g3.app.databank.service.DefaultEhcacheManager;
import com.yeepay.g3.app.databank.service.RoleManager;
import com.yeepay.g3.app.databank.utils.Collections3;
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

    private RoleDao roleMapper;

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
            List<RoleEntity> roleList = roleMapper.getRoleByUserId(userId);
            for (RoleEntity role : roleList) {
                if (null != role.getPermissions()) {
                    for (String item : role.getPermissionList()) {
                        list.add(Long.parseLong(item));
                    }
                }
            }
            ehcacheManager.put(key, list);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Map<String, String>> getTablePermission(List<String> dsIds, Long roleId) {
        // 已缓存
        String key = CacheKeyEnum.ROLE_TABLE_PERMISSION_STR.toString() + ":" + roleId + ":" + Collections3.convertToString(dsIds, ",");
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
                item.put("allow", Collections3.convertToString(allow, ","));
                item.put("notAllow", Collections3.convertToString(notAllow, ","));
                permission.put(dsId, item);
            }
            ehcacheManager.put(key, permission);
        }
        return permission;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, List<String>> getTablePermission(Long dsId, Long userId) {
        // 已缓存
        String key = CacheKeyEnum.ROLE_TABLE_PERMISSION_LIST.toString() + ":" + dsId + ":" + userId;
        Map<String, List<String>> permission = (Map<String, List<String>>) ehcacheManager.get(key);
        if (null == permission) {
            permission = new HashMap<String, List<String>>();
            permission.put("allow", new ArrayList<String>());
            permission.put("notAllow", new ArrayList<String>());
            List<Long> roleList = roleMapper.getRoleIdsByUserId(userId);
            for (Long roleId : roleList) {
                Set<String> allow = new HashSet<String>(roleMapper.getAllowRoleIdAndDsId(roleId, dsId));
                allow.addAll(permission.get("allow"));
                Set<String> notAllow = new HashSet<String>(roleMapper.getNotAllowRoleIdAndDsId(roleId, dsId));
                notAllow.addAll(permission.get("notAllow"));

                permission.put("allow", Collections3.subtract(allow, notAllow));
                permission.put("notAllow", new ArrayList<String>(notAllow));
            }
            ehcacheManager.put(key, permission);
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
        for (String dsId : object.getPermissionList()) {
            roleMapper.add(object);
            String[] allowArray = (String[]) params.get("allow-" + dsId);
            if (null != allowArray && allowArray.length > 0) {
                List<String> allow = Arrays.asList(allowArray);
                roleMapper.addAllow(object.getId(), Long.parseLong(dsId), allow);
            }

            String[] notAllowArray = (String[]) params.get("notAllow-" + dsId);
            if (null != notAllowArray && notAllowArray.length > 0) {
                List<String> notAllow = Arrays.asList(notAllowArray);
                roleMapper.addNotAllow(object.getId(), Long.parseLong(dsId), notAllow);
            }
        }
        roleMapper.addRoleDsR(object.getId(), object.getPermissionList());
        return object;
    }

    @SuppressWarnings("all")
    @Override
    public void update(RoleEntity role, Map params) {
        LOGGER.info("更新用户 user={}, params={}.", role.toString(), params);
        for (String dsId : role.getPermissionList()) {
            String[] allowArray = (String[]) params.get("allow-" + dsId);
            if (null == allowArray || allowArray.length == 0) {
                // 清空
                roleMapper.deleteAllow(role.getId(), Long.parseLong(dsId), null);
            } else {
                List<String> allow = Arrays.asList(allowArray);
                List<String> oldAllow = roleMapper.getAllowRoleIdAndDsId(role.getId(), Long.parseLong(dsId));
                List<String> deleteAllow = Collections3.subtract(oldAllow, allow);
                if (null != deleteAllow && deleteAllow.size() > 0) {
                    roleMapper.deleteAllow(role.getId(), Long.parseLong(dsId), deleteAllow);
                }

                List<String> addAllow = Collections3.subtract(allow, oldAllow);
                if (null != addAllow && addAllow.size() > 0) {
                    roleMapper.addAllow(role.getId(), Long.parseLong(dsId), addAllow);
                }
            }
            String[] notAllowArray = (String[]) params.get("notAllow-" + dsId);
            if (null == notAllowArray || notAllowArray.length == 0) {
                // 清空
                roleMapper.deleteNotAllow(role.getId(), Long.parseLong(dsId), null);
            } else {
                List<String> notAllow = Arrays.asList(notAllowArray);
                List<String> oldNotAllow = roleMapper.getNotAllowRoleIdAndDsId(role.getId(), Long.parseLong(dsId));
                List<String> deleteNotAllow = Collections3.subtract(oldNotAllow, notAllow);
                if (null != deleteNotAllow && deleteNotAllow.size() > 0) {
                    roleMapper.deleteNotAllow(role.getId(), Long.parseLong(dsId), deleteNotAllow);
                }

                List<String> addNotAllow = Collections3.subtract(notAllow, oldNotAllow);
                if (null != addNotAllow && addNotAllow.size() > 0) {
                    roleMapper.addNotAllow(role.getId(), Long.parseLong(dsId), addNotAllow);
                }
            }
        }
        roleMapper.update(role);

        // 更新 Role-DataSource 关系
        List<String> oldRDList = roleMapper.getRoleDsR(role.getId());
        List<String> newRDList = role.getPermissionList();
        if (null == newRDList || newRDList.size() == 0) {
//			if (null == oldRDList || oldRDList.size() == 0) {
//				roleMapper.deleteRoleDsR(role.getId());
//			} else {
            roleMapper.deleteRoleDsR(role.getId(), oldRDList);
//			}
        } else {
            List<String> addList;
            if (null == oldRDList || oldRDList.size() == 0) {
                addList = newRDList;
            } else {
                addList = Collections3.subtract(newRDList, oldRDList);
                List<String> removeList = Collections3.subtract(oldRDList, newRDList);
                if (null != removeList && removeList.size() > 0) {
                    roleMapper.deleteRoleDsR(role.getId(), removeList);
                }
            }
            if (null != addList && addList.size() > 0) {
                roleMapper.addRoleDsR(role.getId(), addList);
            }
        }

        // 重置缓存
        String key = CacheKeyEnum.ROLE_TABLE_PERMISSION_STR.toString() + ":" + role.getId() + ":" + role.getPermissions();
        ehcacheManager.put(key, null);
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteRoleDsR(id, null);
        roleMapper.delete(id);
    }

    @Autowired
    public void setRoleMapper(RoleDao roleMapper) {
        this.roleMapper = roleMapper;
    }

}
