/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.dao.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.dao.GenericDaoDefault;
import com.yeepay.g3.app.databank.dao.UserDao;
import com.yeepay.g3.app.databank.entity.UserEntity;
import com.yeepay.g3.utils.common.CheckUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: User Dao 实现类</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-4 下午3:16
 */
@Repository
public class UserDaoImpl extends GenericDaoDefault<UserEntity> implements UserDao {

    private static final String USER_ID = "userId";

    @Override
    public void updateLastDs(@Param(USER_ID) Long userId, @Param("lastDs") Long lastDs) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(USER_ID, userId);
        map.put("lastDs", lastDs);
        this.getSqlSession().update(getStatementId("updateLastDs"), map);
    }

    @Override
    public UserEntity getByLoginname(String loginname) {
        return (UserEntity) this.queryOne("getByLoginname", loginname);
    }

    @Deprecated
    public Long getLastDsIdByUserId(Long userId) {
        return (Long) this.queryOne("getLastDsIdByUserId", userId);
    }

    @Override
    public void saveRelation(@Param(USER_ID) Long userId, @Param("roleIdList") List<Long> roleIdList, String flag) {
        Map<String, Object> map = Maps.newHashMap();
        if (null == roleIdList || roleIdList.size() == 0) {
            return;
        }
        map.put(USER_ID, userId);
        map.put("roleIdList", roleIdList);
        map.put("flag", flag);
        this.getSqlSession().update("saveRelation", map);
    }

    @Override
    public int getRealation(Long userId, Long roleId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(USER_ID, userId);
        map.put("roleId", roleId);
        if (CheckUtils.isEmpty(this.query("getRelation", map))) {
            return 0;
        }
        return 1;
    }

    @Override
    public void deleteRelationBatch(@Param(USER_ID) Long userId, @Param("roleIdList") List<Long> roleIdList) {
        Map<String, Object> map = Maps.newHashMap();
        if (null == roleIdList || roleIdList.size() == 0) {
            return;
        }
        map.put(USER_ID, userId);
        map.put("roleIdList", roleIdList);
        this.getSqlSession().delete("deleteRelationBatch", map);
    }

    @Override
    public void deleteRelationAll(@Param(USER_ID) Long userId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(USER_ID, userId);
        this.getSqlSession().delete("deleteRelationAll", map);
    }

    @Autowired
//    @Override
    public void setSqlSessionFactory2(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


}
