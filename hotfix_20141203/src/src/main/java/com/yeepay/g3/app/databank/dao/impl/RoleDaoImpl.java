/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.dao.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.dao.GenericDaoDefault;
import com.yeepay.g3.app.databank.dao.RoleDao;
import com.yeepay.g3.app.databank.entity.RoleEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: Role Dao 实现类</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-4 下午3:16
 */
@Repository
public class RoleDaoImpl extends GenericDaoDefault<RoleEntity> implements RoleDao {

    private static final String ROLE_ID = "roleId";

    private static final String DS_IDS = "dsIds";

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getRoleDsR(Long roleId) {
        return this.getSqlSession().selectList("getRoleDsR", roleId);
    }

    @Override
    public void addRoleDsR(Long roleId, List<String> dsIds) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put(DS_IDS, dsIds);
        this.getSqlSession().insert("addRoleDsR", map);
    }

    @Override
    public void deleteRoleDsR(Long roleId, List<String> dsIds) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put(DS_IDS, dsIds);
        this.getSqlSession().insert("deleteRoleDsR", map);
    }

    @Override
    public int addAllow(Long roleId, Long dsId, List<String> allow) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("allow", allow);
        return this.getSqlSession().insert("addAllow", map);
    }

    @Override
    public int deleteAllow(Long roleId, Long dsId, List<String> allow) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("allow", allow);
        return this.getSqlSession().delete("deleteAllow", map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllowRoleIdAndDsId(Long roleId, Long dsId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        return this.getSqlSession().selectList("getAllowByRoleIdAndDsId", map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getNotAllowRoleIdAndDsId(Long roleId, Long dsId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        return this.getSqlSession().selectList("getNotAllowByRoleIdAndDsId", map);
    }

    @Override
    public int addNotAllow(Long roleId, Long dsId, List<String> notAllow) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("notAllow", notAllow);
        return this.getSqlSession().insert("addNotAllow", map);

    }

    @Override
    public int deleteNotAllow(Long roleId, Long dsId, List<String> notAllow) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("notAllow", notAllow);
        return this.getSqlSession().delete("deleteNotAllow", map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return this.getSqlSession().selectList("getRoleIdsByUserId", userId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RoleEntity> getRoleByUserId(Long userId) {
        return this.getSqlSession().selectList("getRoleByUserId", userId);
    }

    @Autowired
//    @Override
    public void setSqlSessionFactory2(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

}
