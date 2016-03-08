/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.service;

import com.yeepay.g3.app.databank.entity.UserEntity;

import java.util.List;

/**
 * <p>Title: 用户管理业务逻辑层接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 2013-4-11 下午14:49:24
 */
public interface UserManager {

    /**
     * 更新用户的最近使用过的数据源
     *
     * @param userId 用户编号
     * @param lastDs 最近使用的数据源
     * @return
     */
    void updateLastDs(Long userId, Long lastDs);

    /**
     * 通过登录名获取用户
     *
     * @param loginname 登录名
     * @return
     */
    UserEntity getByLoginname(String loginname);

    /**
     * 获取用户
     *
     * @param id 用户编号
     * @return
     */
    UserEntity get(Long id);

    /**
     * 保存新用户
     *
     * @param user     用户实体
     * @param roleList 角色编号列表
     * @return
     */
    UserEntity create(UserEntity user, List<Long> roleList);

    /**
     * 删除用户
     *
     * @param userId 用户编号
     * @return
     */
    void delete(Long userId);

    /**
     * 更新用户
     *
     * @param user     用户实体
     * @param roleList 角色编号列表
     * @return
     */
    void update(UserEntity user, List<Long> roleList) throws Exception;

}
