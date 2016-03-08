/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.service.impl;

import com.yeepay.g3.app.databank.dao.RoleDao;
import com.yeepay.g3.app.databank.dao.UserDao;
import com.yeepay.g3.app.databank.entity.UserEntity;
import com.yeepay.g3.app.databank.enumtype.CacheKeyEnum;
import com.yeepay.g3.app.databank.service.DefaultEhcacheManager;
import com.yeepay.g3.app.databank.service.UserManager;
import com.yeepay.g3.app.databank.utils.Collections3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Title: 用户管理业务逻辑层实现类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 2013-4-11 下午14:29:24
 */
@Component
@Transactional(readOnly = false)
public class UserManagerImpl implements UserManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManager.class);

    private UserDao userMapper;

    private RoleDao roleMapper;

    @Autowired
    private DefaultEhcacheManager ehcacheManager;

    @Override
    public UserEntity get(Long id) {
        return userMapper.get(id);
    }

    @Override
    public void updateLastDs(Long userId, Long lastDs) {
        userMapper.updateLastDs(userId, lastDs);
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserEntity getByLoginname(String loginname) {
        // 已缓存
        String key = CacheKeyEnum.USER_BY_LOGINNAME.toString() + ":" + loginname;
        UserEntity user = (UserEntity) ehcacheManager.get(key);
        if (null == user) {
            user = userMapper.getByLoginname(loginname);
            ehcacheManager.put(key, user);
        }
        return user;
    }

    @Override
    @Transactional(readOnly = false)
    public UserEntity create(UserEntity user, List<Long> roleList) {
        LOGGER.info("保存用户 user={}.", user.toString());
        userMapper.add(user);

        // 未指定权限的新用户
        if (null == roleList) {
            userMapper.deleteRelationAll(user.getId());
        } else {
            // 保存 UserEntity-Role 关系
            userMapper.saveRelation(user.getId(), roleList);
        }
        return user;
    }

    // TODO 移动到 Biz 层
    @Override
    @Transactional(readOnly = false)
    public void update(UserEntity user, List<Long> newRoleIdList) throws Exception {
        LOGGER.info("更新用户 user={}.", user.toString());
        try {
            userMapper.update(user);
        } catch (Exception e) {
            LOGGER.error("更新用户出错.");
            throw new Exception();
        }

        // 更新缓存
        String key = CacheKeyEnum.DS_BY_USERID + ":" + user.getId();
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.DSID_BY_USERID.toString() + ":" + user.getId();
        ehcacheManager.put(key, null);

        // 更新 User-Role 关系
        List<Long> oldRoleIdList = roleMapper.getRoleIdsByUserId(user.getId());
        if (null == newRoleIdList || newRoleIdList.size() == 0) {
            if (null == oldRoleIdList || oldRoleIdList.size() == 0) {
                userMapper.deleteRelationAll(user.getId());
            } else {
                userMapper.deleteRelationBatch(user.getId(), oldRoleIdList);
            }
        } else {
            List<Long> addList;
            if (null == oldRoleIdList || oldRoleIdList.size() == 0) {
                addList = newRoleIdList;
            } else {
                addList = Collections3.subtract(newRoleIdList, oldRoleIdList);
                List<Long> removeList = Collections3.subtract(oldRoleIdList, newRoleIdList);
                if (null != removeList && removeList.size() > 0) {
                    userMapper.deleteRelationBatch(user.getId(), removeList);
                }
            }
            if (null != addList && addList.size() > 0) {
                userMapper.saveRelation(user.getId(), addList);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long userId) {
        // 更新缓存
        String key = CacheKeyEnum.DS_BY_USERID + ":" + userId;
        ehcacheManager.put(key, null);
        key = CacheKeyEnum.DSID_BY_USERID.toString() + ":" + userId;
        ehcacheManager.put(key, null);

        userMapper.delete(userId);
        userMapper.deleteRelationAll(userId);
    }

    @Autowired
    public void setUserMapper(UserDao userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setRoleMapper(RoleDao roleMapper) {
        this.roleMapper = roleMapper;
    }

}
