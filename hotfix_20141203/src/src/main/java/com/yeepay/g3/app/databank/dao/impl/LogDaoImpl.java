package com.yeepay.g3.app.databank.dao.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.dao.GenericDaoDefault;
import com.yeepay.g3.app.databank.dao.LogDao;
import com.yeepay.g3.app.databank.entity.LogEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 日志 Dao 实现类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-4 下午03:32
 */
@Repository
public class LogDaoImpl extends GenericDaoDefault<LogEntity> implements LogDao {

    @Override
    public int countByExecutor(String executor, String before) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("executor", executor);
        params.put("before", before);
        return (Integer) super.getSqlSession().selectOne(getStatementId("countByExecutor"), params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getUsedUserNameByDay(Date start, Date end) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("start", start);
        map.put("end", end);
        return getSqlSession().selectList(getStatementId("getUsedUserNameByDay"), map);
    }

    @Autowired
//    @Override
    public void setSqlSessionFactory2(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

}
