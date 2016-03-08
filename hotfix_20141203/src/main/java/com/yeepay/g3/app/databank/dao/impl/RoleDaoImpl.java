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

import java.util.*;

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
    public List<String> getDsByRoleId(Long roleId) {
        return this.getSqlSession().selectList("getDsByRoleId", roleId);
    }

    @Override
    public List<String> getDsByRoleIdAndDsId(Long roleId, Long dsId, Date dateTime) {
        Map map = new HashMap();
        map.put("roleId", roleId);
        map.put("dsId", dsId);
        map.put("endDate", dateTime);
        return this.getSqlSession().selectList("getDsByRoleIdAndDsId", map);
    }

    @Override
    public List<String> getApplyRole(Long dsId, Long roleId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        return this.getSqlSession().selectList("getApplyRoleDs", map);
    }

    @Override
    public void updateByUserId(RoleEntity role) {
        this.getSqlSession().update("updateByUserId", role);
    }

    @Override
    public RoleEntity getByUserId(Long userId) {
        return (RoleEntity) this.getSqlSession().selectOne("getRoByUserId", userId);
    }


    @Override
    public void addRoleDsR(Long roleId, List<String> dsIds, Date endDate) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put(DS_IDS, dsIds);
        map.put("endDate", endDate);
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
    public void deleteRoleDsRAndTime(Long roleId, List<String> dsIds) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("roleId", roleId);
        map.put("dsIds", dsIds);
        this.getSqlSession().insert("deleteRoleDsRAndTime", map);
    }

    @Override
    public void addAllow(Long roleId, Long dsId, List<String> allow, Date date) {
        for (String schemaAndTable : allow) {
            String[] array = schemaAndTable.split("\\.");
            String schemaName = array[0];
            Map<String, Object> map = Maps.newHashMap();
            map.put(ROLE_ID, roleId);
            map.put("dsId", dsId);
            map.put("schemaName", schemaName);
            map.put("tableName", schemaAndTable);
            map.put("endDate", date);
            this.getSqlSession().insert("addAllow", map);
        }
    }

    @Override
    public void addAllowColumn(Long roleId, Long dsId, List<String> allowColumn, Date date) {
        for (String allowCloumn : allowColumn) {
            String[] array = allowCloumn.split("\\.");
            String schemaName = array[0];
            String tableName = array[1];
            Map<String, Object> map = Maps.newHashMap();
            map.put(ROLE_ID, roleId);
            map.put("dsId", dsId);
            map.put("schemaName", schemaName);
            map.put("tableName", tableName);
            map.put("allowCloumn", allowCloumn);
            map.put("endDate", date);
            this.getSqlSession().insert("addAllowColumn", map);
        }
    }

    @Override
    public int deleteAllow(Long roleId, Long dsId, List<String> allow) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("allow", allow);
        return this.getSqlSession().delete("deleteAllow", map);
    }


    @Override
    public int deleteAllowColumn(Long roleId, Long dsId, List<String> AllowColumn) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("allowColumn", AllowColumn);
        return this.getSqlSession().delete("deleteAllowColumn", map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllowRoleIdAndDsId(Long roleId, Long dsId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        return this.getSqlSession().selectList("getAllowByRoleIdAndDsId", map);
    }

    @Override
    public List<String> getAllowColumnRoleIdAndDsId(Long roleId, Long dsId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        return this.getSqlSession().selectList("getAllowColumnByRoleIdAndDsId", map);
    }

    @Override
    public List<String> getAllowColumnRoleIdAndDsIdAndSchemaTable(Long roleId, Long dsId, Set<String> schemaAndTables) {
        List<String> result = new ArrayList<String>();
        for (String schemaAndTable : schemaAndTables) {
            String[] array = schemaAndTable.split("\\.");
            String schemaName = array[0];
            String tableName = array[1];
            Map<String, Object> map = Maps.newHashMap();
            map.put(ROLE_ID, roleId);
            map.put("dsId", dsId);
            map.put("schemaName", schemaName.toUpperCase());
            map.put("tableName", tableName.toUpperCase());
            List<String> temp = this.getSqlSession().selectList("getAllowColumnByRoleIdAndDsIdAndSchemaTable", map);
            //保存所有的值
            result.addAll(temp);
        }
        return result;
    }

    @Override
    public List<String> getNotAllowColumnRoleIdAndDsIdAndSchemaTable(Long roleId, Long dsId, Set<String> schemaAndTables) {
        List<String> result = new ArrayList<String>();
        for (String schemaAndTable : schemaAndTables) {
            String[] array = schemaAndTable.split("\\.");
            String schemaName = array[0];
            String tableName = array[1];
            Map<String, Object> map = Maps.newHashMap();
            map.put(ROLE_ID, roleId);
            map.put("dsId", dsId);
            map.put("schemaName", schemaName.toUpperCase());
            map.put("tableName", tableName.toUpperCase());
            List<String> temp = this.getSqlSession().selectList("getNotAllowColumnByRoleIdAndDsIdAndSchemaTable", map);
            //保存所有的值
            result.addAll(temp);
        }
        return result;
    }

    @Override
    public List<String> getAllowTableByRoleIdAndDsIdAndSchemas(Long roleId, Long dsId, Set<String> schemas) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("schemas", schemas);
        return this.getSqlSession().selectList("getAllowTableByRoleIdAndDsIdAndSchemas", map);
    }

    @Override
    public List<String> getNotAllowTableByRoleIdAndDsIdAndSchemas(Long roleId, Long dsId, Set<String> schemas) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("schemas", schemas);
        return this.getSqlSession().selectList("getNotAllowTableByRoleIdAndDsIdAndSchemas", map);
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
    public List<String> getNotAllowColumnRoleIdAndDsId(Long roleId, Long dsId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        return this.getSqlSession().selectList("getNotAllowColumnByRoleIdAndDsId", map);
    }

    @Override
    public void addNotAllow(Long roleId, Long dsId, List<String> notAllow, Date date) {
        for (String SchemaAndTable : notAllow) {
            String[] array = SchemaAndTable.split("\\.");
            String schemaName = array[0];
            Map<String, Object> map = Maps.newHashMap();
            map.put(ROLE_ID, roleId);
            map.put("dsId", dsId);
            map.put("schemaName", schemaName);
            map.put("tableName", SchemaAndTable);
            map.put("endDate", date);
            this.getSqlSession().insert("addNotAllow", map);
        }
    }

    @Override
    public void addNotAllowColumn(Long roleId, Long dsId, List<String> notAllowColumn, Date date) {
        for (int i = 0; i < notAllowColumn.size(); i++) {
            String notAllowColumns = notAllowColumn.get(i);
            String[] array = notAllowColumns.split("\\.");
            String schemaName = array[0];
            String tableName = array[1];
            Map<String, Object> map = Maps.newHashMap();
            map.put(ROLE_ID, roleId);
            map.put("dsId", dsId);
            map.put("schemaName", schemaName);
            map.put("tableName", tableName);
            map.put("notAllowColumn", notAllowColumns);
            map.put("endDate", date);
            this.getSqlSession().insert("addNotAllowColumn", map);
        }
    }

    @Override
    public int deleteNotAllow(Long roleId, Long dsId, List<String> notAllow) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("notAllow", notAllow);
        return this.getSqlSession().delete("deleteNotAllow", map);
    }

    @Override
    public int deleteNotAllowColumn(Long roleId, Long dsId, List<String> notAllowColumn) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("notAllowColumn", notAllowColumn);
        return this.getSqlSession().delete("deleteNotAllowColumn", map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return this.getSqlSession().selectList("getRoleIdsByUserId", userId);
    }

    @Override
    public List<Long> getApplyRoleIdsByUserId(Long userId) {
        return this.getSqlSession().selectList("getApplyRoleIdsByUserId", userId);
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

    @Override
    public List<String> getAllowTableByTableName(Long roleId, Long dsId, String tableName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("tableName", tableName);
        return this.getSqlSession().selectList("getAllowTableByTableName", map);
    }

    @Override
    public List<String> getNotAllowTableByTableName(Long roleId, Long dsId, String tableName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("tableName", tableName);
        return this.getSqlSession().selectList("getNotTableByTableName", map);
    }

    @Override
    public List<String> getAllowColumnByTableName(Long roleId, Long dsId, String column) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("columnName", column);
        return this.getSqlSession().selectList("getColumnByColumnName", map);
    }

    @Override
    public List<String> getNotAllowColumnByTableName(Long roleId, Long dsId, String column) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(ROLE_ID, roleId);
        map.put("dsId", dsId);
        map.put("columnName", column);
        return this.getSqlSession().selectList("getNotColumnByColumnName", map);
    }

}
