package com.yeepay.g3.app.databank.dao;

import com.yeepay.g3.app.databank.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserEntity: dreambt
 * Date: 13-5-4
 * Time: 下午3:15
 */
public interface UserDao extends GenericDao<UserEntity> {

    /**
     * 更新用户的最近使用过的数据源
     *
     * @return
     */
    void updateLastDs(@Param("userId") Long userId, @Param("lastDs") Long lastDs);

    /**
     * 通过用户名获取用户
     *
     * @return
     */
    UserEntity getByLoginname(@Param("loginname") String loginname);

    /**
     * 保存 UserEntity-Role 关系
     *
     * @param userId     用户编号
     * @param roleIdList 角色编号列表
     */
    void saveRelation(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList,String flag);

    /**
     * 获取  UserEntity-Role 关系
     *
     * @param userId 用户编号
     * @param roleId 角色编号
     * @return
     */
    int  getRealation(Long userId, Long roleId);

    /**
     * 批量删除
     *
     * @param userId     用户编号
     * @param roleIdList 角色列表
     */
    void deleteRelationBatch(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList);

    /**
     * 批量删除
     *
     * @param userId 用户编号
     */
    void deleteRelationAll(@Param("userId") Long userId);

}
