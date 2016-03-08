/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.service.impl;

import com.yeepay.g3.app.databank.dao.SQLStatusDao;
import com.yeepay.g3.app.databank.entity.SQLStatusEntity;
import com.yeepay.g3.app.databank.enumtype.CacheKeyEnum;
import com.yeepay.g3.app.databank.service.DefaultEhcacheManager;
import com.yeepay.g3.app.databank.service.SQLStatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Title: SQL 出错码业务逻辑层实现类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-10-24 下午6:49
 */
@Component
@Transactional(readOnly = false)
public class SQLStatusManagerImpl implements SQLStatusManager {

    private SQLStatusDao mapper;

    @Autowired
    private DefaultEhcacheManager ehcacheManager;

    @Override
    public SQLStatusEntity get(Long id) {
        // 已缓存
        String key = CacheKeyEnum.SQL_STATUS_BY_ID.toString() + ":" + id;
        SQLStatusEntity sqlStatus = (SQLStatusEntity) ehcacheManager.get(key);
        if (null == sqlStatus) {
            sqlStatus = mapper.get(id);
            ehcacheManager.put(key, sqlStatus);
        }
        return sqlStatus;
    }

    @Override
    public SQLStatusEntity create(SQLStatusEntity object) {
        mapper.add(object);
        return object;
    }

    @Transactional(readOnly = false)
    @Override
    public void update(SQLStatusEntity object) {
        mapper.update(object);
        String key = CacheKeyEnum.SQL_STATUS_BY_ID.toString() + ":" + object.getId();
        ehcacheManager.put(key, null);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Long id) {
        mapper.delete(id);
        String key = CacheKeyEnum.SQL_STATUS_BY_ID.toString() + ":" + id;
        ehcacheManager.put(key, null);
    }

    @Autowired
    public void setMapper(SQLStatusDao mapper) {
        this.mapper = mapper;
    }

    @Override
    public SQLStatusEntity getBySQLStatus(String status) {
        String key = CacheKeyEnum.SQL_STATUS_BY_STRING.toString() + ":" + status;
        SQLStatusEntity sqlStatus = (SQLStatusEntity) ehcacheManager.get(key);
        if (null == sqlStatus) {
            sqlStatus = mapper.getBySQLStatus(status);
            ehcacheManager.put(key, sqlStatus);
        }
        return sqlStatus;
    }
}
